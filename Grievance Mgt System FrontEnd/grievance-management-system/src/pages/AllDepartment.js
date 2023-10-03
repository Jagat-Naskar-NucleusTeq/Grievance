import React, { useState, useEffect } from "react";
import "../assets/css/AllDepartment.css";

function AllDepartment() {
  const [currentPage, setCurrentPage] = useState(0);

  const handleNextPage = () => {
    setCurrentPage(currentPage + 1);
  };

  const handlePreviousPage = () => {
    if (currentPage > 0) {
      setCurrentPage(currentPage - 1);
    }
  };

  const [departmentList, setDepartmentList] = useState([
    { deptId: "", deptName: "" },
  ]);

  const getAllDept = (currentPage) => {
    fetch(`http://localhost:8080/api/dept/allDepartment/${currentPage}`)
      .then((response) => response.json())
      .then((data) => setDepartmentList(data))
      .catch((error) => console.error("Error:", error));
  };

  useEffect(() => {
    getAllDept(currentPage);
  }, [currentPage]);

  const departmentTableRows = departmentList.map((department) => (
    <tr key={department.deptId}>
      <td>{department.deptId}</td>
      <td>{department.deptName}</td>
    </tr>
  ));

  return (
    <div className="AD-department-list">
      <table className="AD-table">
        <thead className="AD-table-head">
          <tr className="table-row">
            <th>Department ID</th>
            <th>Department Name</th>
          </tr>
        </thead>
        <tbody>{departmentTableRows}</tbody>
      </table>
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
          disabled={currentPage > departmentTableRows.length / 5}
        >
          Next
        </button>
      </div>
    </div>
  );
}

export default AllDepartment;
