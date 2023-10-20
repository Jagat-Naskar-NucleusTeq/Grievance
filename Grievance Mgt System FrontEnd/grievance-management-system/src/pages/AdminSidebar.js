import React, { useState } from "react";
import { NavLink, Outlet, useNavigate } from "react-router-dom";
import "../assets/css/AdminSidebar1.css";
import Header from "../component/Header";
import ConfirmationBox from "../component/ConfirmationBox";
import AdminProfilePic from "../assets/icons/admin-icon.svg";
import AllUserIcon from "../assets/icons/all-users-icon.svg";
import TicketsIcon from "../assets/icons/tickets-icon.svg";
import DepartmentIcon from "../assets/icons/department-icon.svg";
import PasswordChangeIcon from "../assets/icons/password-reset-icon.svg";

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
      <Header/>
      <div className="container" style={{ display: "flex" }}>
        <div className="sidebar">
          <div className="AS-menu">
            {/* <NavLink to="adminProfile">
              <div className="AD-li">
                <img
                  src={AdminProfilePic}
                  alt="Description"
                  style={{ width: "15%" }}
                  className="icon"
                />
                <span className="text-style">Profile</span>
              </div>
            </NavLink> */}
            {/* <NavLink to="addUser">
              <div className="AD-li">
              <img
                  src={AddUserIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                  className="icon"
                />
                 <span className="text-style" >Add User</span>
               
              </div>
            </NavLink> */}
            {localStorage.getItem("Admin_Role") === "admin"  && (
              <NavLink to="users">
                <div className="AD-li">
                  <img
                    src={AllUserIcon}
                    alt="Description"
                    style={{ width: "15%" }}
                    className="icon"
                  />
                  <span className="text-style">Users</span>
                </div>
              </NavLink>
            )}

            <NavLink to="tickets">
              <div className="AD-li">
                <img
                  src={TicketsIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                  className="icon"
                />
                <span className="text-style">Tickets</span>
              </div>
            </NavLink>
            {/* <NavLink to="tickets/createTicket">
              <div className="AD-li">
                <img
                  src={TicketIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                  className="icon"
                />
                 <span className="text-style">Create Ticket</span>
                
              </div>
            </NavLink> */}
            {localStorage.getItem("Admin_Role") === "admin" && (
              <NavLink to="viewDept">
                <div className="AD-li">
                  <img
                    src={DepartmentIcon}
                    alt="Description"
                    style={{ width: "15%" }}
                    className="icon"
                  />
                  <span className="text-style">Departments</span>
                </div>
              </NavLink>
            )}
            {/* <NavLink to="changePassword">
              <div className="AD-li">
                <img
                  src={PasswordChangeIcon}
                  alt="Description"
                  style={{ width: "15%" }}
                  className="icon"
                />
                <span className="text-style">Change Password</span>
              </div>
            </NavLink> */}
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
