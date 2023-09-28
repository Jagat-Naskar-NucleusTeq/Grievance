import React, { useState, useEffect } from "react";
import "../Components/style/DeleteDepartment.css";
import CustomAlert from "./CommonComponents/CustomAlert";
import { Link, Outlet } from "react-router-dom";
import ConfirmationBox from "./CommonComponents/ConfirmationBox";

function AllDepartment() {
  const [delete1, setDelete1] = useState(false);
  const [currentPage, setCurrentPage] = useState(0);
  const [deleteState, setDeleteState] = useState("");
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [showAlert, setShowAlert] = useState(false);
  const [message, setMessage] = useState("");

  const handleShowAlert = () => {
    setShowAlert(true);
  };
  const handleCloseAlert = () => {
    setShowAlert(false);
  };

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
  }, [currentPage, delete1]);

  const handleConfirm = () => {
    handleDelete();
    setShowConfirmation(false);
  };

  const handleCancel = () => {
    setShowConfirmation(false);
  };

  const handleDelete = async () => {
    await fetch(`http://localhost:8080/api/dept/deleteDept/${deleteState}`, {
      method: "POST",
    })
      .then((response) => {
        if (response.ok) {
          setDepartmentList((prevDepartments) =>
            prevDepartments.filter((dept) => dept.deptName !== deleteState)
          );
          setMessage("Deleted Successfully...");
          handleShowAlert();
          setCurrentPage(0);
        } else {
          console.error("Error deleting department:", response.statusText);
        }
      })
      .catch((error) => console.error("Error deleting department:", error));
    setDelete1(false);
  };

  const openConfirmBox = (name) => {
    setShowConfirmation(true);
    setDeleteState(name);
  };

  const departmentTableRows =
    departmentList.length > 0 ? (
      departmentList.map((department, index) => (
        <tr key={index}>
          <td>{department.deptId}</td>
          <td>{department.deptName}</td>
          <td>
            <button
              onClick={() => openConfirmBox(department.deptName)}
              className="delete-button"
            >
              Delete
            </button>
          </td>
        </tr>
      ))
    ) : (
      <tr>
        <td colSpan="3">
          <p className="Data-not-available">
            No Data available in this page...
          </p>
        </td>
      </tr>
    );

  return (
    <>
      <Outlet />
      <div className="DD-addDept-btn">
        <Link to="createDept" className="add-dept-btn">
          Add Department
        </Link>
      </div>
      <div className="DD-department-list">
        <table className="DD-table">
          <thead className="DD-table-head">
            <tr className="DD-table-row">
              <th>Department Id</th>
              <th>Department Name</th>
              <th>Action</th>
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
            disabled={
              5 > departmentTableRows.length || departmentList.length === 0
            }
          >
            Next
          </button>
        </div>
        <div>
          {showConfirmation && (
            <ConfirmationBox
              message="Are you sure you want to proceed?"
              onConfirm={handleConfirm}
              onCancel={handleCancel}
            />
          )}
          {showAlert && (
            <CustomAlert
              message={message}
              handleCloseAlert={handleCloseAlert}
            />
          )}
        </div>
      </div>
    </>
  );
}

export default AllDepartment;
