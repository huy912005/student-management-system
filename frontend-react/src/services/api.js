import axios from 'axios';
const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: {
    'Content-Type': 'application/json'
  }
});

api.interceptors.request.use(
  (config) => {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) 
      config.headers.Authorization = `Bearer ${accessToken}`;
    return config;
  },
  (error) => {
    console.error("Lỗi gửi request: ", error);
    return Promise.reject(error);
  }
);

api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    console.error("Lỗi phản hồi: ", error);
    if(error?.response?.status===401 &&  !originalRequest._retry){
       originalRequest._retry = true;
       try {
          const refreshToken = localStorage.getItem("refreshToken");
          if (!refreshToken) 
            throw new Error("Không có refresh token");
          const response = await axios.post(`${import.meta.env.VITE_API_URL}/user/auth/refresh`,{refreshToken});
          const newAccessToken = response.data.data.accessToken;
          localStorage.setItem("accessToken",newAccessToken);
          originalRequest.headers.Authorization =`Bearer ${newAccessToken}`;
          return api(originalRequest);
        } catch (refreshError) {
          console.error("Lỗi làm mới token: ", refreshError);
          localStorage.removeItem('accessToken');
          localStorage.removeItem('refreshToken');
          localStorage.removeItem('username');
          window.location.href = '/';
        }
       }
      return Promise.reject(error);
    }
);

export default api;