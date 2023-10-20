import { useNavigate } from "react-router-dom";
import "../assets/css/Login.css";
import axios from "axios";
import React, { useState } from "react";
import CustomAlert from "../component/CustomAlert";
import imageSrc from "../assets/img/login-image.png";
import { setLoggedIn } from "../index.js";
import MyPic from "../assets/img/LoginPic.png";

const Login = () => {
  const [username1, setUsername1] = useState("");
  const [password1, setPassword1] = useState("");
  const [error, setError] = useState("");
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [showAlert, setShowAlert] = useState(false);
  const [message, setMessage] = useState("");
  const [post, setPost] = useState({ email: "", password: "" });
  let navigatee = useNavigate();

  const handleShowAlert = () => {
    setShowAlert(true);
  };
  const handleCloseAlert = () => {
    setShowAlert(false);
  };
  const resetForm = () => {
    setUsername1("");
    setPassword1("");
    setIsSubmitted(false);
  };
  const handleLogin = async (e) => {
    e.preventDefault();
    if (username1.trim() === "") {
      setError("Email is required");
    } else if (password1.trim() === "") {
      setError("Password is required");
    } else {
      setError("");
      setIsSubmitted(true);
      const encodedUsername = btoa(username1);
      const encodedPassword = btoa(password1);
      post.email = encodedUsername;
      post.password = encodedPassword;

      setPost({ ...post });
      try {
        const res = await axios.post(
          "http://localhost:8080/api/users/login",
          post,
          {
            headers: {
              email: encodedUsername,
              password: encodedPassword,
            },
          }
        );
        localStorage.setItem("LoggendIn22", "true");
        localStorage.setItem("Name_of_User", res.data.name);
        localStorage.setItem("session_user_name", res.data.userName);
        localStorage.setItem("session_password", res.data.password);
        localStorage.setItem("Admin_Role", res.data.userType);
        localStorage.setItem("MyDeptName", res.data.departmentName);
        localStorage.setItem("finalPassword_set", res.data.finalPassword);
        if (res.data.finalPassword === "false") {
          setLoggedIn("true");
          navigatee("/changePassword");
        } else if (res.data.finalPassword === "true") {
          setLoggedIn("true");
          navigatee("/tickets");
        }
        // } else if (res.data.userType === "member" && res.data.finalPassword === "false") {
        //   setLoggedIn("true");
        //   navigatee("/changePassword");
        // } else if (res.data.userType === "member" && res.data.finalPassword === "true") {
        //   setLoggedIn("true");
        //   navigatee("/member/tickets");
        // }
        else {
          setMessage("Invalid Credentials!!!");
          handleShowAlert();
          resetForm();
        }
      } catch (e) {
        setMessage("Failed to login");
        handleShowAlert();
      }
    }
  };

  return (
    <div className="login-body">
      <div className="head">
        <h1>Greviance Management System</h1>
      </div>

      <div className="L-main">
        <img src={MyPic} alt="Description" className="L-profilePicture" />

        <div className="login-page">
          <div className="login-image">
            <img src={imageSrc} alt="Description" style={{ width: "25%" }} />
            <h2>Login</h2>
          </div>

          <div className="error1">
            {error && <p className="error-message">{error}</p>}
          </div>
          <form onSubmit={handleLogin} method="post">
            <div className="form-group">
              <label id="label-username">
                Email<span className="astrix">*</span>
              </label>
              <input
                type="text"
                id="username"
                placeholder="Enter your email"
                value={username1}
                onChange={(e) => setUsername1(e.target.value)}
              />
              {isSubmitted && username1.trim() === "" && (
                <p className="error-message">Username is required</p>
              )}
            </div>
            <div className="form-group">
              <label id="label-password">
                Password<span className="astrix">*</span>
              </label>
              <input
                type="password"
                id="password"
                placeholder="Enter your password"
                value={password1}
                onChange={(e) => setPassword1(e.target.value)}
              />
              {isSubmitted && password1.trim() === "" && (
                <p className="error-message">Password is required</p>
              )}
            </div>
            {showAlert && (
              <CustomAlert
                message={message}
                handleCloseAlert={handleCloseAlert}
              />
            )}
            <button className="Login-btn" type="submit">
              Login
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
