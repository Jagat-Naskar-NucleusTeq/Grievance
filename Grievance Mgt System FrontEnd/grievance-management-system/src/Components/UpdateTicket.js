import React, { useState, useEffect } from "react";
import "../Components/style/UpdateTicket.css";
import axios from "axios";

function UpdateTicket(props) {
  const [status, setStatus] = useState("Select Status");

  const [comment, setComment] = useState("");

  const [commentError, setCommentError] = useState("");
  const [statusError, setStatusError] = useState("");

  const [ticket, setTicket] = useState({
    ticketId: null,
    title: "",
    creationTime: "",
    updationTime: "",
    ticketStatus: "",
    ticketType: "",
    createdBy: "",
    departmentName: "",
    comments: [
      {
        commentedByUser: "",
        commentMessage: "",
        commentId: null,
      },
    ],
  });

  // Fetching the ticket
  const fetchTicketData = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/tickets/getIcketById/${props.id}`
      );
      const data = response.data;
      setTicket(data);
    } catch (error) {
      console.error("Error fetching ticket data:", error);
    }
  };
  useEffect(() => {
    fetchTicketData();
  }, [props.id]);

  const statusList = ["Select Status", "Open", "Being_Addressed", "Resolved"];
  //reset all the fields
  const resetAllFields = () => {
    setComment("");
    setStatus("Select Status");
  };
  //reset all error
  const resetFormError = () => {
    setCommentError("");
    setStatusError("");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    let isValid = true;

    if (!comment || comment === "") {
      setCommentError("Comment required*.");
      isValid = false;
    } else {
      setCommentError("");
    }

    if (!status || status === "Select Status") {
      setStatusError("Select Status");
      isValid = false;
    }

    if (isValid) {
      const sendingData = {
        ticketId: props.id,
        ticketStatus: status,
        comment: comment,
      };
      axios
        .post(`http://localhost:8080/api/tickets/updateTicket`, sendingData)
        .then((res) => {
          resetFormError();
          resetAllFields();
          fetchTicketData();
        });
    }
  };

  return (
    <div className="updateDeptMainDiv">
      <div className="tickets-container">
        <form className="ticket-form" onSubmit={handleSubmit}>
          <h2 className="UT-h2">Update Ticket Details</h2>
          <div className="main-div">
            <div className="div1">
              <label className="ticketType">Ticket Type</label>
              <select
                id="ticketType"
                name="ticketType"
                value={ticket.ticketType}
                disabled
              >
                <option>{ticket.ticketType}</option>
              </select>

              <label className="currentTitle">Ticket Title</label>
              <select
                id="TType"
                name="TicketType"
                value={ticket.title}
                disabled
              >
                <option>{ticket.title}</option>
              </select>

              <label className="currentStatus">Current status</label>
              <select
                id="Cstatus"
                name="currentStatus"
                value={ticket.ticketStatus}
                disabled
              >
                <option>{ticket.ticketStatus}</option>
              </select>

              <label className="UT-status">Change Status</label>

              <select
                id="status"
                name="status"
                value={status}
                onChange={(e) => setStatus(e.target.value)}
              >
                {statusList.map((e, index) => (
                  <option key={index} value={e}>
                    {e}
                  </option>
                ))}
              </select>
              {statusError && <p className="error">{statusError}</p>}
            </div>

            <div className="div1">
              <label className="assignTo">Department</label>
              <select
                id="assignTo"
                name="assignTo"
                value={ticket.departmentName}
                disabled
              >
                <option>{ticket.departmentName}</option>
              </select>
              <label className="createdBy">Created by</label>
              <select
                id="createdBy"
                name="createdBy"
                value={ticket.createdBy}
                disabled
              >
                <option>{ticket.createdBy}</option>
              </select>

              <label className="description">Description</label>
              <textarea
                id="description"
                name="description"
                value={ticket.ticketDescription}
                disabled
              />

              <label className="comment">Comment</label>
              <input
              className="input-comment"
                id="comment"
                name="comment"
                value={comment}
                onChange={(e) => setComment(e.target.value)}
                placeholder="write comment..."
              />
              {commentError && <p className="error">{commentError}</p>}
            </div>
          </div>
          {/* <button type="submit" className = "editButton1"  >Submit</button> */}
          {props.editButtonsDisabled ? null : (
            <button className="editButton1" type="submit">
              Edit
            </button>
          )}

          <button className="Ut-nevigate-back" onClick={props.onClose}>
            Close
          </button>
        </form>

        <div className="comment-box">
          {ticket.comments.map((commenttt, index) => (
            <div className="comment1" key={index}>
              <p className="commentedBy">{commenttt.commentedByUser}</p>
              <p className="commentMessage">{commenttt.commentMessage}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
export default UpdateTicket;

// theese should be visibe in the ticket

// Title
// Ticket Type
// Department
// Comments (Can be a tabular representation)
// Status
// Assigned By
// Creation time
// Last updated time
