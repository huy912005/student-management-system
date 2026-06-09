import api from "../api";

export const login = async (data) => {
    const response= await api.post('/user/auth/login', data);
    return response.data;
}
export const logout = () => {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('username');
}
export const register = async (data) => {
    const response = await api.post('/user', data);
    return response.data;
}
export const refreshToken = async () => {
    const refreshToken = localStorage.getItem('refreshToken');
    if (!refreshToken) throw new Error("No refresh token available");
    const response = await api.post('/user/auth/refresh', { refreshToken });
    return response.data;
}