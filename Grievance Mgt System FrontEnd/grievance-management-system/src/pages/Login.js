import { useNavigate } from "react-router-dom";
import "../assets/css/Login.css";
import axios from "axios";
import React, { useState } from "react";
import CustomAlert from "../component/CustomAlert";
import imageSrc from "../assets/img/login-image.png";
import {setLoggedIn} from "../index.js"

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
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
    setUsername("");
    setPassword("");
    setIsSubmitted(false);
  };
  const handleLogin = async (e) => {
    e.preventDefault();

    if (username.trim() === "") {
      setError("Username is required");
    } else if (password.trim() === "") {
      setError("Password is required");
    } else {
      setError("");
      setIsSubmitted(true);
      const encodedUsername = btoa(username);
      const encodedPassword = btoa(password);
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
        const decodedEmail = atob(post.email);
        localStorage.setItem("LoggendIn22", "true");
        localStorage.setItem("session_user_name", decodedEmail);
        if (res.data === "true_admin_cp") {
          localStorage.setItem("session_user_name", decodedEmail);
          localStorage.setItem("session_password", post.password);
          localStorage.setItem("Admin_Role", "admin");
          setLoggedIn("true");
          navigatee("/changePassword");
        } else if (res.data === "true_admin") {
          localStorage.setItem("session_user_name", decodedEmail);
          localStorage.setItem("session_password", post.password);
          localStorage.setItem("Admin_Role", "admin");
          setLoggedIn("true");
          navigatee("/admin/tickets");
        } else if (res.data === "true_member_cp") {
          localStorage.setItem("session_user_name", decodedEmail);
          localStorage.setItem("session_password", post.password);
          localStorage.setItem("Admin_Role", "member");
          setLoggedIn("true");
          navigatee("/changePassword");
        } else if (res.data === "true_member") {
          localStorage.setItem("session_user_name", decodedEmail);
          localStorage.setItem("session_password", post.password);
          localStorage.setItem("Admin_Role", "member");
          setLoggedIn("true");
          navigatee("/member/tickets");
        } else {
          setMessage("Invalid Credentials!!!");
          handleShowAlert();
          resetForm();
        }
      } catch (e) {
        setMessage(e.message);
        handleShowAlert();
      }
    }
  };

  return (
    <div className="login-body">
      <div className="head">
        <h1>Greviance Management System</h1>
      </div>
      <div className="login-page">
        <div className="login-image">
          <img
            src={imageSrc}
            alt="Description"
            style={{ width: "25%" }}
          />
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
              placeholder="Enter your username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            {isSubmitted && username.trim() === "" && (
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
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {isSubmitted && password.trim() === "" && (
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
  );
};

export default Login;
