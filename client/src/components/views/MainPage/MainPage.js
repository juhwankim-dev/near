// // rfce
import axios from 'axios';
import React from 'react';
import MainHeader from './MainHeader';
import Footer from '../Footer/Footer';
import MainStudy from './MainStudy';
import MainGame from './MainGame';
import NavBar from '../NavBar/NavBar';

function MainPage(props) {

  return (
    <div style={{ 
      // display:'flex', justifyContent: 'center', alignItems: 'center'
      // ,width: '100%', height: '100vh'
    }}>
      <NavBar></NavBar>
      <MainHeader></MainHeader>
      <MainGame></MainGame>
      <MainStudy></MainStudy>
      {/* <Footer></Footer> */}
    </div>
  ); 
}

export default MainPage;
