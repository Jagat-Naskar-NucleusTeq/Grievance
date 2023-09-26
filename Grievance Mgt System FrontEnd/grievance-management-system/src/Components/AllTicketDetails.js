import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import "../Components/style/AllTicketDetails.css";
import FullTicketModel from "./FullTicketModel";
import axios from "axios";
import UpdateTicket from "./UpdateTicket";
import { format } from "date-fns";

const AllTicketDetails = () => {
  const statusList = ["Open", "Being_Addressed", "Resolved"];
  const [showFullDetails, setShowFullDetails] = useState(false);
  const [showEditTicket, setShowEditTicket] = useState(false);
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [editButtonsDisabled, setEditButtonsDisabled] = useState(false);

  const [ticketList, setTicketList] = useState([]); // list of ticket
  const [selectedStatus, setSelectedStatus] = useState("");
  const [deptWise, setDeptWise] = useState("true");
  const [OwnWise, setOwnWise] = useState("false");

  const [currentPage, setCurrentPage] = useState(0);
  const [itemsPerPage] = useState(3); // Number of items per page

  const handleNextPage = () => {
    setCurrentPage(currentPage + 1);
  };

  const handlePreviousPage = () => {
    if (currentPage > 0) {
      setCurrentPage(currentPage - 1);
    }
  };

  const fetchData = async () => {
    try {
      const dataToSend = {
        email: btoa(sessionStorage.getItem("session_user_name")),
        departmentBased: deptWise,
        assignByOwn: OwnWise,
        filterStatus: selectedStatus,
        pageNumber: currentPage,
      };

      const response = await axios.post(
        "http://localhost:8080/api/tickets/getAllTicket",
        dataToSend
      );

      if (response.status !== 200) {
        throw new Error("Network response was not ok");
      }

      const data = response.data;
      setTicketList(data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const openEditModel = (ticket) => {
    setSelectedTicket(ticket);
    setShowEditTicket(true);
  };
  const openModal = (ticket) => {
    setSelectedTicket(ticket);
    setShowFullDetails(true);
  };

  const closeModal = () => {
    setShowFullDetails(false);
  };
  const closeEditModel = () => {
    setShowEditTicket(false);
  };

  const handleEdit = (ticket) => {
    setSelectedTicket(ticket);
    openEditModel(ticket);
  };

  useEffect(() => {
    fetchData();
  }, [OwnWise, deptWise, selectedStatus, currentPage]);

  const handleFilterChange = (e) => {
    setCurrentPage(0);
    setSelectedStatus(e.target.value);
  };

  const AdminRole = sessionStorage.getItem("Admin_Role");

  return (
    <div className="full-details">
      <div className="all-button">
        <div>
          {AdminRole === "admin" && (
            <button
              className="AT-allTickets"
              onClick={() => {
                setCurrentPage(0);
                setOwnWise("false");
                setDeptWise("false");
                setSelectedStatus("Select status");
                setEditButtonsDisabled(true);
              }}
            >
              All Tickets
            </button>
          )}
        </div>

        <button
          className="AT-deptTickets"
          onClick={() => {
            setCurrentPage(0);
            setDeptWise("true");
            setOwnWise("false");
            setSelectedStatus("Select status");
            setEditButtonsDisabled(false);
          }}
        >
          Dept Based
        </button>

        <button
          className="AT-myTickets"
          onClick={() => {
            setCurrentPage(0);
            setDeptWise("false");
            setOwnWise("true");
            setSelectedStatus("Select status");
            setEditButtonsDisabled(false);
          }}
        >
          My Tickets
        </button>

        <select
          id="statusFilter"
          className="statusFilter"
          name="filter"
          value={selectedStatus}
          onChange={handleFilterChange}
        >
          <option value="">Select status</option>
          {statusList.map((e) => (
            <option key={e} value={e}>
              {e}
            </option>
          ))}
        </select>
      </div>
      {ticketList.length > 0 ? (
        <table>
          <thead>
            <tr>
              {/* <th>Ticket ID</th> */}
              <th>Department</th>
              <th>Ticket Status</th>
              <th>Created By</th>
              <th>Last Updated</th>
              <th>Action</th>
              <th>Expand</th>
            </tr>
          </thead>
          <tbody>
            {ticketList.map((ticket) => {
              const formattedDateTime = format(
                new Date(ticket.updationTime),
                "EEE, d MMM , yyyy  h:mm a"
              );

              return (
                <tr key={ticket.ticketId}>
                  <td>{ticket.departmentName}</td>
                  <td>{ticket.ticketStatus}</td>
                  <td>{ticket.createdBy}</td>
                  <td>{formattedDateTime}</td>

                  <td>
                    <button
                      className="edit-btn"
                      onClick={() => handleEdit(ticket)}
                    >
                      Edit
                    </button>
                  </td>
                  <td>
                    <button
                      className="expand-btn"
                      onClick={() => openModal(ticket)}
                    >
                      View Details
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      ) : (
        <p className="AT-Empty-list">No Data Found...</p>
      )}
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
          disabled={currentPage > ticketList.length / 5}
        >
          Next
        </button>
      </div>

      {showEditTicket && (
        <UpdateTicket
          id={selectedTicket.ticketId}
          onClose={closeEditModel}
          editButtonsDisabled={editButtonsDisabled}
        />
      )}

      {showFullDetails && (
        <FullTicketModel ticket={selectedTicket} onClose={closeModal} />
      )}
    </div>
  );
};

export default AllTicketDetails;
