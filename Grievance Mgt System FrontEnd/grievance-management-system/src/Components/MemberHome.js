import React, { useState, useEffect } from 'react';
import axios from 'axios';
import adminIcon from '../Components/images/MemberProfile.png';
import "../Components/style/HomePage.css";


function MemberHome() {
  const [user, setUser] = useState(null);
  const [showPassword, setShowPassword] = useState(false);

  const togglePasswordVisibility = () => {
    setShowPassword(prevState => !prevState);
  };

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const userName = localStorage.getItem('session_user_name');
        const response = await axios.get(`http://localhost:8080/api/users/getByUsrName/${userName}`);
        setUser(response.data);
      } catch (error) {
        console.error('Error fetching user details:', error);
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
        {user.userType === 'member' && <img src={adminIcon} alt="Admin Icon" className="profile-pic" />}
        {/* <img src={user.profilePicture} alt="Profile" /> */}
      </div>
      <div className="user-details">
        <h2>{user.name}</h2>
        <p>Username: {user.userName}</p>
        <p>Password: {showPassword ? atob(user.password) : '*********'}</p>
        <p>User Type: {user.userType}</p>
        <p>Department: {user.departmentName}</p>
      </div>
      <button onClick={togglePasswordVisibility}>
        {showPassword ? 'Hide Password' : 'Show Password'}
      </button>
    </div>
  );
};

export default MemberHome;