import "./App.css";
import Login from "./Components/CommonComponents/Login";
import {
  Route,
  Routes,
  useNavigate,
} from "react-router-dom";
import AdminHome from "./Components/AdminHome";
import NewUser from "../src/Components/NewUser";
import Ticket from "./Components/Ticket";
import AdminSidebar from "./Components/AdminSidebar";
import NewDepartment from "./Components/NewDepartment";
import MemberSidebar from "./Components/MemberSidebar";
import DeleteUser from "./Components/DeleteUser";
import DeleteDepartment from "./Components/DeleteDepartment";
import PrivateRoute from "../src/Components/PrivateRoute"; //
import Error404Page from "./Components/Error404Page";
import UpdateTicket from "./Components/UpdateTicket";
import AllTicketDetails from "./Components/AllTicketDetails";
import PasswordChange from "./Components/PasswordChange";
import MemberHome from "./Components/MemberHome";
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
  }, []);
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
