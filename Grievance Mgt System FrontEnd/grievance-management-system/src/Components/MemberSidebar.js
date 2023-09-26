import React from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import { Menu } from "antd";
import "../Components/style/AdminSidebar.css";
import Header from "../Components/Header";

import {
  HomeFilled,
  DeleteFilled,
  EyeFilled,
  MehFilled,
  PoweroffOutlined,
  MessageFilled,
  PlusCircleFilled,
  FileAddFilled,
  SecurityScanOutlined,
} from "@ant-design/icons";



function MemberSidebar() {
  let navigatee = useNavigate();

  const handleLogout = () => {
    // localStorage.clear();
    sessionStorage.clear();
    navigatee("/");
  };
  return (
    <>
    <Header name={sessionStorage.getItem("session_user_name")} role="member" />
      <div className="sidebar-parent">
        <div className="menu">
          <Menu
            items={[
              { label: <Link to="memberProfile">Profile</Link>, icon: <HomeFilled /> },
              {
                label: <Link to="tickets">Tickets</Link>,
                icon: <MehFilled />,
              },
              {
                label: <Link to="createTicket">Create Ticket</Link>,
                icon: <PlusCircleFilled />,
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
          <Outlet />
        </div>
      </div>
    </>
  );
}


export default MemberSidebar;
