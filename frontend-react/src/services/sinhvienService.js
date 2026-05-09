import api from "./api"

export const getAllSinhVien = async(page, size) => {
    return api.get(`/sinhvien?page=${page}&size=${size}`);
}
export const searchSinhVien = async(keyword, page, size) => {
    return api.get(`/sinhvien/search?name=${keyword}&page=${page}&size=${size}`);
}
export const deleteSinhVien = async(id) => {
    return api.delete(`/sinhvien/${id}`);
}
export const addSinhVien = async(data) => {
    return api.post('/sinhvien', data);
}
export const updateSinhVien = async(id, data) => {
    return api.put(`/sinhvien/${id}`, data);
}