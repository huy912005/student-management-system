import { useState } from "react";
import './LoginPage.css';
import { useLoginMutation } from "../hooks/login/useLoginMutation";
import z from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";

export default function LoginPage() {
    const [form, setForm] = useState({
        username:'',
        password:''
    });
    const loginSchema = z.object({
        username: z.string().min(1, "Username không được để trống"),
        password: z.string().min(1, "Password không được để trống")
    })
    const {register,handleSubmit,reset,formState:{errors}} = useForm({
        resolver:zodResolver(loginSchema)
    })
    const loginMutation = useLoginMutation();
    const handleChange=(e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        })
    }
    const handleLogin = async(e)=>{
        e.preventDefault();
        await loginMutation.mutateAsync(form);
    }
    return (
       <div className="container">
            <div className="card">
                <h2 className="title">Login</h2>
                <form onSubmit={handleLogin}>
                    <input type="text" {...register("username")} placeholder="Username *" onChange={handleChange} className="input"/>
                    {errors.username && <p className="error">{errors.username.message}</p>}
                    <input type="password" {...register("password")} placeholder="Password *" onChange={handleChange} className="input"/>
                    {errors.password && <p className="error">{errors.password.message}</p>}
                    <button type="submit" disabled={!form.username || !form.password} className="button">Login</button>
                    <p style={{textAlign:"center"}}>Chưa có tài khoản? <a href="/user">Đăng ký ngay</a></p>
                </form>
            </div>
        </div>
    )
}