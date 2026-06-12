import { FaChartLine, FaStar, FaTrophy, FaUserGraduate } from "react-icons/fa";
import { DashboardCard } from "../components/dashboard/DashboardCard";
import { useDashboardQuery } from "../hooks/dashboard/useDashboardQuery";
import AdminLayout from "../layouts/AdminLayout";
import "./AdminPage.css";
import { TopSinhVienCard } from "../components/dashboard/TopSinhVienCard";
import { HocLucChart } from "../components/dashboard/HocLucChart";

export default function AdminPage() {
    const { data, isLoading, error } = useDashboardQuery();
    const dashboard = data?.data ;
    const diemTrungBinh =dashboard?.dtb?.toFixed(2) ?? "0";
    console.log("Dashboard data:", dashboard);
    const cards = dashboard ? [
        {
            title: "Tổng số sinh viên",
            value: dashboard.tongSinhVien,
            icon:<FaUserGraduate/>,
            color: "#3b82f6"
        },
        {
            title: "Điểm trung bình",
            value: diemTrungBinh,
            icon:<FaChartLine/>,
            color: "#8b5cf6"
        },
        {
            title: "Điểm cao nhất",
            value: dashboard.diemCaoNhat,
            icon:<FaTrophy/>,
             color: "#f59e0b"
        },
        {
            title: "Sinh viên giỏi",
            value: dashboard.sinhVienGioi,
            icon:<FaStar/>,
            color: "#22c55e"
        }
    ] : [];
    const top5SV = dashboard?.topSinhVien || [];
    return (
        <AdminLayout>
            <h1>Admin Dashboard</h1>
            <div>
                {isLoading ?(
                    <p>Đang tải dữ liệu...</p>
                ):dashboard?(
                    <div className="dashboard-grid">
                        {cards.map((card) => (
                            <DashboardCard key={card.title} icon={card.icon} title={card.title} value={card.value} color={card.color}/>
                        ))}
                    </div>
                ):(
                    <p>Không có dữ liệu</p>
                )}
            </div>
            <div>
                {isLoading ? (
                    <p>Đang tải dữ liệu...</p>
                ) : top5SV.length > 0 ? (
                    <TopSinhVienCard sinhViens={top5SV}/>
                ) : (
                    <p>Không có dữ liệu</p>
                )}
            </div>
            <HocLucChart dashboard={dashboard}/>
        </AdminLayout>
    )
}