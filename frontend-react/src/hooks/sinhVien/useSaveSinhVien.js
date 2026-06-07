import { useMutation, useQueryClient } from "@tanstack/react-query";
import { addSinhVien, updateSinhVien } from "../../services/sinhvienService";
import { toast } from "react-toastify";

export const useSaveSinhVien = (editing,handleCloseModal,setPage) => {
    const queryClient = useQueryClient();
    return useMutation({
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
    })
}