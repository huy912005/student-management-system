import { useEffect, useState } from "react";
import AdminLayout from "../layouts/AdminLayout";
import { addSinhVien, deleteSinhVien, getAllSinhVien, searchSinhVien, updateSinhVien } from "../services/sinhvienService";
import Swal from 'sweetalert2';
import './SinhVienPage.css';
import { FaEdit, FaTrash } from 'react-icons/fa';

export default function SinhVienPage() {
    const[list,setList]=useState([]);
    const [keyword, setKeyword] = useState("");
    const [page, setPage] = useState(0);
    const [size] = useState(5);
    const [totalPages, setTotalPages] = useState(0);
    const [loading, setLoading] = useState(false);
    const [open, setOpen] = useState(false);
    const [editing, setEditing] = useState(null);
    const [form, setForm] = useState({
        ten: '',
        tuoi: '',
        dtb: ''
    });
    useEffect(()=>{
        const fetchSinhVien = async () => {
            try {
                setLoading(true);
                let response;
                if (keyword.trim()) {
                    response = await searchSinhVien(keyword, page, size);
                } else {
                    response = await getAllSinhVien(page, size);
                }
                console.log("Fetched sinh viên data:", response.data);
                const total = response.data.data.meta.total;
                const totalPages = Math.ceil(total / size);
                console.log("Total:", total, "Pages:", totalPages);
                setList(response.data.data.data);
                setTotalPages(totalPages);
            } catch (error) {
                console.error('Error fetching sinh viên:', error);
            } finally {
                setLoading(false);
            }
        };
        fetchSinhVien();
    },[keyword,page,size]);
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
    const validateForm = () => {
        const errors = [];
        if (!form.ten.trim()) 
            errors.push('Tên không được để trống');
        if (!form.tuoi || isNaN(form.tuoi) || form.tuoi <= 0) 
            errors.push('Tuổi > 0');
        if (!form.dtb || isNaN(form.dtb) || form.dtb < 0 || form.dtb > 10) 
            errors.push('Điểm từ 0-10');
        if (errors.length > 0) {
            Swal.fire('Lỗi!', errors.join('\n'), 'error');
            return false;
        }
        return true;
    }

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
            try {
                await deleteSinhVien(id);
                setList(list.filter(sv => sv.id !== id));
                Swal.fire(
                    'Đã xóa!',
                    'Sinh viên đã được xóa khỏi hệ thống.',
                    'success'
                );
                setPage(0);
            } catch (error) {
                console.error(error);
                Swal.fire(
                    'Lỗi!',
                    'Đã xảy ra lỗi khi xóa sinh viên.',
                    'error'
                );
            }
        }
    };
   const handleSubmit = async (e) => {
        e.preventDefault();
        if (!validateForm()) 
            return;
        setLoading(true); 
        try {
            if (editing) {
                const res = await updateSinhVien(editing.id, form);
                const updated = res.data.data;
                setList(list.map(sv => sv.id === editing.id ? updated : sv));
                Swal.fire('Thành công!', 'Đã cập nhật thông tin sinh viên.', 'success');
            } else {
                const res = await addSinhVien(form);
                const newItem = res.data.data;
                setList([newItem, ...list]); // thêm vào đầu
                Swal.fire('Thành công!', 'Đã thêm sinh viên mới.', 'success');
            }
            setOpen(false);
            setForm({ ten: '', tuoi: '', dtb: '' }); 
            setEditing(null);
            setPage(0);
        } catch (error) {
            console.error(error);
            Swal.fire('Lỗi!', 'Có lỗi xảy ra, vui lòng thử lại.', 'error');
        }
         finally {
            setLoading(false); 
        }
    };
    const handleCloseModal = () => {
        setOpen(false);
        setForm({ ten: '', tuoi: '', dtb: '' });
        setEditing(null);
    };
    
    return (
        <AdminLayout>
            <h2>Quản lý sinh viên</h2>
            <div style={{ marginBottom: "20px" }}>
                <input placeholder="Tìm tên sinh viên..." value={keyword} onChange={handleSearch} style={{ padding: "8px", width: "200px" }}/>
                <button className="btnThemSV" onClick={()=>{setEditing(null); setForm({ ten: '', tuoi: '', dtb: '' }); setOpen(true);}}>Thêm sinh viên</button>
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
                    {loading ? (
                            <tr>
                                <td colSpan="4" style={{ textAlign: "center" }}>Đang tải...</td>
                            </tr>
                        ) : list.length > 0 ? (
                            list.map((sinhVien) => (
                                <tr key={sinhVien.id}>
                                    <td>{sinhVien.id}</td>
                                    <td>{sinhVien.ten}</td>
                                    <td>{sinhVien.tuoi}</td>
                                    <td>{sinhVien.dtb}</td>
                                    <td>
                                        <button onClick={()=>{setEditing(sinhVien); setForm({ ten: sinhVien.ten, tuoi: sinhVien.tuoi, dtb: sinhVien.dtb }); setOpen(true);}}>
                                            <FaEdit style={{ color: 'blue', fontSize: '18px' }} />
                                        </button>
                                        <button onClick={() => handleDelete(sinhVien.id)}>
                                            <FaTrash style={{ color: 'red', fontSize: '18px' }} />
                                        </button>   
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="4" style={{ textAlign: "center" }}>Không có dữ liệu</td>
                            </tr>
                        )
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
                            <form onSubmit={handleSubmit}>
                                <input placeholder="Tên" value={form.ten} onChange={(e) => setForm({ ...form, ten: e.target.value })} required/>
                                <input placeholder="Tuổi" value={form.tuoi} onChange={(e) => setForm({ ...form, tuoi: e.target.value })} required/>
                                <input placeholder="Điểm trung bình" value={form.dtb} onChange={(e) => setForm({ ...form, dtb: e.target.value })} required/>
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