import React, { useState } from "react";
import "../assets/css/Header.css";
import LogoutIcon from "../assets/icons/logout.svg";
import ChangePasswordIcon from "../assets/icons/changePassword.svg";
import ProfileIcon from "../assets/icons/profileIcon.svg";
import {useNavigate } from "react-router-dom";
import ConfirmationBox from "./ConfirmationBox";
import project_logo from "../assets/icons/logo5.png"

const Header = () => {

  const [userName, setUserName] = useState(localStorage.getItem("Name_of_User"));
  const [showConfirmation, setShowConfirmation] = useState(false);
  const[indexofSpace, setIndexOfSpace] = useState(0);
  let navigatee = useNavigate();

  const handleLogout = () => {
    sessionStorage.clear();
    localStorage.clear();
    navigatee("/");
  };

  const openConfirmBox = () => {
    setShowDropdown(false);
    setShowConfirmation(true);
  };
  const handlePasswordChange = () =>{
    setShowDropdown(false);
    navigatee("changePassword");
  }
  const handleProfile = () =>{
    setShowDropdown(false);
    navigatee("adminProfile");
  }
  const handleConfirm = () => {
    handleLogout();
    setShowConfirmation(false);
  };

  const handleCancel = () => {
    setShowConfirmation(false);
  };





  const [showDropdown, setShowDropdown] = useState(false);

  const toggleDropdown = () => {
    setShowDropdown(!showDropdown);
  };

  const firstSpaceIndex = userName.indexOf(' ');
  const substring = firstSpaceIndex !== -1 ? userName.substring(0, firstSpaceIndex) : userName;


  return (
    <div className="header">
      <div className="projectLogo"><img 
                  src={project_logo}
                  alt="Description"
                  className="project_icon"
                /></div>
      <h2>Welcome,  <span className="nameOfUser">{substring}</span></h2>
      <h1>{localStorage.getItem("session_user_name")}</h1>

      <div className="icon" onClick={toggleDropdown}>
        <span className="dropDown-click">
          ☰
        </span>
      </div>

      {showDropdown && (
        <div className="dropdown">
          <ul>
            <div to = "adminProfile" onClick={handleProfile}>
            <li>
              <span className="H-ProfileSection">
                <span className="H-UserName">
                  <img
                    src={ProfileIcon}
                    alt="Description"
                    style={{ width: "15%" }}
                    className="icon"
                  />
                  {localStorage.getItem("Name_of_User")}
                </span>
                <span className="H-proifle">Profile</span>
                <span className="H-divider"></span>
              </span>
            </li>
            </div>
            <div onClick={handlePasswordChange}>
            <li >
              <img
                src={ChangePasswordIcon}
                alt="Description"
                style={{ width: "15%" }}
                className="icon"
              />
              Change Password
            </li>
            </div>
            <div onClick={openConfirmBox}>
            <li>
              <img
                src={LogoutIcon}
                alt="Description"
                style={{ width: "15%" }}
                className="icon"
              />
              <span className="H-Logout">LogOut</span>
              
            </li>
            </div>
          </ul>
        </div>
      )}
      {showConfirmation && (
            <ConfirmationBox
              message="Are you sure to Logout?"
              onConfirm={handleConfirm}
              onCancel={handleCancel}
            />
          )}
    </div>
  );
};

export default Header;
