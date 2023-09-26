import "./App.css";
import Login from "./Components/CommonComponents/Login";
import {
  Route,
  createBrowserRouter,
  createRoutesFromElements,
  RouterProvider,
  Routes,
  BrowserRouter,
  Router,
  Navigate,
} from "react-router-dom";
import AdminHome from "./Components/AdminHome";
import NewUser from "../src/Components/NewUser";
import Ticket from "./Components/Ticket";
import AdminSidebar from "./Components/AdminSidebar";
import NewDepartment from "./Components/NewDepartment";
import MemberSidebar from "./Components/MemberSidebar";
import DeleteUser from "./Components/DeleteUser";
import AllDepartment from "./Components/AllDepartment";
import DeleteDepartment from "./Components/DeleteDepartment";
import AllUsers from "./Components/AllUsers";

import PrivateRoute from "../src/Components/PrivateRoute"; //
// import { useAuth } from '../src/Components/AuthContext';
import { Switch } from "antd";
import Error404Page from "./Components/Error404Page";
import UpdateTicket from "./Components/UpdateTicket";
import AllTicketDetails from "./Components/AllTicketDetails";
import PasswordChange from "./Components/PasswordChange";
import MemberHome from "./Components/MemberHome";

function App() {
  // const { isLoggedIn } = useAuth();
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
              <Route path="viewDept" element={<DeleteDepartment />} >
                <Route path="createDept" element={<NewDepartment />} />
              </Route>
              <Route path="createTicket" element={<Ticket />} />
              <Route path="changePassword" element={<PasswordChange />} />
              <Route path="tickets" element={<AllTicketDetails />} />
              <Route path="update-ticket" element={<UpdateTicket />}/>
              <Route
                path="allFeedback"
                element={<div>All Feedback & Greviance Page</div>}
              />
            </Route>
            <Route path="member" element={<MemberSidebar />}>
              <Route index element={<MemberHome/>} />
              <Route path="memberProfile" element={<MemberHome/>} />
              <Route path="tickets" element={<AllTicketDetails />}>
               
              </Route>
              <Route path="update-ticket" element={<UpdateTicket />}/>
              <Route path="createTicket" element={<Ticket />} />
              <Route path="changePassword" element={<PasswordChange />} />
            </Route>
          </Route>
          <Route path="*" element={<Error404Page />} />
        </Routes>

        /* <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/admin" element={<AdminSidebar />}>
          <Route path="adminHome" element={<AdminHome />}/>
          <Route path="addUser" element={<NewUser />} />
          <Route path="allUsers" element={<AllUsers />} />
          <Route path="deleteUser" element={<DeleteUser />} />
          <Route path="createDept" element={<NewDepartment />} />
          <Route path="viewDept" element={<AllDepartment />} />
          <Route path="deleteDept" element={<DeleteDepartment />} />
          <Route path="createTicket" element={<Ticket/>} />
          <Route
            path="allFeedback"
            element={<div>All Feedback & Greviance Page</div>}
          />
        </Route>
        <Route path="/member" element={<MemberSidebar />}>
          <Route path="memberHome" element={<div>Member Home Page</div>} />
          <Route path="" element={<div></div>} />
          <Route path="" element={<div></div>} />
          <Route path="myTicket" element={<div>My Ticket Page</div>} />
          <Route path="myDeptTicket" element={<div>My Department Ticket Page</div>} />
          <Route path="" element={<div></div>}/>
          <Route path="createTicket" element={<Ticket/>} />
          <Route path="" element={<div></div>} />
          <Route path="" element={<div></div>} />
        </Route>
      </Routes> */
      }

      {/* <RouterProvider router={routtee} />  */}
      {/* <Practise/> */}
      {/* <Ticket/> 
       <NewUser/>
       <NewDepartment/> 
       <Login/> 
       <AdminSidebar/> 
       <MemberSidebar/>
       <Error404Page/> */}
      {/* <UpdateTicket/> */}
      {/* <AllTicketDetails/> */}

      {/* <Ticket/>
       <PasswordChange/>
       <Login/> */}

      {/* <UpdateTicket/>
       <PasswordChange/> */}

      {/* <Routes>
        <Route path="/" element={<AllTicketDetails />} />
        <Route path="/update-ticket/:ticketId" element={<UpdateTicket />} />
      </Routes> */}
    </div>
  );
}

export default App;
