import { useEffect, useState } from "react";
import AdminLayout from "../layouts/AdminLayout";
import Swal from 'sweetalert2';
import './SinhVienPage.css';
import { FaEdit, FaTrash } from 'react-icons/fa';
import { useForm } from "react-hook-form";
import {z} from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useSinhVienQuery } from "../hooks/sinhVien/useSinhVienQuery";
import { useDeleteSinhVien } from "../hooks/sinhVien/useDeleteSinhVien";
import { useSaveSinhVien } from "../hooks/sinhVien/useSaveSinhVien";
import SkeletonRow from "../components/SkeletonRow";
import { uploadAvatar } from "../services/sinhvienService";

export default function SinhVienPage() {
    const [keyword, setKeyword] = useState("");
    const [page, setPage] = useState(0);
    const [size] = useState(5);
    const [open, setOpen] = useState(false);
    const [editing, setEditing] = useState(null);
    const [avatar, setAvatar] = useState(null);
    const sinhVienSchema = z.object({
        ten: z.string().min(2,"Tên không được để trống"),
        tuoi: z.coerce.number().min(1,"Tuổi phải lớn hơn 0"),
        dtb: z.coerce.number().min(0,"Điểm >= 0").max(10,"Điểm <= 10")
    });
    const {register,reset,handleSubmit,formState:{errors}}=useForm({
        resolver: zodResolver(sinhVienSchema)
    });
    // Debounce keyword để tránh xử lý quá nhiều
    const [debounceKeyword, setDebounceKeyword] = useState(keyword);
    useEffect(()=>{
        const timer = setTimeout(()=>{
            setDebounceKeyword(keyword);
        },500);
        return ()=>clearTimeout(timer);
    },[keyword]);
    const {data : queryData, isLoading, isError, error, refetch} = useSinhVienQuery(debounceKeyword, page, size);
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
    const deleteMutation = useDeleteSinhVien(setPage);
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
    const handleCloseModal = () => {
        setOpen(false);
        reset();
        setEditing(null);
    };
    const saveMutation = useSaveSinhVien(editing, handleCloseModal, setPage);
    const onSubmit = async (data) => {
        console.log(data);
        if(avatar){
            const uploadRes = await uploadAvatar(avatar);
            data.avatar= uploadRes.data;
            console.log(data);
        }
        saveMutation.mutate({ id: editing?.id, data: data });
    };
    if(isError){
       return <p>Có lỗi xảy ra</p>
    }

    const role = localStorage.getItem("role");
    const isAdmin = role === "admin";

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
                    <th>Hình ảnh</th>
                    <th>Tên</th>
                    <th>Tuổi</th>
                    <th>DTB</th>
                    <th>Thực thi</th>
                </tr>
                </thead>
                <tbody>
                    {isLoading ? (
                                <>
                                    <SkeletonRow />
                                    <SkeletonRow />
                                    <SkeletonRow />
                                </>
                        ) : sinhVienList.length > 0 ? (
                            sinhVienList.map((sinhVien) => (
                                <tr key={sinhVien.id}>
                                    <td>{sinhVien.id}</td>
                                    <td>{sinhVien.avatar && <img src={`${import.meta.env.VITE_API_URL}/uploads/${sinhVien.avatar}`} alt = "avatar" className="avatar"/>}</td>
                                    <td>{sinhVien.ten}</td>
                                    <td>{sinhVien.tuoi}</td>
                                    <td>{sinhVien.dtb}</td>
                                    <td style={{ textAlign: "center" }}>
                                        <button onClick={()=>{setEditing(sinhVien); reset({ten: sinhVien.ten,tuoi: sinhVien.tuoi,dtb: sinhVien.dtb}); setOpen(true);}}>
                                            <FaEdit style={{ color: 'blue', fontSize: '18px' }} />
                                        </button>
                                        {isAdmin && (
                                            <button onClick={() => handleDelete(sinhVien.id)} disabled={deleteMutation.isPending }>
                                                <FaTrash style={{ color: 'red', fontSize: '18px' }}/>
                                            </button> 
                                        )}  
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
                                <input placeholder="Tên" {...register('ten')} />
                                {errors.ten &&(<p>{errors.ten.message}</p>)}
                                {/* <input type="number" placeholder="Tuổi" {...register('tuoi',{required:"Tuổi không được trống!",min:{value:1,message:"Tuổi phải lớn hơn 0!"}})}/> */}
                                <input type="number" placeholder="Tuổi" {...register('tuoi')}/>
                                {errors.tuoi &&(<p>{errors.tuoi.message}</p>)}
                                <input type="number" placeholder="Điểm trung bình" {...register('dtb')}/>
                                {errors.dtb &&(<p>{errors.dtb.message}</p>)}
                                <input type="file" accept="image/*" onChange={(e)=>{console.log(e.target.files[0]);setAvatar(e.target.files[0]);}}/>
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