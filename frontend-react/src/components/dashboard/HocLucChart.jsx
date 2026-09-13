import { Cell, Pie, PieChart, Tooltip } from "recharts";
import "./HocLucChart.css";
export const HocLucChart = ({ dashboard }) => {
    const data = [
        {
            name:"Giỏi",
            value: dashboard?.sinhVienGioi || 0
        },
        {
            name:"Khá",
            value: dashboard?.sinhVienKha || 0
        },
        {
            name:"Trung bình",
            value: dashboard?.sinhVienTrungBinh || 0
        },
        {
            name:"Yếu",
            value: dashboard?.sinhVienYeu || 0
        }
    ];
    console.log("HocLucChart data:", data);
    const COLORS = [
        "#22c55e",
        "#3b82f6",
        "#f59e0b",
        "#ef4444"
    ];
    return(
        <div>
            <div className="hocluc-card">
                <h2 className="hocluc-title">Biểu đồ học lực</h2>
                <div className="hocluc-wrapper">
                    <PieChart width={400} height={300}> 
                        <Pie data={data} dataKey="value" nameKey="name" cx="50%" cy="50%" outerRadius={100}>
                            {data.map((entry, index) =>(
                                <Cell key={index} fill={COLORS[index]}/>
                            ))}
                        </Pie>
                        <Tooltip/>
                    </PieChart>
                </div>
            </div>
        </div>
    )
}