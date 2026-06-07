import ReactDOM from "react-dom/client";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

// Tạo bộ nhớ cache cho React Query
const queryClient = new QueryClient()

ReactDOM.createRoot(document.getElementById("root")).render(
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <App />
        <ToastContainer/>
      </BrowserRouter>
    </QueryClientProvider>
);