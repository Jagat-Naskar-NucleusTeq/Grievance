import { useNavigate } from "react-router-dom";
import "../style/Login.css";
import axios from "axios";
import React, { useState } from "react";
import CustomAlert from "./CustomAlert";
import { doLogin, setLoggedIn } from "../..";

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
    //validating
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
        if (res.data === "true_admin_cp") {
          sessionStorage.setItem("session_user_name", decodedEmail);
          sessionStorage.setItem("session_password", post.password)
          setLoggedIn("true");
          navigatee("/changePassword");
        } else if (res.data === "true_admin") {
          sessionStorage.setItem("session_user_name", decodedEmail);
          sessionStorage.setItem("session_password", post.password)
          setLoggedIn("true"); //for private route
          navigatee("/admin/tickets");
        } else if (res.data === "true_member_cp") {
          sessionStorage.setItem("session_user_name", decodedEmail);
          sessionStorage.setItem("session_password", post.password)
          setLoggedIn("true");
          navigatee("/changePassword");
        } else if (res.data === "true_member") {
          sessionStorage.setItem("session_user_name", decodedEmail);
          sessionStorage.setItem("session_password", post.password)
          setLoggedIn("true");
          navigatee("/member/tickets");
        } else {
          //showing User is not valid
          setMessage("Invalid Credentials!!!");
          handleShowAlert();
          resetForm();
        }
      } catch (e) {
        //showing Backend is not connected through CustomAlert Box
        setMessage(e.message); //setting message for not having connection
        handleShowAlert(); //ShowAlert == true
      }
    }
  };

  return (
    <div className="login-body">
      <div className="head">
        <h1>Greviance Management System</h1>
      </div>
      <div className="login-page">
        <h2>Login</h2>
        <div className="error1">
          {error && <p className="error-message">{error}</p>}
        </div>
        <form onSubmit={handleLogin} method="post">
          <div className="form-group">
            <label id="label-username">Username*</label>
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
            <label id="label-password">Password*</label>
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

          <button className="Login-btn" type="submit">Login</button>
        </form>
      </div>
    </div>
  );
};

export default Login;