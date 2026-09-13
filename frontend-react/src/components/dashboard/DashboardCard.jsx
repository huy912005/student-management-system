import "./DashboardCard.css";
export const DashboardCard=({title,value,icon,color})=>{
    return(
         <div className="dashboard-card">
            <div className="card-header">
                <div className="card-icon-wrapper" style={{ backgroundColor: color }}>{icon}</div>
                <span className="card-title">{title}</span>
            </div>
            <div className="card-value">{value}</div>
        </div>
    )
}