// // rfce
import axios from 'axios';
import React from 'react';
import MainHeader from './MainHeader';
import Footer from '../Footer/Footer';

function MainPage(props) {

  return (
    <div style={{
      // display:'flex', justifyContent: 'center', alignItems: 'center'
      // ,width: '100%', height: '100vh'
    }}>
      
      <MainHeader></MainHeader>
      메인페이지
    </div>
  );
}

export default MainPage;
