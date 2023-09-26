import React, { useState } from "react";

import "../Components/style/PasswordChange.css";
import { changePasswordPost } from "../services/userService/User";
import { useNavigate } from "react-router-dom";
import CustomAlert from "./CommonComponents/CustomAlert";


const PasswordChange = () => {
  const [formData, setFormData] = useState({
    oldPassword: "",
    newPassword: "",
    confirmNewPassword: "",
  });

  const [showAlert, setShowAlert] = useState(false);

  const handleShowAlert = () => {
    setShowAlert(true);
  };
  const handleCloseAlert=()=>{
    setShowAlert(false);
  }

  let navigatee = useNavigate();
  const [errors, setErrors] = useState({});
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };




  



  const validateForm = () => {
    const newErrors = {};
    if (
      !formData.oldPassword ||
      !formData.newPassword ||
      !formData.confirmNewPassword
    ) {
      newErrors.message = "All fields are required";
    } else if (formData.newPassword !== formData.confirmNewPassword) {
      newErrors.message = "New password and confirm password do not match";
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (validateForm()) {
      try {
        const encodedFormData = {// Encode data
          userName: btoa(sessionStorage.getItem("session_user_name")),
          oldPassword: btoa(formData.oldPassword),
          newPassword: btoa(formData.newPassword), 
          confirmNewPassword: btoa(formData.confirmNewPassword), 
        };

        const response = await changePasswordPost(encodedFormData);
        if(response === "Password changed successfully")
        {
          navigatee("/admin");
        }
        else if(response === "Password changed successfully.")
        {
          setMessage(response);
          handleShowAlert();
          navigatee("/member");
        }
        else if(response === "Incorrect old password.") {
          setMessage(response);
          handleShowAlert();
          setFormData({
            oldPassword: "",
            newPassword: "",
            confirmNewPassword: "",
          });
        }
        else {
          setMessage(response);
          handleShowAlert();
          setFormData({
            oldPassword: "",
            newPassword: "",
            confirmNewPassword: "",
          });
        }
      } catch (error) {
        setMessage("Error changing password. Please try again later.");
        handleShowAlert();
      }
    }
  };

  return (
    <div className="cover_page">
    <div className="parentChangePassword">
      <div className="form-body">
        <h2 className="passwordHeader">Password Change</h2>

        <form onSubmit={handleSubmit}>
          <div className="oldPasswordLabel">
            <label className="label1">Old Password:</label>
            <input
              type="password"
              id="oldPassword"
              name="oldPassword"
              placeholder="your old password"
              value={formData.oldPassword}
              onChange={handleChange}
            />
          </div>
          <div>
            <label className="label2">New Password:</label>
            <input
              type="password"
              id="newPassword"
              name="newPassword"
              placeholder="your new password"
              value={formData.newPassword}
              onChange={handleChange}
            />
          </div>
          <div>
            <label className="label3">Confirm New Password:</label>
            <input
              type="password"
              id="confirmNewPassword"
              name="confirmNewPassword"
              placeholder="confirm password"
              value={formData.confirmNewPassword}
              onChange={handleChange}
            />
          </div>
          <button type="submit" className="PC-submit-btn">
            Submit
          </button>
        </form>
        {errors.message && <div className="error">{errors.message}</div>}
        {message && <div className="success">{message}</div>}
      </div>
    </div>
          {
            showAlert &&
              <CustomAlert message = {message} handleCloseAlert={handleCloseAlert}/>
          }
    </div>
  );
};

export default PasswordChange;
