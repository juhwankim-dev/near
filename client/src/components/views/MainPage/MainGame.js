import React from 'react';
import './MainGame.scss';

const MainGame = () => {



  return (
  <div >
    {/* <h1>Play with Me</h1> */}
    <div class="background_game">
    <button class="oxquiz">
    


    </button>
    <button class="fingerquiz"></button>

  {/* ox퀴즈 위치 버튼 4k화면 사이즈 기준으로 맞춤 추후 다시 수정해야할듯*/}
    <div class="playleftbtn">
      <a href="#">
    <span>play</span>
    </a>
    <span class="ok">play</span>
    </div>

  {/* 낱말퀴즈 위치 버튼 4k화면 사이즈 기준으로 맞춤 추후 다시 수정해야할듯*/}
    <div class="playrightbtn">
      <a href="http://localhost:3000/teamplay">
    <span>play</span>
    </a>
    <span class="ok">play</span>
    </div>
   

    </div>
  </div>
  );
};

export default MainGame;
