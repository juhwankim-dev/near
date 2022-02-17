import React from 'react';
import './MainGame.scss';
import { Container } from 'react-bootstrap';

const MainGame = () => {



  return (
  <div>
    <div class="background_game">
    <button class="oxquiz">
    
    </button>
    <button class="fingerquiz"></button>

  {/* 낱말퀴즈 위치 버튼 4k화면 사이즈 기준으로 맞춤 추후 다시 수정해야할듯*/}
    <div class="playleftbtn">
      <a href="http://i6d203.p.ssafy.io/fingerquiz">
    <span>play</span>
    </a>
    <span class="ok">play</span>
    </div>

  {/* ox퀴즈 위치 버튼 4k화면 사이즈 기준으로 맞춤 추후 다시 수정해야할듯*/}
    <div class="playrightbtn">
      <a href="http://i6d203.p.ssafy.io/oxquiz/start/cnt">
    <span>play</span>
    </a>
    <span class="ok">play</span>
    </div>
   

    </div>
  </div>
  );
};

export default MainGame;
