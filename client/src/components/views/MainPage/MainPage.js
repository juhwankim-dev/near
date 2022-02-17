// // rfce
import React from 'react';
import MainHeader from './MainHeader';
import MainStudy from './MainStudy';
import MainGame from './MainGame';
import NavBar from '../NavBar/NavBar';
import { animateScroll as scroll, } from 'react-scroll'
import './MainPage.scss';

function MainPage(props) {


  return (
    <div style={{ 
      // display:'flex', justifyContent: 'center', alignItems: 'center'
      // ,width: '100%', height: '100vh'
    }}>
      <NavBar></NavBar>
      <MainHeader></MainHeader>
      <div style={{ 
      display:'flex', justifyContent: 'center', alignItems: 'center', backgroundColor: 'white'
      ,width: '100%', height: '11vh'
    }}>
      <a class="mouses" onClick={() => scroll.scrollMore(1200)}></a><p onClick={() => scroll.scrollMore(1200)}>Play with Me</p>
      </div>
      <MainGame></MainGame>
      <div style={{ 
      display:'flex', justifyContent: 'center', alignItems: 'center', backgroundColor: 'white'
      ,width: '100%', height: '11vh'
    }}>
      <a class="mouses" onClick={() => scroll.scrollMore(1150)}></a><p onClick={() => scroll.scrollMore(1150)}>Study with Me</p>
      </div>
      <MainStudy></MainStudy>
    </div>
  ); 
}

export default MainPage;
