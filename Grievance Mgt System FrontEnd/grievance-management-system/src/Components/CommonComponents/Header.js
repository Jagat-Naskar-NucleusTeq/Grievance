import React from "react";
import "../style/Header.css";
// import AdminLogo from "C:\Users\jagat\OneDrive\Desktop\CapstronProject\sprint6.3\Grievance Mgt System FrontEnd\grievance-management-system\src\Components\images\icons\manager-icon.svg";

function Header(props) {
  const { name, role } = props;

  return (
    <div className="admin-header">
      <h1>Greviance Management System</h1>
      <p className="role">{role}</p>

      <h3>
                {/* <img
                  src={AdminLogo}
                  alt="Description of the image"
                  style={{ width: "15%" }}
                /> */}
        {name}</h3>
    </div>
  );
}

export default Header;
