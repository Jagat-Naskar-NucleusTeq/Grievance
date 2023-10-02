import React, { useState } from "react";
import { Link } from "react-router-dom";
import "../style/Registration.css";

function Registration() {
  const [name, setName] = useState("");
  const [nameError, setNameError] = useState("");
  const [username, setUsername] = useState("");
  const [usernameError, setUsernameError] = useState("");
  const [password, setPassword] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [conpassword, setConPassword] = useState("");
  const [conPasswordError, setConPasswordError] = useState("");
  const [userType, setUserType] = useState("");
  const [userTypeError, setUserTypeError] = useState("");
  const [showAlert, setShowAlert] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    validateName();
    validateUsername();
    validatePassword();
    validateConPassword();
    validateUserType();

    if (
      !nameError &&
      !usernameError &&
      !passwordError &&
      !conPasswordError &&
      !userTypeError
    ) {
      // Validation successful
      // Reset the form after successful submission
      resetForm();
      <Link to="/home" />;
    } else {
      alert("Form has validation errors");
    }
  };

  const resetForm = () => {
    setName("");
    setUsername("");
    setPassword("");
    setConPassword("");
    setUserType("");
  };

  const validateName = () => {
    if (name.trim() === "") {
      setNameError("required fields*");
    } else if (name.length < 3) {
      setNameError("Min len is 3");
    } else if (name.length > 25) {
      setNameError("Max len is 25");
    } else {
      setNameError("");
    }
  };

  const validateUsername = () => {
    if (username.trim() === "") {
      setUsernameError("required filed*");
    } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(username)) {
      setUsernameError("Your email pattern is incorrect");
    } else {
      setUsernameError("");
    }
  };

  const validatePassword = () => {
    if (password === "") {
      setPasswordError("Password is required");
    } else if (password.length < 8) {
      setPasswordError("min len should be 8 char");
    } else if (!/[A-Z]/.test(password)) {
      setPasswordError("min Uppercase should be 1");
    } else if (!/[a-z]/.test(password)) {
      setPasswordError("Min lowercase should be 1");
    } else if (!/[0-9]/.test(password)) {
      setPassword("min number should be 1");
    } else if (!/[!@#$%^&*]/.test(password)) {
      setPasswordError("min symbol should be 1");
    } else {
      setPasswordError("");
    }
  };

  const validateConPassword = () => {
    if (conpassword === "") {
      setConPasswordError("Confirm Password is required");
    } else if (conpassword !== password) {
      setConPasswordError("Passwords do not match");
    } else {
      setConPasswordError("");
    }
  };

  const validateUserType = () => {
    if (userType === "") {
      setUserTypeError("Please select a user type");
    } else {
      setUserTypeError("");
    }
  };

  return (
    <div>
      <form className="reg-form" onSubmit={handleSubmit}>
        <h2 className="reg-heading">Registration</h2>
        <label className="name">
          Name <p className="error1">{nameError}</p>{" "}
        </label>
        <input
          type="text"
          id="name"
          placeholder="Enter your name"
          name="name"
          onChange={(e) => {
            setName(e.target.value);
          }}
        />
        <label className="username">
          Username <p className="error1">{usernameError}</p>{" "}
        </label>

        <input
          type="text"
          id="username"
          placeholder="Enter your Username"
          name="username"
          onChange={(e) => {
            setUsername(e.target.value);
          }}
        />
        <label className="password">
          Password <p className="error1">{passwordError}</p>{" "}
        </label>
        <input
          type="password"
          id="password"
          placeholder="Enter your Password"
          name="password"
          onChange={(e) => {
            setPassword(e.target.value);
          }}
        />
        <label className="conpassword">
          Confirm Password <p className="error1">{conPasswordError}</p>{" "}
        </label>
        <input
          type="password"
          id="confirm-password"
          placeholder="Confirm your Password"
          name="confirm_password"
          onChange={(e) => {
            setConPassword(e.target.value);
          }}
        />
        <label className="userType">
          User Type <p className="error1">{userTypeError}</p>{" "}
        </label>
        <select
          id="userType"
          name="user_type"
          onChange={(e) => {
            setUserType(e.target.value);
          }}
        >
          <option value="">Select User Type</option>
          <option value="admin">Admin</option>
          <option value="user">User</option>
        </select>
        <button type="submit">Register</button>
        <p>
          Already have an account?
          <Link className="login-link" to="/login">
            {" "}
            Log in
          </Link>
        </p>
      </form>
    </div>
  );
}

export default Registration;
