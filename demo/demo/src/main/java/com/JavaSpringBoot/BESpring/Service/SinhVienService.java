package com.JavaSpringBoot.BESpring.Service;

import com.JavaSpringBoot.BESpring.DTO.Request.SinhVienRequest;
import com.JavaSpringBoot.BESpring.DTO.Response.SinhVienResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.UserResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.page.Meta;
import com.JavaSpringBoot.BESpring.DTO.Response.page.PageResponse;
import com.JavaSpringBoot.BESpring.Entity.SinhVienEntity;
import com.JavaSpringBoot.BESpring.Repository.SinhVIenRepository;
import com.JavaSpringBoot.BESpring.converter.SinhVienMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinhVienService {
    @Autowired
    private SinhVIenRepository sinhVIenRepository;
    public PageResponse<SinhVienResponse> getAll(int page, int size) {

        // BƯỚC 1: LẤY DỮ LIỆU TỪ DATABASE (Đã phân trang)
        // PageRequest.of(page, size) sẽ tự động tạo ra câu lệnh SQL có LIMIT và OFFSET.
        // Ví dụ: lấy page = 0, size = 5 -> Sinh ra SQL: SELECT * FROM sinh_vien LIMIT 5 OFFSET 0
        // Biến pageData lúc này chứa cả Data (danh sách sinh viên) lẫn các thông tin phụ (tổng số trang, tổng số record...)
        Page<SinhVienEntity> pageData = sinhVIenRepository.findAll(PageRequest.of(page, size, Sort.by("ten").ascending()));

        // BƯỚC 2: BIẾN ĐỔI ENTITY SANG DTO (Tránh lộ DB ra ngoài)
        // - pageData.getContent(): Moi cái danh sách SinhVienEntity từ trong pageData ra.
        // - .stream().map(...): Giống như một cái băng chuyền trong nhà máy. Đưa từng SinhVienEntity qua máy ép (SinhVienMapper::toResponse) để biến nó thành SinhVienResponse.
        // - .toList(): Gom tất cả các SinhVienResponse vừa ép xong lại thành một List mới.
        List<SinhVienResponse> data = pageData.getContent()
                .stream()
                .map(SinhVienMapper::toResponse)
                .toList();

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
    public SinhVienResponse save(SinhVienRequest sinhVienRequest){
        if(sinhVienRequest.getTen()==null ||sinhVienRequest.getTen()==""){
            throw new IllegalArgumentException("Tên không được trống!");
        }
        SinhVienEntity entity = SinhVienMapper.toEntity(sinhVienRequest);
        SinhVienEntity saved = sinhVIenRepository.save(entity);
        return SinhVienMapper.toResponse(saved);
    }

    public SinhVienResponse update(int id,SinhVienRequest sinhVienRequest){
        SinhVienEntity  entity = sinhVIenRepository.findById(id).orElseThrow(()->new RuntimeException("Không tìm thấy"));
        if(sinhVienRequest.getTen()!=null)
            entity.setTen(sinhVienRequest.getTen());
        if(sinhVienRequest.getDtb()!=0)
            entity.setDtb(sinhVienRequest.getDtb());
        if(sinhVienRequest.getTuoi()!=0)
            entity.setTuoi(sinhVienRequest.getTuoi());
        entity = sinhVIenRepository.save(entity);
        return SinhVienMapper.toResponse(sinhVIenRepository.save(entity));
    }

    public void delete(int id){
        sinhVIenRepository.deleteById(id);
    }
    public PageResponse<SinhVienResponse> search(String name, int page, int size){
        Page<SinhVienEntity> pageData = sinhVIenRepository.findByTenContaining(name, PageRequest.of(page, size));

        List<SinhVienResponse> data = pageData.getContent()
                .stream()
                .map(SinhVienMapper::toResponse)
                .toList();

        Meta meta = new Meta();
        meta.setPage(page);
        meta.setSize(size);
        meta.setTotal(pageData.getTotalElements());

        PageResponse<SinhVienResponse> res = new PageResponse<>();
        res.setData(data);
        res.setMeta(meta);

        return res;
    }
}
