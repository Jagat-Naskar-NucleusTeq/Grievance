// import React from "react";
// import { Link, Outlet, useNavigate } from "react-router-dom";
// import { Menu } from "antd";
// import "../Components/style/AdminSidebar.css";
// import Header from "../Components/Header";

// import {
//   HomeFilled,
//   DeleteFilled,
//   EyeFilled,
//   MehFilled,
//   PoweroffOutlined,
//   MessageFilled,
//   PlusCircleFilled,
//   FileAddFilled,
//   SecurityScanOutlined,
// } from "@ant-design/icons";



// function MemberSidebar() {
//   let navigatee = useNavigate();

//   const handleLogout = () => {
//     // localStorage.clear();
//     sessionStorage.clear();
//     navigatee("/");
//   };
//   return (
//     <>
//     <Header name={sessionStorage.getItem("session_user_name")} role="member" />
//       <div className="sidebar-parent">
//         <div className="menu">
//           <Menu
//             items={[
//               { label: <Link to="memberProfile">Profile</Link>, icon: <HomeFilled /> },
//               {
//                 label: <Link to="tickets">Tickets</Link>,
//                 icon: <MehFilled />,
//               },
//               {
//                 label: <Link to="createTicket">Create Ticket</Link>,
//                 icon: <PlusCircleFilled />,
//               },
//               {
//                 label: <Link to="changePassword">Change Password</Link>,
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
//           <Outlet />
//         </div>
//       </div>
//     </>
//   );
// }


// export default MemberSidebar;





import React, { createContext, useState } from "react";
import { NavLink, Outlet, useNavigate } from "react-router-dom";
import "../Components/style/AdminSidebar1.css"; // Import the CSS file
import Header from "../Components/Header";

function MemberSidebar() {
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
      {sessionStorage.setItem("Admin_Role", "member")}
      <Header name={sessionStorage.getItem("session_user_name")} role="member" />
      <div className="container" style={{ display: "flex" }}>
        <div className={`sidebar ${sidebarExpanded ? "expanded" : ""}`}>
          <div className="AS-menu">
            <NavLink to="memberProfile">
              <div className="AD-li">Profile</div>
            </NavLink>
            <NavLink to="tickets">
              <div className="AD-li">Tickets</div>
            </NavLink>
            <NavLink to="createTicket">
              <div className="AD-li">Create Ticket</div>
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

export default MemberSidebar;
