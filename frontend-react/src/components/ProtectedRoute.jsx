import { Navigate } from "react-router-dom";

export default function ProtectedRoute({ children,allowedRoles }) {
    const token = localStorage.getItem('accessToken');
    const userRole = localStorage.getItem('role');
    if(!token || (allowedRoles && !allowedRoles.includes(userRole)))
        return <Navigate to="/" replace />
    return children;
}