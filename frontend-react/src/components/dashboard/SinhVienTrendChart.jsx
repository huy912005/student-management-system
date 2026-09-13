import { CartesianGrid, Line, LineChart, Tooltip, XAxis, YAxis } from "recharts";
import "./SinhVienTrendChart.css";

export const SinhVienTrendChart = ({ trenData }) => {
    console.log("Trend data:", trenData);
    return(
        <div className="chart-card">
            <h2 className="chart-title">Xu hướng sinh viên</h2>
            <div className="chart-wrapper">
                <LineChart width={600} height={300} data={trenData} >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="thang"/>
                    <YAxis dataKey="soLuong"/>
                    <Tooltip/>
                    <Line type="monotone" dataKey="soLuong" stroke="#8884d8" activeDot={{ r: 8 }}/>
                </LineChart>
            </div>
        </div>
    )
}