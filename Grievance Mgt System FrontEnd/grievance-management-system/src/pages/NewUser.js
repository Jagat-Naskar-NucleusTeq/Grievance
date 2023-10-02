import React, { useState, useEffect } from "react";
import CustomAlert from "../component/CustomAlert";
import axios from "axios";
import "../assets/css/NewUser.css";
import {
  validateName,
  validateUsername,
  validatePassword,
  validateUserType,
  validateDeptName,
} from "../pages/UserValidation";

export default function NewUser() {
  const [name, setName] = useState("");
  const [nameError, setNameError] = useState("");
  const [username, setUsername] = useState("");
  const [usernameError, setUsernameError] = useState("");
  const [password, setPassword] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [userType, setUserType] = useState("");
  const [userTypeError, setUserTypeError] = useState("");
  const [departmentName, setDepartmentName] = useState("");
  const [departmentList, setDepartmentList] = useState([
    { deptId: "", deptName: "" },
  ]);
  const [deptNameError, setDeptNameError] = useState("");
  const [showAlert, setShowAlert] = useState(false);
  const [message, setMessage] = useState("");

  const handleShowAlert = () => {
    setShowAlert(true);
  };

  const handleCloseAlert = () => {
    setShowAlert(false);
  };

  useEffect(() => {
    fetch("http://localhost:8080/api/dept/allDepartment")
      .then((response) => response.json())
      .then((data) => setDepartmentList((prevList) => [...prevList, ...data]))
      .catch((error) => console.error("deptList Error:", error));
  }, []);

  const resetForm = () => {
    setName("");
    setUsername("");
    setPassword("");
    setUserType("Select User Type");
    setDepartmentName("Select Department");

    setUsernameError("");
    setNameError("");
    setDeptNameError("");
    setPasswordError("");
    setUserTypeError("");
  };

  const UserTypeList = ["Select User Type", "admin", "member"];

  const handleSubmit = async (e) => {
    e.preventDefault();

    const nameError = validateName(name);
    const usernameError = validateUsername(username);
    const passwordError = validatePassword(password);
    const userTypeError = validateUserType(userType);
    const deptNameError = validateDeptName(departmentName);

    if (
      !nameError &&
      !usernameError &&
      !passwordError &&
      !userTypeError &&
      !deptNameError
    ) {
      const encodedPassword = btoa(password);
      const postObject = {
        name: name,
        departmentName: departmentName,
        password: encodedPassword,
        userType: userType,
        userName: username,
      };
      try {
        const res = await axios.post(
          "http://localhost:8080/api/users/addUser",
          postObject
        );
        setMessage(res.data);
        handleShowAlert();

        resetForm();
      } catch (e) {
        setMessage(e.message);
        handleShowAlert();
      }
    } else {
      setNameError(nameError);
      setUsernameError(usernameError);
      setPasswordError(passwordError);
      setUserTypeError(userTypeError);
      setDeptNameError(deptNameError);
    }
  };

  return (
    <div>
      <form className="NU-reg-form" onSubmit={handleSubmit} method="post">
        <div className="NU-parent">
          <h2 className="NU-reg-heading">Add User</h2>
          <div className="NU-container1">
            <label className="NU-label-user">
              Name<span className="astrix">*</span>{" "}
              <p className="NU-error1">{nameError}</p>{" "}
            </label>
            <input
              className="NU-input-user"
              type="text"
              id="name"
              placeholder="Enter your name"
              name="name"
              value={name}
              onChange={(e) => {
                setName(e.target.value);
              }}
            />
            <label className="NU-label-user">
              Email<span className="astrix">*</span>{" "}
              <p className="NU-error1">{usernameError}</p>{" "}
            </label>

            <input
              className="NU-input-user"
              type="text"
              id="username"
              placeholder="Enter your Username"
              name="username"
              value={username}
              onChange={(e) => {
                setUsername(e.target.value);
              }}
            />
            <label className="NU-label-user">
              Initial Password<span className="astrix">*</span>{" "}
              <p className="NU-error1">{passwordError}</p>{" "}
            </label>
            <input
              className="NU-input-user"
              type="password"
              id="password"
              placeholder="Enter your Password"
              name="password"
              value={password}
              style={{
                width: "94%",
                padding: "6px",
                marginRight: "6%",
              }}
              onChange={(e) => {
                setPassword(e.target.value);
              }}
            />
          </div>
          <div className="NU-container2">
            <label className="NU-label-user">
              User Type<span className="astrix">*</span>{" "}
              <p className="NU-error1">{userTypeError}</p>{" "}
            </label>
            <select
              className="NU-input-user"
              id="userType"
              name="user_type"
              value={userType}
              onChange={(e) => {
                setUserType(e.target.value);
              }}
            >
              {UserTypeList.map((e) => (
                <option key={e} value={e}>
                  {e}
                </option>
              ))}
            </select>

            <label className="NU-label-user">
              Department<span className="astrix">*</span>{" "}
              <p className="NU-error1">{deptNameError}</p>{" "}
            </label>
            <select
              className="NU-input-user"
              id="deptName"
              name="dept_type"
              value={departmentName}
              onChange={(e) => {
                setDepartmentName(e.target.value);
              }}
            >
              <option value="" disabled>
                Select a Department
              </option>
              {departmentList.map((e) => (
                <option key={e.id} value={e.deptName}>
                  {e.deptName}
                </option>
              ))}
            </select>

            <button className="NU-button" type="submit">
              {" "}
              Add User{" "}
            </button>
          </div>
        </div>
      </form>
      {showAlert && (
        <CustomAlert message={message} handleCloseAlert={handleCloseAlert} />
      )}
    </div>
  );
}
