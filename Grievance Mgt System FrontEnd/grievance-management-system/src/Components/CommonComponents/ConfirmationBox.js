import React from "react";
import "../style/ConfirmationBox.css";

const ConfirmationBox = ({ message, onConfirm, onCancel }) => {
  return (
    <div className="CB-main">
      <div className="confirmation-box">
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
