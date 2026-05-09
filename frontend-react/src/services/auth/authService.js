import api from "../api";

export const login = async (data) => {
    return await api.post('/user/auth/login', data);
}
export const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
}
export const register = async (data) => {
    return await api.post('/user', data);
}