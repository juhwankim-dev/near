/* eslint-disable */

import React from 'react'
import { BrowserRouter as Router, Routes, Route,} from "react-router-dom";

import LandingPage from './components/views/LandingPage/LandingPage';
import LoginPage from './components/views/LoginPage/LoginPage';
import RegisterPage from './components/views/RegisterPage/RegisterPage';
import MainPage from './components/views/MainPage/MainPage';
import NavBar from './components/views/NavBar/NavBar';
import SignPage from './components/views/ContentPage/SignPage/SignPage';

import Auth from './hoc/auth' //1.해당페이지에 들어올 수 있는 유저인지 확인


function App() {
  return ( 
    <Router>
    <NavBar>
      </NavBar>   
      <Routes>
        <Route exact path="/" element={<LandingPage /> } />
        {/* <Route exact path="/" element={<LandingPage /> } /> */}
        <Route path="/main" element={<MainPage />} />
        <Route exact path="/sign" element={<SignPage />} />
 

        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        {/* <Route exact path="/" element={Auth(<LandingPage />, null) } />
        <Route path="/login" element={Auth(<LoginPage />,false)} />
        <Route path="/register" element={Auth(<RegisterPage />)} />
        이거 Auth활용해서 페이지 제한 둬야하는데 당장에는 Auth연결을 백이랑 안해서 그런지 페이지 안떠서 일단 주석처리함 */}
      </Routes>
      
    </Router>
  );
} 

export default App;
// 참고 강의는 v5버전으로 되어있어서 위와 같이 v6버전으로 고침
