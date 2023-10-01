import React, { useEffect } from "react";
import { Navigate, Outlet, useLocation} from "react-router-dom";

const PrivateRoute = () => {
  const isLoggedInng =localStorage.getItem("LoggendIn22");

  const location = useLocation();

  useEffect(() => {
    if (isLoggedInng === "true") {
      localStorage.setItem("currentRoute", location.pathname);
    }
  }, [isLoggedInng, location.pathname]);

  if (isLoggedInng === "true") {
    return <Outlet />;
  } else {
    return <Navigate to={"/"} />;
  }
};

export default PrivateRoute;
