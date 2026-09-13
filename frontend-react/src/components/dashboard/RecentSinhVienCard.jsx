import "./RecentSinhVienCard.css";

export const RecentSinhVienCard = ({ recentSinhViens }) => {
    return (
        <div className="recent-card">
            <h2 className="recent-title">Sinh viên mới nhất</h2>
            <div className="recent-wrapper">
                {recentSinhViens.map((sv) => (
                    <div key={sv.ten + sv.createdAt} className="recent-item">
                        <span className="recent-name">👤 {sv.ten}</span>
                        <span className="recent-date">{new Date(sv.createdAt).toLocaleDateString("vi-VN")}</span>
                    </div>
                ))}
            </div>
        </div>
    );
};