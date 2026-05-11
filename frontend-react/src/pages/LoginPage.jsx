import { useState } from "react";
import { login } from "../services/auth/authService";
import './LoginPage.css';
import { useNavigate } from "react-router-dom";
import { useMutation } from "@tanstack/react-query";
import Swal from "sweetalert2";
import { toast } from "react-toastify";

export default function LoginPage() {
    const navigate = useNavigate();
    const [form, setForm] = useState({
        username:'',
        password:''
    });
    const loginMutation = useMutation({
        mutationFn:(loginData)=>login(loginData),
        onSuccess:(res) => {
            const data = res;
            if(data.success) {
                toast.success('Đăng nhập thành công');
                localStorage.setItem('token', data.data.token);
                localStorage.setItem('username', data.data.username);
                navigate('/admin');
            }
            else
                Swal.fire('Thất bại', data.message || 'Sai tài khoản hoặc mật khẩu', 'error');
        },
        onError:(error) => {
            console.error("Login error", error);
            toast.error('Đăng nhập thất bại');
        }
    })
    const handleChange=(e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        })
    }
    const handleLogin = async(e)=>{
        e.preventDefault();
        loginMutation.mutate(form);
    }
    return (
       <div className="container">
            <div className="card">
                <h2 className="title">Login</h2>
                <form onSubmit={handleLogin}>
                    <input type="text" name="username" placeholder="Username" value={form.username} onChange={handleChange} className="input"/>
                    <input type="password" name="password" placeholder="Password" value={form.password} onChange={handleChange} className="input"/>
                    <button type="submit" disabled={!form.username || !form.password} className="button">Login</button>
                    <p style={{textAlign:"center"}}>Chưa có tài khoản? <a href="/user">Đăng ký ngay</a></p>
                </form>
            </div>
        </div>
    )
}