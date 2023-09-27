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
      {localStorage.setItem("Admin_Role", "admin")}
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
