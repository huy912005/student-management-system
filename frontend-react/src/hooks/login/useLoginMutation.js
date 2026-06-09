import { useMutation } from "@tanstack/react-query";
import { login } from "../../services/auth/authService";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export const useLoginMutation=()=>{
    const navigate = useNavigate();
    return useMutation({
        mutationFn:(loginData)=>login(loginData),
        onSuccess:(res) => {
            const data = res;
            if(data.success) {
                toast.success('Đăng nhập thành công');
                localStorage.setItem('accessToken', data.data.accessToken);
                localStorage.setItem('refreshToken', data.data.refreshToken);
                localStorage.setItem('username', data.data.userName);
                navigate('/admin');
            }
        },
        onError:(error) => {
            console.error("Login error", error);
            toast.error('Đăng nhập thất bại');
        }
    })
}