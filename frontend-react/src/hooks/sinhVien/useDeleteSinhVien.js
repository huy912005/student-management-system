import { useMutation, useQueryClient } from "@tanstack/react-query"
import { deleteSinhVien } from "../../services/sinhvienService";
import { toast } from "react-toastify";

export const useDeleteSinhVien = (setPage) => {
    const queryClient = useQueryClient();
    return useMutation({
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
}