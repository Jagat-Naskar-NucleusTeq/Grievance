import React, { useState } from "react";
import "../assets/css/NewDepartment.css";
import axios from "axios";
import CustomAlert from "../component/CustomAlert";
import { useNavigate } from "react-router-dom";

function NewDepartment() {
  const [showAlert, setShowAlert] = useState(false);
  const [message, setMessage] = useState("");
  const [formData, setFormData] = useState({ deptName: "" });

  const [departmentNameError, setDepartmentNameError] = useState("");

  const nevigatee = new useNavigate();



  
  const handleClose = () => {
    nevigatee("/viewDept");
  };

  const handleShowAlert = () => {
    setShowAlert(true);
  };

  const handleCloseAlert = () => {
    setShowAlert(false);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (formData.deptName === "") {
      setDepartmentNameError("required*");
    } else {
      try {
        const addNewDeptUrl = "http://localhost:8080/api/dept/addDept";
        console.log("=>" + btoa(localStorage.getItem("session_user_name")));
        console.log("=>" + localStorage.getItem("session_password"));
        const response = await axios.post(addNewDeptUrl, formData, {
          headers: {
            Email: btoa(localStorage.getItem("session_user_name")),
            Password: localStorage.getItem("session_password"),
          },
        });
        setFormData({ ...formData, deptName: "" });
        setDepartmentNameError("");
        setMessage(response.data);
        handleShowAlert();
      } catch (error) {
        console.error(error);
      }
    }
    setFormData({ ...formData, deptName: "" });
  };

  return (
    <div className="modal-wrapper">
      
      <div className="new-department-card">
      <button className="ND-close-btn" onClick={handleClose}>
          X
        </button>
        <h1 className="new-department-title">Add Department</h1>
        <form onSubmit={handleSubmit}>
          <label>
            Department Name: <span className="astrix">*</span>{" "}
            <p className="error">{departmentNameError}</p>{" "}
          </label>
          <input
            className="ND-input"
            type="text"
            value={formData.deptName}
            onChange={(e) =>
              setFormData({ ...formData, deptName: e.target.value })
            }
          />
          {showAlert && (
            <CustomAlert
              message={message}
              handleCloseAlert={handleCloseAlert}
            />
          )}
          <button className="NDsubmit" type="submit">
            Add
          </button>
        </form>
        
      </div>
    </div>
  );
}

export default NewDepartment;
