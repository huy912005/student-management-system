import api from "./api"

export const getDashboard = async () => {
    const response = await api.get('/sinhvien/dashboard');
    return response.data;
}