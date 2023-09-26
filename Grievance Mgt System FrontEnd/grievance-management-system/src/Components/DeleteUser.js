import React, { useState, useEffect } from "react";
import "../Components/style/DeleteUser.css";

function DeleteUser() {
  const [users, setUsers] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);

  const handleNextPage = () => {
    setCurrentPage(currentPage + 1);
  };

  const handlePreviousPage = () => {
    if (currentPage > 0) {
      setCurrentPage(currentPage - 1);
    }
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
  
  useEffect(() =>{
    getAllUsers(currentPage)
  },[currentPage]);

  const handleDelete = (id) => {
    fetch(`http://localhost:8080/api/users/deleteUser/${id}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (response.ok) {
          setUsers((prevUsers) => prevUsers.filter((user) => user.id !== id));
        } else {
          console.error("Error deleting user:", response.statusText);
        }
      })
      .catch((error) => console.error("Error deleting user:", error));
  };
  const session_userName = sessionStorage.getItem("session_user_name");

  const userTable = (
    <table className="user-table">
      <thead>
        <tr>
          <th>User ID</th>
          <th>Name</th>
          <th>Username</th>
          <th>User Type</th>
          <th>Department</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {users.map((user) => (
          <tr key={user.id}>
            <td>{user.id}</td>
            <td>{user.name}</td>
            <td>{user.userName}</td>
            <td>{user.userType}</td>
            <td>{user.departmentName}</td>
            <td>


            {session_userName === user.userName ? (
                <button className="delete-button" disabled>
                  Delete
                </button>
              ) : (
                <button
                  className="delete-button"
                  onClick={() => handleDelete(user.id)}
                >
                  Delete
                </button>
              )}


            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );

  return <>
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
          onClick={handleNextPage}
          disabled={currentPage > users.length / 5}
        >
          Next
        </button>
      </div>
    </div>;
    </>
}

export default DeleteUser;
