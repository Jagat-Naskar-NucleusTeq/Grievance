import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// import AuthProvider from '../src/Components/AuthContext';
import {BrowserRouter} from 'react-router-dom';

//checking if logged in or not
// export const isLoggedIn = () => {
//   console.log("Jagat");
//   const sessionUserName = sessionStorage.getItem("session_user_name");
//   if(sessionUserName != null && sessionUserName !== ""){
//     return true;
//   }
//   else return false;
// }

export const setLoggedIn = (isLoggedIn) => {
  let l =  isLoggedIn ? "true" : "false";
  sessionStorage.setItem("isLoggedIn",l);
};


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  // <React.StrictMode>
     <BrowserRouter>
       <App />
     </BrowserRouter>
  // </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
