import { FaChartPie, FaUserGraduate } from 'react-icons/fa';
import './AdminLayout.css';
import { NavLink } from "react-router-dom";
export default function AdminLayout({ children }) {
    const username=localStorage.getItem('username');
    return(
        <div className="admin">
            <div className="sidebar">
                <h2 className="sidebar-title">Student Admin</h2>
                <ul>
                    <li>
                        <NavLink to="/admin" end className={({isActive}) => isActive ? "menu-link active" : "menu-link"}>
                            <FaChartPie />
                            <span>Dashboard</span>
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/admin/sinhvien" className={({isActive}) => isActive ? "menu-link active" : "menu-link"}>
                            <FaUserGraduate />
                            <span>Sinh viên</span>
                        </NavLink>
                    </li>
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