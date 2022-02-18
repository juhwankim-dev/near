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
      display:'flex', flexDirection:'column-reverse',justifyContent: 'center', alignItems: 'center', backgroundColor: 'white'
      ,width: '100%', height: '15.5vh'
    }}>
      <a class="mouses" onClick={() => scroll.scrollMore(1400)}></a>
      <p onClick={() => scroll.scrollMore(1400)}>Play!</p>
      </div>
      <MainGame></MainGame>
      <div style={{ 
      display:'flex', flexDirection:'column-reverse', justifyContent: 'center', alignItems: 'center', backgroundColor: 'white'
      ,width: '100%', height: '16vh'
    }}>
      <a class="mouses" onClick={() => scroll.scrollMore(1400)}></a><p onClick={() => scroll.scrollMore(1400)}>Study!</p>
      </div>
      <MainStudy></MainStudy>
    </div>
  ); 
}

export default MainPage;
