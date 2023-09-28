import React, { useState, useEffect } from "react";
import "../Components/style/Ticket.css";
import axios from "axios";
import CustomAlert from "./CommonComponents/CustomAlert";

function Ticket() {
  const [ticketType, setTicketType] = useState("");
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [assignTo, setAssignTo] = useState("");
  const [status, setStatus] = useState("select");
  const [comment, setComment] = useState("select");
  const [message, setMessage] = useState("");
  const [showAlert, setShowAlert] = useState(false);

  const [ticketTypeError, setTicketTypeError] = useState("");
  const [titleError, setTitleError] = useState("");
  const [descriptionError, setDescriptionError] = useState("");
  const [assignToError, setAssignToError] = useState("");

  const [departmentList, setDepartmentList] = useState([
    { deptId: "", deptName: "" },
  ]);
  const ticketTypeList = ["Select Ticket Type", "Feedback", "Grievance"];
  const statusList = ["Open", "Being Addressed", "Resolved"];
  //reset all the fields
  const resetAllFields = () => {
    setTicketType("");
    setTitle("");
    setDescription("");
    setAssignTo("select Detartment");
    setStatus("select status");
  };

  //reset all error
  const resetFormError = () => {
    setTicketTypeError("");
    setTitleError("");
    setDescriptionError("");
    setAssignToError("");
  };

  const handleShowAlert = () => {
    setShowAlert(true);
  };
  const handleCloseAlert = () => {
    setShowAlert(false);
  };

  //setting deptList from backend
  useEffect(() => {
    fetch("http://localhost:8080/api/dept/allDepartment")
      .then((response) => response.json())
      .then((data) => setDepartmentList((previous) => [...previous, ...data]))
      .catch((error) => console.error("Error:", error));
  }, []);

  //handle submit form
  const handleSubmit = (e) => {
    e.preventDefault();
    let isValid = true;

    if (!ticketType || ticketType === "Select Ticket Type") {
      setTicketTypeError("Please enter a ticket type.");
      isValid = false;
    } else {
      setTicketTypeError("");
    }

    if (!title) {
      setTitleError("Please enter a title.");
      isValid = false;
    } else {
      setTitleError("");
    }

    if (!description) {
      setDescriptionError("Please enter a description.");
      isValid = false;
    } else {
      setDescriptionError("");
    }

    if (!assignTo || assignTo === "Select Department" || assignTo === "") {
      setAssignToError("Select your department");
      isValid = false;
    }

    if (isValid) {
      axios
        .post("http://localhost:8080/api/tickets/addTicket", {
          ticketTitle: title,
          ticketType: ticketType,
          ticketStatus: "Open",
          ticketDescription: description,
          deptName: assignTo,
          senderEmail: btoa(localStorage.getItem("session_user_name")),
        })
        .then((res) => {
          setMessage("Ticket added successfully!!!");
          handleShowAlert();
          resetFormError();
          resetAllFields();
        });
    }
  };

  return (
    <div className="Ttickets-container">
      <form className="Tticket-form" onSubmit={handleSubmit}>
        <h2>Add Ticket</h2>
        <div className="Tsub-structure">
          <div className="Tsub-structure1">
            <label className="TticketType">
              Ticket Type<span className="astrix">*</span>
            </label>

            <select
              id="ticketType"
              name="ticketType"
              value={ticketType}
              onChange={(e) => setTicketType(e.target.value)}
            >
              {ticketTypeList.map((e) => (
                <option value={e}>{e}</option>
              ))}
            </select>

            {ticketTypeError && <p className="Terror">{ticketTypeError}</p>}

            <label className="Ttitle">
              Title<span className="astrix">*</span>
            </label>
            <input
              type="text"
              id="title"
              name="title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />
            {titleError && <p className="Terror">{titleError}</p>}

            <label className="Tdescription">
              Description<span className="astrix">*</span>
            </label>
            <textarea
              id="description"
              name="description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            ></textarea>
            {descriptionError && <p className="Terror">{descriptionError}</p>}
          </div>

          <div className="Tsub-structure1">
            <label className="TassignTo">
              Assign To<span className="astrix">*</span>
            </label>
            <select
              id="assignTo"
              name="assignTo"
              value={assignTo}
              onChange={(e) => setAssignTo(e.target.value)}
            >
              <option value="" disabled>
                Select a Department
              </option>
              {departmentList.map((e) => (
                <option key={e.id} value={e.deptName}>
                  {e.deptName}
                </option>
              ))}
            </select>
            {assignToError && <p className="Terror">{assignToError}</p>}

            <label className="Tstatus">Status</label>
            <select
              id="status"
              name="status"
              value={status}
              disabled
              onChange={(e) => setStatus(e.target.value)}
            >
              {statusList.map((e) => (
                <option value={e}>{e}</option>
              ))}
            </select>

            <label className="Tcomment">Comment</label>
            <select
              id="comment"
              name="comment"
              value={comment}
              disabled
              onChange={(e) => setComment(e.target.value)}
            ></select>
          </div>
        </div>
        <button type="submit">Submit</button>
      </form>

      <div>
        {showAlert && (
          <CustomAlert message={message} handleCloseAlert={handleCloseAlert} />
        )}
      </div>
    </div>
  );
}

export default Ticket;
