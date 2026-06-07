import api from "../api";

export const login = async (data) => {
    const response= await api.post('/user/auth/login', data);
    return response.data;
}
export const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
}
export const register = async (data) => {
    const response = await api.post('/user', data);
    return response.data;
}