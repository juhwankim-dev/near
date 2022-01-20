import React, { useState, useEffect } from 'react';
import { withRouter } from 'react-router-dom';
import RegisterPage from '../../components/Accounts/Register';
import LoginPage from '../../components/Accounts/Login';
import toast, { Toaster } from 'react-hot-toast';
// import './Account.scss';
import './Account.css';


function Account({ history }) {
  if (localStorage.getItem('user')) {
    history.goBack();
  }


  // const [isActive, setActive] = useState(false);
  // const toggleClass = () => {
  //   setActive(!isActive);
  //   if (isActive) {
  //     toast.success('로그인 페이지 입니다.');
  //   } else {
  //     toast.success('회원가입 페이지 입니다.');
  //   }
  // };

  // const [goLogin, setgoLogin] = useState(false);
  // const goLoginHandler = () => {
  //   const stategoLogin = goLogin;
  //   setgoLogin(!stategoLogin);
  // };

  


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
  

      {/* <button onClick={goLoginHandler} className="account__gologinbtn">
            회원가입
          </button> */}
        
        {/* <div
          className={`${
            isActive ? 'container right-panel-active' : 'container'
          } ${goLogin ? 'containeractive' : ''}`}
          id="container"
        > */}
          {/* <RegisterPage toggleClass={toggleClass} />
          <LoginPage /> */}


          {/* <div className="overlay-container">
            <div className="overlay">
              <div className="overlay-panel overlay-left">
                <button
                  className="account__button ghost"
                  id="signIn"
                  onClick={toggleClass}
                >
                  SIGN IN
                </button>
              </div>
              <div className="overlay-panel overlay-right">
                <button
                  className="account__button ghost"
                  id="signUp"
                  onClick={toggleClass}
                >
                  SIGN UP
                </button>
              </div>
            </div>
          </div> */}

          
        </div>
      </div>
    // </div>
  );
}

export default withRouter(Account);
