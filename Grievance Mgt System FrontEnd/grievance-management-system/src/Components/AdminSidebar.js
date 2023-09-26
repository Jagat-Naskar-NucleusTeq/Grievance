import React, { createContext } from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import { Menu } from "antd";
import "../Components/style/AdminSidebar.css";
import Header from "../Components/Header";

import {
  HomeFilled,
  DeleteFilled,
  EyeFilled,
  UserOutlined,
  PoweroffOutlined,
  MessageFilled,
  PlusCircleFilled,
  UserAddOutlined,
  SecurityScanOutlined,
  UsergroupDeleteOutlined,
} from "@ant-design/icons";
import AllTicketDetails from "./AllTicketDetails";

const AdminRole = createContext();


function AdminSidebar() {
  let navigatee = useNavigate();

  const handleLogout = () => {
      sessionStorage.clear();
      navigatee("/");
  };

  return (
    <>
    
    {sessionStorage.setItem("Admin_Role", 'admin')}
    <Header name={sessionStorage.getItem("session_user_name")} role="admin" />
      <div className="sidebar-parent">
        <div className="menu">
          <Menu
            items={[
              { label: <Link to="adminProfile">Profile</Link>, icon: <HomeFilled /> },
              {
                label: <Link to="addUser">Add User</Link>,
                icon: <UserAddOutlined />,
              },
              {
                label: <Link to="allUsers">All Users</Link>,
                icon: <UsergroupDeleteOutlined />,
              },
              {
                label: <Link to="tickets">Tickets</Link>,
                icon: <MessageFilled />,
              },
              {
                label: <Link to="createTicket">Create Ticket</Link>,
                icon: <PlusCircleFilled />,
              },
              {
                label: <Link to="viewDept">Departments</Link>,
                icon: <DeleteFilled />,
              },
              {
                label: <Link to="changePassword">Change Password</Link>,
                icon: <SecurityScanOutlined />,
              },
              {
                label: <span onClick={handleLogout}>LogOut</span>,
                icon: <PoweroffOutlined />,
                danger: true,
              },
            ]}
          />
        </div>
        <div className="admin-content">
          <Outlet/>
        </div>
      </div>
    </>
  );
}


export default AdminSidebar;