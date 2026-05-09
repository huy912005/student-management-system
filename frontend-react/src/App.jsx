  import { Route, Routes } from 'react-router-dom'
  import './App.css'
  import LoginPage from './pages/LoginPage'
  import AdminPage from './pages/AdminPage'
  import SinhVienPage from "./pages/SinhVienPage";
  function App() {

    return (
      <Routes>
        <Route path='/' element={<LoginPage/>}/>
        <Route path='/admin' element={<AdminPage/>}/>
        <Route path="/admin/sinhvien" element={<SinhVienPage />} />
      </Routes>
    )
  }

  export default App
