import React, { createContext, useState } from "react";
import { NavLink, Outlet, useNavigate } from "react-router-dom";
import "../Components/style/AdminSidebar1.css"; 
import Header from "../Components/Header";

function AdminSidebar() {
  let navigatee = useNavigate();
  const [sidebarExpanded, setSidebarExpanded] = useState(true);

  const handleLogout = () => {
    sessionStorage.clear();
    localStorage.clear();
    navigatee("/");
  };

  const toggleSidebar = () => {
    setSidebarExpanded(!sidebarExpanded);
  };

  return (
    <>
      {/* {localStorage.setItem("Admin_Role", "admin")} */}
      <Header name={localStorage.getItem("session_user_name")} role="admin" />
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



// import React, { createContext, useState } from "react";
// import { NavLink, Outlet, useNavigate } from "react-router-dom";
// import "../Components/style/AdminSidebar1.css"; 
// import Header from "../Components/Header";

// function AdminSidebar() {
//   let navigatee = useNavigate();
//   const [sidebarExpanded, setSidebarExpanded] = useState(true);

//   const handleLogout = () => {
//     sessionStorage.clear();
//     localStorage.clear();
//     navigatee("/");
//   };

//   const toggleSidebar = () => {
//     setSidebarExpanded(!sidebarExpanded);
//   };

//   return (
//     <>
//       {localStorage.setItem("Admin_Role", "admin")}
//       <Header name={localStorage.getItem("session_user_name")} role="admin" />
//       <div className="container" style={{ display: "flex" }}>
//         <div className={`sidebar ${sidebarExpanded ? "expanded" : ""}`}>
//           <div className="AS-menu">
//             <NavLink to="adminProfile">
//               <div className="AD-li">Profile</div>
//             </NavLink>

//             <div className="AD-li sub-menu-container">
//               Users
//               <div className="sub-menu1">
//                 <NavLink to="addUser">Add User</NavLink>
//                 <NavLink to="allUsers">View Users</NavLink>
//               </div>
//             </div>


//             <div className="AD-li sub-menu-container">
//               Tickets
//               <NavLink to="createTicket">
//                 <div className="sub-menu1">Create Ticket</div>
//              </NavLink>
//               <NavLink to="tickets">
//                 <div className="sub-menu1">View Tickets</div>
//               </NavLink>
             
//             </div>

            



//             <div className="AD-li sub-menu-container">
//             Departments
//               <NavLink to="viewDept">
//                 <div className="sub-menu1">View Departments</div>
//              </NavLink>
//              <NavLink to="/admin/viewDept/createDept">
//                 <div className="sub-menu1">Create Department</div>
//              </NavLink>
//             </div>


            







//             <NavLink to="changePassword">
//               <div className="AD-li">Change Password</div>
//             </NavLink>
//             <div className="AD-li" onClick={handleLogout}>
//               LogOut
//             </div>
//           </div>
//           <div className="arrow" onClick={toggleSidebar}>
//             {sidebarExpanded ? <span>&#x2190;</span> : <span>&#x2192;</span>}
//           </div>
//         </div>
//         <div className="admin-content">
//           <Outlet />
//         </div>
//       </div>
//     </>
//   );
// }

// export default AdminSidebar;
