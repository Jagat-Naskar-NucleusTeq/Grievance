// import React, { useEffect } from "react";
// import "../Components/style/AllUsers.css";
// import { useState } from "react";

// function AllUsers() {
//   const [users, setUsers] = useState([]);

//   useEffect(() => {
//     // Step 2: Fetch user data from the backend API when the component mounts
//     fetch("http://localhost:8080/api/users/allUsers") // Replace with your actual backend API URL
//       .then((response) => {
//         if (!response.ok) {
//           throw new Error("Network response was not ok");
//         }
//         return response.json();
//       })
//       .then((data) => setUsers(data))
//       .catch((error) => console.error("Error fetching data:", error));
//   }, []);

//   // Step 3: Map over the user data to render each user's information in cards
//   const userCards = users.map((user) => (
//     <div key={user.id} className="user-card">
//       <h2 className="user-title">{user.name}</h2>
//       <div className="user-body">
//         <p>
//           <strong>UserId:</strong> {user.id}
//         </p>
//         <p>
//           <strong>Username:</strong> {user.userName}
//         </p>
//         <p>
//           <strong>User Type:</strong> {user.userType}
//         </p>
//         <p>
//           <strong>Department:</strong> {user.departmentName}
//         </p>
//         {/* Password is not displayed in the card */}
//       </div>
//     </div>
//   ));

//   return (
//     <div className="AU-main">
//       <div className="user-list">{userCards}</div>
//     </div>
//   );
// }

// export default AllUsers;



import React, { useEffect, useState } from "react";
import "../Components/style/AllUsers.css";

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
