import { useState } from "react";
import AdminLayout from "../layouts/AdminLayout";
import { addSinhVien, deleteSinhVien, getAllSinhVien, searchSinhVien, updateSinhVien } from "../services/sinhvienService";
import Swal from 'sweetalert2';
import './SinhVienPage.css';
import { FaEdit, FaTrash } from 'react-icons/fa';
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";

export default function SinhVienPage() {
    const [keyword, setKeyword] = useState("");
    const [page, setPage] = useState(0);
    const [size] = useState(5);
    const [open, setOpen] = useState(false);
    const [editing, setEditing] = useState(null);
    const queryClient = useQueryClient(); // khai báo  bộ nhớ cache
    const {register,reset,handleSubmit,formState:{errors}}=useForm({});
    const {data : queryData, isLoading, isError, error, refetch} = useQuery({
        queryKey:['sinhvien', keyword, page, size],
        queryFn:async () => {
            //gọi Api dựa vào có tìm kiếm hay không
            if(keyword.trim()) 
                return await searchSinhVien(keyword, page, size);
            else 
                return await getAllSinhVien(page, size);
        }
    });
    const sinhVienList = queryData?.data?.data || []; 
    const totalCount = queryData?.data?.meta?.total || 0;
    const totalPages = Math.ceil(totalCount / size);
    const handlePrevPage=() => {
        if(page>0)
            setPage(page-1);
    }
    const handleNextPage=() => {
        if(page < totalPages - 1)
            setPage(page + 1);
    }
    const handleSearch=(e) => {
        setKeyword(e.target.value);
        setPage(0); 
    }
    const deleteMutation = useMutation({
        mutationFn:(id)=>deleteSinhVien(id),
        onSuccess:()=>{
            queryClient.invalidateQueries({queryKey:[`sinhvien`]});// xóa cache để tự động refetch
            toast.success('Xóa sinh viên thành công');
            setPage(0);
        },
        onError:()=>{
            toast.error('Xóa sinh viên thất bại');
        }
    });
    const handleDelete = async (id) => {
        const result = await Swal.fire({
            title: 'Bạn có chắc chắn?',
            text: "Bạn sẽ không thể hoàn tác hành động này!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6', 
            confirmButtonText: 'Xóa',
            cancelButtonText: 'Hủy'
        });
        if (result.isConfirmed) {
            deleteMutation.mutate(id);
        }
    };
    const saveMutation = useMutation({
        mutationFn: async({id, data}) => {
            return id? await updateSinhVien(id, data) : await addSinhVien(data);
        },
        onSuccess:()=>{
            queryClient.invalidateQueries({queryKey:[`sinhvien`]});//cache cũ rồi nên xóa đi để tự động gọi lại api lấy dữ liệu mới
            toast.success(editing ? 'Đã cập nhật thông tin sinh viên.' : 'Đã thêm sinh viên mới.');
            handleCloseModal();
            setPage(0);
        },
        onError: () => {
            toast.error(editing ? 'Cập nhật sinh viên thất bại' : 'Thêm sinh viên thất bại');
        }
    });
   const onSubmit = (data) => {
        saveMutation.mutate({ id: editing?.id, data: data });
    };
    const handleCloseModal = () => {
        setOpen(false);
        reset();
        setEditing(null);
    };
    if(isError){
       return <p>Có lỗi xảy ra</p>
    }
    
    return (
        <AdminLayout>
            <h2>Quản lý sinh viên</h2>
            <div style={{ marginBottom: "20px" }}>
                <input placeholder="Tìm tên sinh viên..." value={keyword} onChange={handleSearch} style={{ padding: "8px", width: "200px" }}/>
                <button className="btnThemSV" onClick={()=>{setEditing(null); reset({ ten: '', tuoi: '', dtb: '' }); setOpen(true);}}>Thêm sinh viên</button>
            </div>
            <table border="1" cellPadding="10">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên</th>
                    <th>Tuổi</th>
                    <th>DTB</th>
                    <th>Thực thi</th>
                </tr>
                </thead>
                <tbody>
                    {isLoading ? (
                            <tr>
                                <td colSpan="5" style={{ textAlign: "center" }}>Đang tải...</td>
                            </tr>
                        ) : sinhVienList.length > 0 ? (
                            sinhVienList.map((sinhVien) => (
                                <tr key={sinhVien.id}>
                                    <td>{sinhVien.id}</td>
                                    <td>{sinhVien.ten}</td>
                                    <td>{sinhVien.tuoi}</td>
                                    <td>{sinhVien.dtb}</td>
                                    <td>
                                        <button onClick={()=>{setEditing(sinhVien); reset({ten: sinhVien.ten,tuoi: sinhVien.tuoi,dtb: sinhVien.dtb}); setOpen(true);}}>
                                            <FaEdit style={{ color: 'blue', fontSize: '18px' }} />
                                        </button>
                                        <button onClick={() => handleDelete(sinhVien.id)} disabled={deleteMutation.isPending}>
                                            <FaTrash style={{ color: 'red', fontSize: '18px' }} />
                                        </button>   
                                    </td>
                                </tr>
                            ))
                        ) : 
                        (<tr><td colSpan="4" style={{ textAlign: "center" }}>Không có dữ liệu</td></tr>)
                    }
                </tbody>
            </table>
            <div style={{ marginTop: "20px" }}>
                <button onClick={handlePrevPage} disabled={page === 0}>
                    Trang trước
                </button>
                <span style={{ margin: "0 15px" }}>
                    Trang {page + 1} / {totalPages}
                </span>
                <button onClick={handleNextPage} disabled={page + 1 >= totalPages || totalPages === 0}>
                    Trang sau
                </button>
            </div>
            {
                open &&(
                    <div className="modal">
                        <div className="modal-content">
                            <h2>{editing ? "Sửa sinh viên" : "Thêm sinh viên"}</h2>
                            <form onSubmit={handleSubmit(onSubmit)}>
                                {/* <input placeholder="Tên" value={form.ten} onChange={(e) => setForm({ ...form, ten: e.target.value })} required/> */}
                                <input placeholder="Tên" {...register('ten', { required: "Tên không được trống!" })} />
                                {errors.ten &&(<p>{errors.ten.message}</p>)}
                                <input type="number" placeholder="Tuổi" {...register('tuoi',{required:"Tuổi không được trống!",min:{value:1,message:"Tuổi phải lớn hơn 0!"}})}/>
                                {errors.tuoi &&(<p>{errors.tuoi.message}</p>)}
                                <input type="number" placeholder="Điểm trung bình" {...register('dtb',{required:"Điểm không được trống!",min:{value:0,message:"Điểm phải >= 0"},max:{value:10,message:"Điểm phải <= 10"}})}/>
                                {errors.dtb &&(<p>{errors.dtb.message}</p>)}
                                <div style={{ display: 'flex', gap: '10px' }}>
                                    <button type="submit">{editing ? "Chỉnh sửa" : "Thêm"}</button>
                                    <button type="button" onClick={handleCloseModal}>Hủy</button>
                                </div>
                            </form>
                        </div>
                    </div>
                )
            }
        </AdminLayout>
    );
}