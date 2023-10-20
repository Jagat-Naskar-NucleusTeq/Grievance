import React, { useState, useEffect } from "react";
import "../assets/css/DeleteUser.css";
import ConfirmationBox from "../component/ConfirmationBox";
import CustomAlert from "../component/CustomAlert";
import DeleteUserIcon from "../assets/icons/delete-user-icon.svg";
import { Link } from "react-router-dom";

function DeleteUser() {
  const [users, setUsers] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);

  const [deleteState, setDeleteState] = useState("");
  const [showConfirmation, setShowConfirmation] = useState(false);

  const [showAlert, setShowAlert] = useState(false);
  const [message1, setMessage1] = useState("");

  const handleNextPage = () => {
    setCurrentPage(currentPage + 1);
  };

  const handlePreviousPage = () => {
    if (currentPage > 0) {
      setCurrentPage(currentPage - 1);
    }
  };

  const handleShowAlert = () => {
    setShowAlert(true);
  };
  const handleCloseAlert = () => {
    setShowAlert(false);
  };

  const getAllUsers = (currentPage) => {
    fetch(`http://localhost:8080/api/users/allUsers/${currentPage}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => setUsers(data))
      .catch((error) => console.error("Error fetching data:", error));
  };

  useEffect(() => {
    getAllUsers(currentPage);
  }, [currentPage]);

  const openConfirmBox = (name) => {
    setShowConfirmation(true);
    setDeleteState(name);
  };
  const handleConfirm = () => {
    handleDelete();
    setShowConfirmation(false);
  };

  const handleCancel = () => {
    setShowConfirmation(false);
  };

  const handleDelete = () => {
    fetch(`http://localhost:8080/api/users/deleteUser/${deleteState}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (response.ok) {
          setUsers((prevUsers) =>
            prevUsers.filter((user) => user.id !== deleteState)
          );
          setCurrentPage(0);
          setMessage1("Deleted Successfully...");
          handleShowAlert();
        } else {
          console.error("Error deleting user:", response.statusText);
        }
      })
      .catch((error) => console.error("Error deleting user:", error));
  };
  const session_userName = localStorage.getItem("session_user_name");

  const message =
    users.length === 0 ? (
      <p className="Data-not-available">Empty page...</p>
    ) : null;

  const userTable = (
    <table className="user-table">
      <thead>
        <tr>
          <th>User ID</th>
          <th>Name</th>
          <th>Username</th>
          <th>User Type</th>
          <th>Department</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody>
        {message}
        {users.map((user) => (
          <tr key={user.id}>
            <td>{user.id}</td>
            <td>{user.name}</td>
            <td>{user.userName}</td>
            <td>{user.userType}</td>
            <td>{user.departmentName}</td>
            <td>
                <button
                  className="delete-button"
                  onClick={() => openConfirmBox(user.id)}
                  disabled= {session_userName === user.userName}
                >
                  <img
                    src={DeleteUserIcon}
                    alt="Description"
                    style={{ width: "50%" }}
                  />
                </button>
              
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );

  return (
    <>
      <Link to="/users/addUser" className="add-dept-btn-link">
        <div className="DD-addDept-btn">Add User</div>
      </Link>
      <div className="DUmain-div">
        {userTable}
        <div>
          <button
            className="paging-btn"
            onClick={handlePreviousPage}
            disabled={currentPage === 0}
          >
            Prev
          </button>
          {currentPage + 1}
          <button
            className="paging-btn"
            disabled={5 > users.length || users.length <= 0}
            onClick={handleNextPage}
          >
            Next
          </button>
        </div>
      </div>
      <div>
        {showConfirmation && (
          <ConfirmationBox
            message="Are you sure to delete?"
            onConfirm={handleConfirm}
            onCancel={handleCancel}
          />
        )}

        {showAlert && (
          <CustomAlert message={message1} handleCloseAlert={handleCloseAlert} />
        )}
      </div>
    </>
  );
}

export default DeleteUser;
