/* eslint-disable */

import React from 'react'
import { BrowserRouter as Router, Routes, Route,} from "react-router-dom";

import LandingPage from './components/views/LandingPage/LandingPage';
import LoginPage from './components/views/LoginPage/LoginPage';
import RegisterPage from './components/views/RegisterPage/RegisterPage';
import MainPage from './components/views/MainPage/MainPage';
import SignPage from './components/views/ContentPage/SignPage/SignPage';

import SignDetail3 from './components/views/ContentPage/SignPage/SignDetail3';
import FingerPage from './components/views/ContentPage/FingerPage/FingerPage';
import FingerPage2 from './components/views/ContentPage/FingerPage/FingerPage2';
import MyVoca from './components/views/MyVocaPage/MyVoca';
import MyStudy from './components/views/MyProfilePage.js/MyStudy';
import Profile from './components/views/MyProfilePage.js/MyProfile';
import VocaDetail from './components/views/MyVocaPage/VocaDetail3';

// import NavBar from './components/views/NavBar/NavBar';
import OXQuizStart from './components/views/OXQuiz/OXQuizStart';
import OXQuizStartCnt from './components/views/OXQuiz/OXQuizStartCnt';
import OXQuiz from './components/views/OXQuiz/OXQuiz';
import FingerQuiz from './components/views/FingerGame/FingerQuiz';
import OXQuiz2 from './components/views/OXQuiz/OXQuiz2';
import OXQuiz3 from './components/views/OXQuiz/OXQuiz3';
import OXQuizEnd from './components/views/OXQuiz/OXQuizEnd';

import Auth from './hoc/auth' //1.해당페이지에 들어올 수 있는 유저인지 확인


// import Game1 from './components/views/FingerGame/Game1';

function App() {
  return ( 
    <Router>
      
      <Routes>
        {/* <Route path="/" element={<LandingPage /> } /> */}
        {/* <Route exact path="/" element={<LandingPage /> } /> */}
        <Route path="/main" element={<MainPage />} />
        {/* <Route path="/game" element={<Game />} />
        <Route path="/gameroom" element={<GameRoom />} /> */}
        <Route path="/fingerquiz" element={<FingerQuiz />} />
        <Route path ="/oxquiz/start" element={<OXQuizStart />} />
        <Route path ="/oxquiz/start/cnt" element={<OXQuizStartCnt />} />
        <Route path ="/oxquiz" element={<OXQuiz />} />
        <Route path ="/oxquiz2" element={<OXQuiz2 />} />
        <Route path ="/oxquiz3" element={<OXQuiz3 />} />
        <Route path ="/oxquiz/end" element={<OXQuizEnd />} />

        <Route path="/sign" element={<SignPage />} />
        <Route path ="/sign/:i" element={<SignDetail3 />} />
        <Route path ="/finger/1" element={<FingerPage />} />
        <Route path ="/finger/2" element={<FingerPage2 />} />
        {/* <Route path ="/finger/:i" element={<FingerDetail />} /> */}
        <Route path ="/myvoca" element={<MyVoca />} />
        <Route path ="/myvoca/:i" element={<VocaDetail />} />
        <Route path ="/mystudy" element={<MyStudy />} />
        <Route path ="/profile" element={<Profile />} />
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
