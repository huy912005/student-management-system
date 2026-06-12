package com.JavaSpringBoot.BESpring.Service.impl;

import com.JavaSpringBoot.BESpring.DTO.Request.SinhVienRequest;
import com.JavaSpringBoot.BESpring.DTO.Response.ApiResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.DashboardResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.SinhVienResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.TopSinhVienResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.page.Meta;
import com.JavaSpringBoot.BESpring.DTO.Response.page.PageResponse;
import com.JavaSpringBoot.BESpring.Entity.SinhVienEntity;
import com.JavaSpringBoot.BESpring.Repository.SinhVIenRepository;
import com.JavaSpringBoot.BESpring.Service.ISinhVienService;
import com.JavaSpringBoot.BESpring.Service.ImageUploadService;
import com.JavaSpringBoot.BESpring.mapper.SinhVienMapper;

import com.JavaSpringBoot.BESpring.Exception.BadRequestException;
import com.JavaSpringBoot.BESpring.Exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SinhVienServiceImpl implements ISinhVienService {
    private static final Logger log = LoggerFactory.getLogger(SinhVienServiceImpl.class);
    private final SinhVIenRepository sinhVIenRepository;
    private final ImageUploadService imageUploadService;
    @Override
    public PageResponse<SinhVienResponse> getAll(int page, int size) {
        log.debug("Lấy danh sách sinh viên, page={}, size={}", page, size);

        // BƯỚC 1: LẤY DỮ LIỆU TỪ DATABASE (Đã phân trang)
        // PageRequest.of(page, size) sẽ tự động tạo ra câu lệnh SQL có LIMIT và OFFSET.
        // Ví dụ: lấy page = 0, size = 5 -> Sinh ra SQL: SELECT * FROM sinh_vien LIMIT 5 OFFSET 0
        // Biến pageData lúc này chứa cả Data (danh sách sinh viên) lẫn các thông tin phụ (tổng số trang, tổng số record...)
        Page<SinhVienEntity> pageData = sinhVIenRepository.findAll(PageRequest.of(page, size, Sort.by("ten").ascending()));

        // BƯỚC 2: BIẾN ĐỔI ENTITY SANG DTO (Tránh lộ DB ra ngoài)
        // - pageData.getContent(): Moi cái danh sách SinhVienEntity từ trong pageData ra.
        // - .stream().map(...): Giống như một cái băng chuyền trong nhà máy. Đưa từng SinhVienEntity qua máy ép (SinhVienMapper::toResponse) để biến nó thành SinhVienResponse.
        // - .toList(): Gom tất cả các SinhVienResponse vừa ép xong lại thành một List mới.
        List<SinhVienResponse> data = pageData.getContent().stream().map(SinhVienMapper::toResponse).toList();

        // BƯỚC 3: TẠO THÔNG TIN SIÊU DỮ LIỆU (META)
        // Frontend cần biết đang ở trang mấy, 1 trang có bao nhiêu người, tổng số người trong DB là bao nhiêu để vẽ cái nút phân trang (1, 2, 3, 4, Next, Prev)
        Meta meta = new Meta();
        meta.setPage(page);
        meta.setSize(size);
        // Hàm getTotalElements() sẽ tự động đếm tổng số sinh viên trong DB (chạy lệnh SELECT COUNT(*))
        meta.setTotal(pageData.getTotalElements());

        // BƯỚC 4: ĐÓNG GÓI VÀ TRẢ VỀ
        // Bỏ cái data (bước 2) và cái meta (bước 3) vào chung một cái hộp (PageResponse)
        PageResponse<SinhVienResponse> res = new PageResponse<>();
        res.setData(data);
        res.setMeta(meta);

        // Trả cái hộp này về cho Controller
        return res;
    }
    @Override
    @Transactional
    public SinhVienResponse save(SinhVienRequest sinhVienRequest){
        // Ghi nhận lúc user vừa gọi hàm (dùng loại DEBUG để dev nhìn)
        log.debug("Bắt đầu xử lý thêm mới sinh viên có tên: {}", sinhVienRequest.getTen());

        // Fix bug so sánh chuỗi (vấn đề 1 hôm nọ) bằng .isBlank()
        if(sinhVienRequest.getTen() == null || sinhVienRequest.getTen().isBlank()){
            // Khách nhập sai, ghi cảnh báo tốn chút dung lượng file log (loại WARN)
            log.warn("Thêm mới thất bại - Dữ liệu tên sinh viên bị trống!");
            throw new BadRequestException("Tên không được trống!");
        }

        SinhVienEntity entity = SinhVienMapper.toEntity(sinhVienRequest);
        SinhVienEntity saved = sinhVIenRepository.save(entity);
        
        // Đã lưu Database thành công, ghi chú lại là tốt đẹp (loại INFO)
        log.info("Lưu thành công sinh viên vào DB với ID: {}", saved.getId());

        return SinhVienMapper.toResponse(saved);
    }
    @Transactional
    public SinhVienResponse update(int id,SinhVienRequest sinhVienRequest){
        log.debug("Cập nhật sinh viên có id = {}, request : {}",id,sinhVienRequest);
        // Tìm sinh viên, không tìm thấy → throw 404
        SinhVienEntity  entity = sinhVIenRepository.findById(id).orElseThrow(()->{
            log.warn("Không tìm thấy sinh viên có id = {} để cập nhật!", id);
            return new ResourceNotFoundException("Không tìm thấy sinh viên có id = " + id);
        });
        if(sinhVienRequest.getTen()!=null && !sinhVienRequest.getTen().isBlank())
            entity.setTen(sinhVienRequest.getTen());
        if(sinhVienRequest.getDtb()!=0)
            entity.setDtb(sinhVienRequest.getDtb());
        if(sinhVienRequest.getTuoi()!=0)
            entity.setTuoi(sinhVienRequest.getTuoi());
        String oldAvatar = entity.getAvatar();
        if(sinhVienRequest.getAvatar()!=null && !sinhVienRequest.getAvatar().isBlank())
            entity.setAvatar(sinhVienRequest.getAvatar());
        SinhVienEntity saved = sinhVIenRepository.save(entity);
        if(oldAvatar!=null && !oldAvatar.isBlank() && !oldAvatar.equals(saved.getAvatar()))
            imageUploadService.deleteImage(oldAvatar);
        log.info("Cập nhật thành công sinh viên id={}", id);
        return SinhVienMapper.toResponse(saved);
    }
    @Transactional
    public void delete(int id){
        log.debug("Xóa sinh viên id={}", id);
        SinhVienEntity entity = sinhVIenRepository.findById(id).orElseThrow(()->{
            log.warn("Xóa thất bại - không tìm thấy sinh viên id={}", id);
            return new ResourceNotFoundException("Không tìm thấy sinh viên với id: " + id);
        });
        String oldAvatar = entity.getAvatar();
        sinhVIenRepository.deleteById(id);
        if(oldAvatar!=null && !oldAvatar.isBlank())
            imageUploadService.deleteImage(oldAvatar);
        log.info("Đã xóa sinh viên id={} thành công", id);
    }
    public PageResponse<SinhVienResponse> search(String name, int page, int size){
        Page<SinhVienEntity> pageData = sinhVIenRepository.findByTenContaining(name, PageRequest.of(page, size));
        List<SinhVienResponse> data = pageData.getContent().stream().map(SinhVienMapper::toResponse).toList();
        Meta meta = new Meta();
        meta.setPage(page);
        meta.setSize(size);
        meta.setTotal(pageData.getTotalElements());
        PageResponse<SinhVienResponse> res = new PageResponse<>();
        res.setData(data);
        res.setMeta(meta);
        return res;
    }

    @Override
    public ApiResponse<DashboardResponse> dashBoard() {
        DashboardResponse response = new DashboardResponse();
        response.setTongSinhVien(sinhVIenRepository.count());
        Double avg = sinhVIenRepository.getAvgSinhVien();
        Double diemCaoNhat = sinhVIenRepository.getDiemCaoNhat();
        response.setDtb((avg==null)?0.0:avg);
        response.setSinhVienGioi(sinhVIenRepository.getSinhVienGioi());
        response.setDiemCaoNhat(diemCaoNhat==null?0.0:diemCaoNhat);
        List<SinhVienEntity> topSV = sinhVIenRepository.findTop5ByOrderByDtbDesc();
        List<TopSinhVienResponse> topSvResponse = topSV.stream().map(SinhVienMapper::toTopSinhVienResponse).toList();
        response.setTopSinhVien(topSvResponse);
        response.setSinhVienKha(sinhVIenRepository.getSinhVienKha());
        response.setSinhVienTrungBinh(sinhVIenRepository.getSinhVienTrungBinh());
        response.setSinhVienYeu(sinhVIenRepository.getSinhVienYeu());
        return new ApiResponse<>(true,"Get dashBoard thành công",response);
    }
}
