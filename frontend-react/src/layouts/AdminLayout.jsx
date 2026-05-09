import './AdminLayout.css';
import { Link } from "react-router-dom";
export default function AdminLayout({ children }) {
    const username=localStorage.getItem('username');
    return(
        <div className="admin">
            <div className="sidebar">
                <h2>Admin Sidebar</h2>
                <ul>
                    <li><Link to="/admin">Dashboard</Link></li>
                    <li><Link to="/admin/sinhvien">Sinh viên</Link></li>
                </ul>
            </div>
            <div className="main">
                <div className="header">
                    xin chào {username}
                </div>
                <div className="content">
                    {children}
                </div>
            </div>
        </div>
    )
}