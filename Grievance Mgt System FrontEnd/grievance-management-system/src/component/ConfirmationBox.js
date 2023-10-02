import React from "react";
import "../assets/css/ConfirmationBox.css";
import AlertIcon from "../assets/icons/alert-icons.svg";

const ConfirmationBox = ({ message, onConfirm, onCancel }) => {
  return (
    <div className="CB-main">
      <div className="confirmation-box">
      <img
                  src={AlertIcon}
                  alt="Description"
                  style={{ width: "35px"}}
                />
        <div className="message">{message}</div>
        <button className="confirm-btn" onClick={onConfirm}>
          Confirm
        </button>
        <button className="cancel-btn" onClick={onCancel}>
          Cancel
        </button>
      </div>
    </div>
  );
};

export default ConfirmationBox;
