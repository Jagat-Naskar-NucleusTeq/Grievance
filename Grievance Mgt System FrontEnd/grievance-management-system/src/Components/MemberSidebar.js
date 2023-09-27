
import React, { createContext, useState } from "react";
import { NavLink, Outlet, useNavigate } from "react-router-dom";
import "../Components/style/AdminSidebar1.css"; // Import the CSS file
import Header from "../Components/Header";

function MemberSidebar() {
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
      {localStorage.setItem("Admin_Role", "member")}
      <Header name={localStorage.getItem("session_user_name")} role="member" />
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
