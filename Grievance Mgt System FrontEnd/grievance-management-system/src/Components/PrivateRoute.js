import React from "react";
import { Navigate, Outlet, Route, Routes } from "react-router-dom";

const PrivateRoute = () => {
  const isLoggedInng = sessionStorage.getItem("isLoggedIn");

  if (isLoggedInng == "true") {
    return <Outlet />;
  } else {
    return <Navigate to={"/"} />;
  }
};

export default PrivateRoute;
