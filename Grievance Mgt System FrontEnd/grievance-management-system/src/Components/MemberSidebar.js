import React, { useState } from "react";
import { NavLink, Outlet, useNavigate } from "react-router-dom";
import "../Components/style/AdminSidebar1.css";
import Header from "../Components/CommonComponents/Header";
import ConfirmationBox from "./CommonComponents/ConfirmationBox";
import AdminProfilePic from "../Components/images/icons/member-profile-icon.svg";
import TicketsIcon from "../Components/images/icons/tickets-icon.svg";
import TicketIcon from "../Components/images/icons/ticket-icon.svg";
import PasswordChangeIcon from "../Components/images/icons/password-reset-icon.svg";

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
                Profile
              </div>
            </NavLink>
            <NavLink to="tickets">
              <div className="AD-li">
                <img
                  src={TicketsIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                Tickets
              </div>
            </NavLink>
            <NavLink to="createTicket">
              <div className="AD-li">
                <img
                  src={TicketIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                Create Ticket
              </div>
            </NavLink>
            <NavLink to="changePassword">
              <div className="AD-li">
                <img
                  src={PasswordChangeIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                Change Password
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
