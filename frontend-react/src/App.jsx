  import { Route, Routes } from 'react-router-dom'
  import './App.css'
  import LoginPage from './pages/LoginPage'
  import AdminPage from './pages/AdminPage'
  import SinhVienPage from "./pages/SinhVienPage";
import ProtectedRoute from './components/ProtectedRoute';
  function App() {
    return (
      <Routes>
        <Route path='/' element={<LoginPage/>}/>
        <Route path='/admin' element={<ProtectedRoute allowedRoles={["admin"]}><AdminPage/></ProtectedRoute>}/>
        <Route path="/admin/sinhvien" element={<ProtectedRoute allowedRoles={["admin"]}><SinhVienPage/></ProtectedRoute>} />
      </Routes>
    )
  }

  export default App
