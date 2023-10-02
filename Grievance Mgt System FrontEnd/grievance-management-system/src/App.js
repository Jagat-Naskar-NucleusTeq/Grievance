import "./App.css";
import Login from "./pages/Login";
import {
  Route,
  Routes,
  useNavigate,
} from "react-router-dom";
import AdminHome from "./pages/AdminHome";
import NewUser from "./pages/NewUser";
import Ticket from "./pages/Ticket";
import AdminSidebar from "./pages/AdminSidebar";
import NewDepartment from "./pages/NewDepartment";
import MemberSidebar from "./pages/MemberSidebar";
import DeleteUser from "./pages/DeleteUser";
import DeleteDepartment from "./pages/DeleteDepartment";
import PrivateRoute from "./pages/PrivateRoute"; 
import Error404Page from "./pages/Error404Page";
import UpdateTicket from "./pages/UpdateTicket";
import AllTicketDetails from "./pages/AllTicketDetails";
import PasswordChange from "./pages/PasswordChange";
import MemberHome from "./pages/MemberHome";
import { useEffect } from "react";

function App() {

  let nevigatee = useNavigate();

  useEffect(() => {
    if (localStorage.getItem("LoggendIn22") === "true") {
      if (localStorage.getItem("Admin_Role") === "member") {
        nevigatee("/member/tickets");
      } else if (localStorage.getItem("Admin_Role") === "admin") {
        nevigatee("/admin/tickets");
      }
    }
  }, [nevigatee]);//made changes here
  
  useEffect(() => {
    const storedRoute = localStorage.getItem('currentRoute');
    if (storedRoute) {
      localStorage.removeItem('currentRoute'); // Remove stored route
      nevigatee(storedRoute); // Navigate to the stored route
    }
  }, [nevigatee]);
  return (
    <div className="App">
      {
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="login" element={<Login />} />
          <Route element={<PrivateRoute />}>
            <Route path="/changePassword" element={<PasswordChange />} />
            <Route path="admin" element={<AdminSidebar />}>
              <Route index element={<AdminHome />} />
              <Route path="adminProfile" element={<AdminHome />} />
              <Route path="addUser" element={<NewUser />} />
              <Route path="allUsers" element={<DeleteUser />} />
              <Route path="viewDept" element={<DeleteDepartment />}>
                <Route path="createDept" element={<NewDepartment />} />
              </Route>
              <Route path="createTicket" element={<Ticket />} />
              <Route path="changePassword" element={<PasswordChange />} />
              <Route path="tickets" element={<AllTicketDetails />} />
              <Route path="update-ticket" element={<UpdateTicket />} />
              <Route
                path="allFeedback"
                element={<div>All Feedback & Greviance Page</div>}
              />
            </Route>
            <Route path="member" element={<MemberSidebar />}>
              <Route index element={<MemberHome />} />
              <Route path="memberProfile" element={<MemberHome />} />
              <Route path="tickets" element={<AllTicketDetails />}></Route>
              <Route path="update-ticket" element={<UpdateTicket />} />
              <Route path="createTicket" element={<Ticket />} />
              <Route path="changePassword" element={<PasswordChange />} />
            </Route>
          </Route>
          <Route path="*" element={<Error404Page />} />
        </Routes>
      }
    </div>
  );
}

export default App;
