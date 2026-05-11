import api from "./api"

export const getAllSinhVien = async(page, size) => {
    const response = await api.get(`/sinhvien?page=${page}&size=${size}`)
    return response.data;
}
export const searchSinhVien = async(keyword, page, size) => {
    const response = await api.get(`/sinhvien/search?name=${keyword}&page=${page}&size=${size}`);
    return response.data;
}
export const deleteSinhVien = async(id) => {
    const response = await api.delete(`/sinhvien/${id}`);
    return response.data;
}
export const addSinhVien = async(data) => {
    const response = await api.post(`/sinhvien`, data);
    return response.data;
}
export const updateSinhVien = async(id,data)=>{
    const response = await api.put(`/sinhvien/${id}`,data)
    return response.data;
}