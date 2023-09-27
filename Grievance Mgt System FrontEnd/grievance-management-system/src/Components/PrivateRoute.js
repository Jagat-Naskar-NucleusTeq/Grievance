import React from "react";
import { Navigate, Outlet} from "react-router-dom";

const PrivateRoute = () => {
  const isLoggedInng =localStorage.getItem("LoggendIn22");

  if (isLoggedInng === "true") {
    return <Outlet />;
  } else {
    return <Navigate to={"/"} />;
  }
};

export default PrivateRoute;
