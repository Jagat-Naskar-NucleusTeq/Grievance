import React from 'react';
import '../Components/style/Error404Page.css';

const Error404Page = () => {
  return (
    <div className="error-container">
      <h1 className='errorPAge'>404 - Page Not Found</h1>
      <p>Sorry, the page you are looking for does not exist.</p>
    </div>
  );
}

export default Error404Page;