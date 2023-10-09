import React, { useState } from "react";
import { NavLink, Outlet, useNavigate } from "react-router-dom";
import "../assets/css/AdminSidebar1.css";
import Header from "../component/Header";
import ConfirmationBox from "../component/ConfirmationBox";
import AdminProfilePic from "../assets/icons/member-profile-icon.svg";
import TicketsIcon from "../assets/icons/tickets-icon.svg";
import TicketIcon from "../assets/icons/ticket-icon.svg";
import PasswordChangeIcon from "../assets/icons/password-reset-icon.svg";

function MemberSidebar() {
  const [showConfirmation, setShowConfirmation] = useState(false);
  let navigatee = useNavigate();

  const handleLogout = () => {
    sessionStorage.clear();
    localStorage.clear();
    navigatee("/");
  };

  const openConfirmBox = () => {
    setShowConfirmation(true);
  };
  const handleConfirm = () => {
    handleLogout();
    setShowConfirmation(false);
  };

  const handleCancel = () => {
    setShowConfirmation(false);
  };

  return (
    <>
      <Header name={localStorage.getItem("session_user_name")} role="member" />
      <div className="container" style={{ display: "flex" }}>
        <div className="sidebar">
          <div className="AS-menu">
            <NavLink to="memberProfile">
              <div className="AD-li">
                <img
                  src={AdminProfilePic}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                <span className="text-style">Profile</span>
                
              </div>
            </NavLink>
            <NavLink to="tickets">
              <div className="AD-li">
                <img
                  src={TicketsIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                <span className="text-style">Tickets</span>
                
              </div>
            </NavLink>
            <NavLink to="createTicket">
              <div className="AD-li">
                <img
                  src={TicketIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                <span className="text-style">Create Ticket</span>
                
              </div>
            </NavLink>
            <NavLink to="changePassword">
              <div className="AD-li">
                <img
                  src={PasswordChangeIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                <span className="text-style">Change Password</span>
                
              </div>
            </NavLink>

            <div className="AD-li-logout" onClick={openConfirmBox}>
              LogOut
            </div>

          </div>
        </div>
        {showConfirmation && (
          <ConfirmationBox
            message="Are you sure to Logout?"
            onConfirm={handleConfirm}
            onCancel={handleCancel}
          />
        )}
        <div className="admin-content">
          <Outlet />
        </div>
      </div>
    </>
  );
}

export default MemberSidebar;
