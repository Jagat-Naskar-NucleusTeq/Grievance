import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';

import {BrowserRouter} from 'react-router-dom';



export const setLoggedIn = (isLoggedIn) => {
  let l =  isLoggedIn ? "true" : "false";
  sessionStorage.setItem("isLoggedIn",l);
};


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
     <BrowserRouter>
       <App />
     </BrowserRouter>
);

