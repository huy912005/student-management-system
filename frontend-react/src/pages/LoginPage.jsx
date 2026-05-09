import { useState } from "react";
import { login } from "../services/auth/authService";
import './LoginPage.css';
import { useNavigate } from "react-router-dom";

export default function LoginPage() {
    const navigate = useNavigate();
    const [form, setForm] = useState({
        username:'',
        password:''
    });
    const handleChange=(e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        })
    }
    const handleLogin = async(e)=>{
        e.preventDefault();
        try {
            const res = await login(form);
            console.log("Full response:", JSON.stringify(res.data, null, 2));
            
            // Fix: Response là string chứa 2 JSON ghép lại, lấy JSON đầu tiên
            let data = res.data;
            if (typeof data === 'string') {
                try {
                    const jsonMatch = data.match(/\{[^{}]*(?:\{[^{}]*\}[^{}]*)*\}/g);
                    if (jsonMatch && jsonMatch[0]) {
                        data = JSON.parse(jsonMatch[0]);
                    }
                } catch (e) {
                    console.error("Failed to parse response:", e);
                }
            }
            
            if (data.success) {
                localStorage.setItem('token', data.data.token);
                localStorage.setItem('username', data.data.username);
                navigate('/admin');
            } else {
                console.error("Login failed:", data.message);
            }
        } catch (error) {
            console.error("Login error", error);
        }
    }
    return (
       <div className="container">
            <div className="card">
                <h2 className="title">Login</h2>
                <input type="text" name="username" placeholder="Username" value={form.username} onChange={handleChange} className="input"/>
                <input type="password" name="password" placeholder="Password" value={form.password} onChange={handleChange} className="input"/>
                <button onClick={handleLogin} disabled={!form.username || !form.password} className="button">Login</button>
                <p style={{textAlign:"center"}}>Chưa có tài khoản? <a href="/user">Đăng ký ngay</a></p>
            </div>
        </div>
    )
}