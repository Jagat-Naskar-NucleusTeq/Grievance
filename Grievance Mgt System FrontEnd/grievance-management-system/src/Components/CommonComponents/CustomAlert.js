import React from "react";
import "../style/CustomAlert.css";

const CustomAlert = ({ message, handleCloseAlert }) => {
  return (
    <>
      <div className="modal-wrapper">
        <div className="custom-alert modal-container ">
          <div className="custom-alert-content modal-dialog">
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
