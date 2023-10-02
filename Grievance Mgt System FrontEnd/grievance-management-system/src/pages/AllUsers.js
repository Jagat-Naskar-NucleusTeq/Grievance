import React, { useEffect, useState } from "react";
import "../assets/css/AllUsers.css";

function AllUsers() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/users/allUsers")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => setUsers(data))
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  const userTable = (
    <table className="user-table">
      <thead>
        <tr>
          <th>User ID</th>
          <th>Name</th>
          <th>Username</th>
          <th>User Type</th>
          <th>Department</th>
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
          </tr>
        ))}
      </tbody>
    </table>
  );

  return <div className="AU-main">{userTable}</div>;
}

export default AllUsers;
