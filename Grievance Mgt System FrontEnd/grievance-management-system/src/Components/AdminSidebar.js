import React, { useState } from "react";
import { NavLink, Outlet, useNavigate } from "react-router-dom";
import "../Components/style/AdminSidebar1.css";
import Header from "../Components/CommonComponents/Header";
import ConfirmationBox from "./CommonComponents/ConfirmationBox";
import AdminProfilePic from "../Components/images/icons/admin-icon.svg";
import AddUserIcon from "../Components/images/icons/user-add-icon.svg";
import AllUserIcon from "../Components/images/icons/all-users-icon.svg";
import TicketsIcon from "../Components/images/icons/tickets-icon.svg";
import TicketIcon from "../Components/images/icons/ticket-icon.svg";
import DepartmentIcon from "../Components/images/icons/department-icon.svg";
import PasswordChangeIcon from "../Components/images/icons/password-reset-icon.svg";

function AdminSidebar() {
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
      <Header name={localStorage.getItem("session_user_name")} role="admin" />
      <div className="container" style={{ display: "flex" }}>
        <div className="sidebar">
          <div className="AS-menu">
            <NavLink to="adminProfile">
              <div className="AD-li">
                <img
                  src={AdminProfilePic}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                Profile
              </div>
            </NavLink>
            <NavLink to="addUser">
              <div className="AD-li">
              <img
                  src={AddUserIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                Add User
              </div>
            </NavLink>
            <NavLink to="allUsers">
              <div className="AD-li">
                <img
                  src={AllUserIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                All Users
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
            <NavLink to="viewDept">
              <div className="AD-li">
                <img
                  src={DepartmentIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                />
                Departments
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
          {showConfirmation && (
            <ConfirmationBox
              message="Are you sure to Logout?"
              onConfirm={handleConfirm}
              onCancel={handleCancel}
            />
          )}
        </div>
        <div className="admin-content">
          <Outlet />
        </div>
      </div>
    </>
  );
}

export default AdminSidebar;
