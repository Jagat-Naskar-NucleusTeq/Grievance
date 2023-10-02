import React from "react";
import "../assets/css/CustomAlert.css";
import AlertIcon from "../assets/icons/success1-icon.svg";

const CustomAlert = ({ message, handleCloseAlert }) => {
  return (
    <>
      <div className="modal-wrapper">
        <div className="custom-alert modal-container ">
          <div className="custom-alert-content modal-dialog">
          <img
                  src={AlertIcon}
                  alt="Description"
                  style={{ width: "35px"}}
                />
            <p className="AlertMessage">{message}</p>
            <button className="CA-close-btn" onClick={handleCloseAlert}>
              Close
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default CustomAlert;
