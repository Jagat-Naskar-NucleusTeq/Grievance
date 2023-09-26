import React, { useState, useEffect } from 'react';
import '../Components/style/DeleteDepartment.css'; 
import CustomAlert from './CommonComponents/CustomAlert';
import { Link, Outlet } from 'react-router-dom';

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
  const [departmentList, setDepartmentList] = useState([{deptId:'', deptName:''}]);
  // const backendUrl = 'http://localhost:8080/api/dept/allDepartment';

  // useEffect(() => {
  //   // Fetching department data 
  //   fetch(backendUrl)
  //     .then((response) => response.json())
  //     .then((data) => setDepartmentList(data))
  //     .catch((error) => console.error('Error fetching data:', error));
  // }, [backendUrl]);

  const getAllDept = ((currentPage)=>{
    fetch(`http://localhost:8080/api/dept/allDepartment/${currentPage}`)
      .then((response) => response.json())
      .then((data) => setDepartmentList(data))
      .catch((error) => console.error("Error:", error));
  });
  
  useEffect(() =>{
    getAllDept(currentPage)
  },[currentPage]);

  const handleDelete = (deptName) => {
    fetch(`http://localhost:8080/api/dept/deleteDept/${deptName}`, {
      method: 'POST',
    })
      .then((response) => {
        if (response.ok) {
          setDepartmentList((prevDepartments) =>
            prevDepartments.filter((dept) => dept.deptName !== deptName)
          );
          
        } else {
          console.error('Error deleting department:', response.statusText);
        }
      })
      .catch((error) => console.error('Error deleting department:', error));
  };

  

  
  const departmentTableRows = departmentList.map((department, index) => (
    <tr key={index}>
      <td>{department.deptId}</td>
      <td>{department.deptName}</td>
      <td>
        <button onClick={() => handleDelete(department.deptName)} className="delete-button">
          Delete
        </button>
      </td>
    </tr>
  ));

  return (
    <>
    <Outlet/>
    <div className='DD-addDept-btn'>
    <Link to='createDept' className='add-dept-btn'>
        Add Department
      </Link>
      </div>
    <div className="DD-department-list">
      <table className="DD-table">
        <thead className="DD-table-head">
          <tr className="DD-table-row">
            <th>Department Id</th>
            <th>Department Name</th>
            <th>Edit</th>
          </tr>
        </thead>
        <tbody>
          {departmentTableRows}
        </tbody>
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
    
    </>
  );
}

export default AllDepartment;