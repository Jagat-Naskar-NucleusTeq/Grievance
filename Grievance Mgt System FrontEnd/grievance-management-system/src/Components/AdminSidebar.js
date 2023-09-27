// import React, { createContext } from "react";
// import { NavLink, Outlet, useNavigate } from "react-router-dom";
// import { Menu } from "antd";
// import "../Components/style/AdminSidebar.css";
// import Header from "../Components/Header";

// import {
//   HomeFilled,
//   DeleteFilled,
//   EyeFilled,
//   UserOutlined,
//   PoweroffOutlined,
//   MessageFilled,
//   PlusCircleFilled,
//   UserAddOutlined,
//   SecurityScanOutlined,
//   UsergroupDeleteOutlined,
// } from "@ant-design/icons";
// import AllTicketDetails from "./AllTicketDetails";

// const AdminRole = createContext();

// function AdminSidebar() {
//   let navigatee = useNavigate();

//   const handleLogout = () => {
//       sessionStorage.clear();
//       navigatee("/");
//   };

//   return (
//     <>

//     {sessionStorage.setItem("Admin_Role", 'admin')}
//     <Header name={sessionStorage.getItem("session_user_name")} role="admin" />
//       <div className="sidebar-parent">
//         <div className="menu">
//           <Menu
//             items={[
//               { label: <NavLink to="adminProfile">Profile</NavLink>, icon: <HomeFilled /> },
//               {
//                 label: <NavLink to="addUser">Add User</NavLink>,
//                 icon: <UserAddOutlined />,
//               },
//               {
//                 label: <NavLink to="allUsers">All Users</NavLink>,
//                 icon: <UsergroupDeleteOutlined />,
//               },
//               {
//                 label: <NavLink to="tickets">Tickets</NavLink>,
//                 icon: <MessageFilled />,
//               },
//               {
//                 label: <NavLink to="createTicket">Create Ticket</NavLink>,
//                 icon: <PlusCircleFilled />,
//               },
//               {
//                 label: <NavLink to="viewDept">Departments</NavLink>,
//                 icon: <DeleteFilled />,
//               },
//               {
//                 label: <NavLink to="changePassword">Change Password</NavLink>,
//                 icon: <SecurityScanOutlined />,
//               },
//               {
//                 label: <span onClick={handleLogout}>LogOut</span>,
//                 icon: <PoweroffOutlined />,
//                 danger: true,
//               },
//             ]}
//           />
//         </div>
//         <div className="admin-content">
//           <Outlet/>
//         </div>
//       </div>
//     </>
//   );
// }

// export default AdminSidebar;

import React, { createContext, useState } from "react";
import { NavLink, Outlet, useNavigate } from "react-router-dom";
import "../Components/style/AdminSidebar1.css"; // Import the CSS file
import Header from "../Components/Header";

function AdminSidebar() {
  let navigatee = useNavigate();
  const [sidebarExpanded, setSidebarExpanded] = useState(true);

  const handleLogout = () => {
    sessionStorage.clear();
    navigatee("/");
  };

  const toggleSidebar = () => {
    setSidebarExpanded(!sidebarExpanded);
  };

  return (
    <>
      {sessionStorage.setItem("Admin_Role", "admin")}
      <Header name={sessionStorage.getItem("session_user_name")} role="admin" />
      <div className="container" style={{ display: "flex" }}>
        <div className={`sidebar ${sidebarExpanded ? "expanded" : ""}`}>
          <div className="AS-menu">
            <NavLink to="adminProfile">
              <div className="AD-li">Profile</div>
            </NavLink>
            <NavLink to="addUser">
              <div className="AD-li">Add User</div>
            </NavLink>
            <NavLink to="allUsers">
              <div className="AD-li">All Users</div>
            </NavLink>
            <NavLink to="tickets">
              <div className="AD-li">Tickets</div>
            </NavLink>
            <NavLink to="createTicket">
              <div className="AD-li">Create Ticket</div>
            </NavLink>
            <NavLink to="viewDept">
              <div className="AD-li">Departments</div>
            </NavLink>
            <NavLink to="changePassword">
              <div className="AD-li">Change Password</div>
            </NavLink>
            <div className="AD-li" onClick={handleLogout}>
              LogOut
            </div>
          </div>
          <div className="arrow" onClick={toggleSidebar}>
            {sidebarExpanded ? <span>&#x2190;</span> : <span>&#x2192;</span>}
          </div>
        </div>
        <div className="admin-content">
          <Outlet />
        </div>
      </div>
    </>
  );
}

export default AdminSidebar;
