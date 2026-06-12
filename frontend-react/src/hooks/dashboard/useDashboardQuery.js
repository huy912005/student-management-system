import { useQuery } from "@tanstack/react-query"
import { getDashboard } from "../../services/dashboardService";

export const useDashboardQuery = ()=>{
    return useQuery({
        queryKey:['dashboard'],
        queryFn: async()=>{
            return await getDashboard();
        }
    })
}