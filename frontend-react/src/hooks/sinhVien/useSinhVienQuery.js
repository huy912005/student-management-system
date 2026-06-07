import { useQuery } from "@tanstack/react-query";
import { getAllSinhVien, searchSinhVien } from "../../services/sinhvienService";

export const useSinhVienQuery=(keyword, page, size) => {
    return useQuery({
        queryKey:['sinhvien', keyword, page, size],
        queryFn:async () => {
            //gọi Api dựa vào có tìm kiếm hay không
            if(keyword.trim()) 
                return await searchSinhVien(keyword, page, size);
            else 
            {
                // await new Promise(resolve =>
                //     setTimeout(resolve, 3000)
                // );
                return await getAllSinhVien(page, size);
            }
        }
    })
}
