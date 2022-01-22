import React, { useState, useEffect } from 'react';
import { withRouter, useHistory } from 'react-router-dom';
// import RegisterPage from '../../components/Accounts/Register';
import LoginPage from '../../components/Accounts/Login';
import Button from '../../components/NavBar/Button';
import toast, { Toaster } from 'react-hot-toast';
// import './Account.scss';
import '../../trash/LandingPage/Account.css';


function Login() {
  
// function Login({ history }) {
//   if (localStorage.getItem('user')) {
//     history.goBack();
//   }

  let history = useHistory();

  return (
    <div>
      <Toaster
        position="top-center"
        reverseOrder={true}
        toastOptions={{
          duration: 1000,
          style: {
            border: '1px solid #713200',
            padding: '16px',
            margin: '10vh',
            color: '#713200',
          },
        }}
      />
      <div>
      <LoginPage />
      </div>
      
      {/* <Button/> */}
    </div>
      );
    }

export default Login;