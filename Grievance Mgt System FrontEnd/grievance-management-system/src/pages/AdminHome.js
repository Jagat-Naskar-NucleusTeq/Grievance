import React, { useState, useEffect } from "react";
import axios from "axios";
import "../assets/css/HomePage.css";
import adminIcon from "../assets/img/adminProfile.png";
import memberIcon from "../assets/icons/memberProfilePic.svg";

function AdminHome() {
  const [user, setUser] = useState(null);
  const [showPassword, setShowPassword] = useState(false);

  const togglePasswordVisibility = () => {
    setShowPassword((prevState) => !prevState);
  };

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const userName = localStorage.getItem("session_user_name");
        const response = await axios.get(
          `http://localhost:8080/api/users/getByUsrName/${userName}`
        );
        setUser(response.data);
      } catch (error) {
        console.error("Error fetching user details:", error);
      }
    };

    fetchUserDetails();
  }, []);

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
    <div className="Home-Page">
      <div className="profile-picture-container">
        {user.userType === "admin" && (
          <img src={adminIcon} alt="Admin Icon" className="profile-pic" />
        )}
        {user.userType === "member" && (
          <img src={memberIcon} alt="Admin Icon" className="profile-pic" />
        )}
        {/* <img src={user.profilePicture} alt="Profile" /> */}
      </div>
      <div className="user-details">
        <h2>{user.name}</h2>
        <div className="H-main">
          <div className="H-Keys">
            <p>Username: </p>
            <p>Password: </p>
            <p>User Type: </p>
            <p>Department: </p>
          </div>
          <div className="H-Values">
            <p>{user.userName}</p>
            <p>{showPassword ? atob(user.password) : "*********"}</p>
            <p>{user.userType}</p>
            <p>{user.departmentName}</p>
          </div>
        </div>
        {/* <p>Username: {user.userName}</p>
        <p>Password: {showPassword ? atob(user.password) : "*********"}</p>
        <p>User Type: {user.userType}</p>
        <p>Department: {user.departmentName}</p> */}
      </div>
      <button className="Home-btn" onClick={togglePasswordVisibility}>
        {showPassword ? "Hide Password" : "Show Password"}
      </button>
    </div>
  );
}

export default AdminHome;
