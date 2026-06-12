import "./TopSinhVienCard.css";
export const TopSinhVienCard = ({ sinhViens }) => {
    const getRankIcon=(index)=>{
        if(index === 0) return "🥇";
        if(index === 1) return "🥈";
        if(index === 2) return "🥉";
        return `${index + 1}`;
    }
    return(
        <div  className="top-student-card">
            <h2 className="top-student-title">Top 5 sinh viên có điểm cao nhất</h2>
            {sinhViens.map((sv,index) => (
                <div key={`${sv.ten}-${sv.dtb}`}>
                    <div className="ranking-item">
                        <div className="ranking-left">
                            <span>{getRankIcon(index)}</span>
                            <span>{sv.ten}</span>
                        </div>
                        <div className="ranking-score">{sv.dtb.toFixed(2)}</div>
                    </div>
                </div>
            ))}
        </div>
    )
}