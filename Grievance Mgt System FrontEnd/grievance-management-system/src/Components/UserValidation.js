const validateName = (name) => {
    if (!name.trim()) {
      return "Name is a required field.";
    }
    if (name.length < 3 || name.length > 25) {
      return "Name should be between 3 and 25 characters.";
    }
    return "";
  };
  
  const validateUsername = (username) => {
    if (!username.trim()) {
      return "Username is a required field.";
    }
    if (!/^[A-Za-z0-9_.-]+@nucleusteq\.com$/i.test(username)) {
      return "Invalid (email format: ...@nucleusteq.com)";
    }
    return "";
  };
  
  const validatePassword = (password) => {
    if (!password) {
      return "Password is required.";
    }
    if (password.length < 8) {
      return "Password should be at least 8 characters long.";
    }
    if (!/[A-Z]/.test(password)) {
      return "Password should contain at least one uppercase letter.";
    }
    if (!/[a-z]/.test(password)) {
      return "Password should contain at least one lowercase letter.";
    }
    if (!/[0-9]/.test(password)) {
      return "Password should contain at least one digit.";
    }
    if (!/[!@#$%^&*]/.test(password)) {
      return "Password should contain at least one special character.";
    }
    return "";
  };
  
  const validateUserType = (userType) => {
    if (!userType || userType === "Select User Type") {
      return "Please select a user type.";
    }
    return "";
  };
  
  const validateDeptName = (departmentName) => {
    if (!departmentName || departmentName === "Select a Department") {
      return "Please select a department.";
    }
    return "";
  };
  
  export { validateName, validateUsername, validatePassword, validateUserType, validateDeptName }