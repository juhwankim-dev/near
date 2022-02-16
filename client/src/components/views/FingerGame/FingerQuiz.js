import React, { useState, useEffect} from 'react';
import './Finger.css';
import { Container } from 'react-bootstrap';
import NavBar from '../NavBar/NavBar'
import { useHistory,  useNavigate, useParams } from 'react-router-dom';

const FingerQuiz = () => {
 
  var imgArray= new Array();
  imgArray[0]="game/코로나.gif";  //사진
  imgArray[1]="game/코로나.gif";   //사진
  imgArray[2]="game/코로나.gif";   //사진
  imgArray[3]="game/코로나.gif";   //사진

  
function showImage()
{
  var imgNum=Math.round(Math.random()*3);
  var objImg=document.getElementById("introImg");
  objImg.src=imgArray[imgNum];
}


  useEffect(() => {
   start();  showImage();
  }, []); 
  
  const start = () => {
    const wordContainer = document.querySelector('.wordContainer'),
    word = wordContainer.querySelector('h1');
  
  const inputForm = document.querySelector('.inputForm'),
    inputText = inputForm.querySelector('input');
  
  const scoreContainer = document.querySelector('.scoreContainer'),
    scoreText = scoreContainer.querySelector('span');
  

  const wordArray ={ 
    코로나: 'game/코로나.gif',
  
  };

  let count = 0; // 틀린 횟수 확인용 변수
  let currentValue = ''; //현재 input value값 대입
  let innerTextWord = ''; // 현재 innerText 값 대입

  //랜덤 단어
  function randomWord() {
    
    let newWordArray = [];
    // console.log(wordArray);
    // console.log(Object.keys(wordArray));
    newWordArray.push(Object.keys(wordArray));
    let randomWord = newWordArray;
    word.innerText = randomWord;
  }

   
  
  // 틀린단어
  function validation() {
  
    currentValue = inputText.value;;
    innerTextWord = word.innerText;
  
    if (currentValue != innerTextWord) {
        inputText.classList.add('outLine');
      
    }else if (currentValue === '' || currentValue === innerTextWord) {
        inputText.classList.remove('outLine');
    }
    inputForm.addEventListener("submit", answerSubmit);
  
  }
  
  
  // 점수 추가
  function addScore() {
  
    let score = 0;
    scoreText.innerText = parseInt(scoreText.innerText) + 20;
    
    if (scoreText.innerText >= 20) {
        alert('정답👏');
        window.location.reload();
    }
  }
  
  
  
  //정답확인
  function answerSubmit(e) {
    e.preventDefault();
    randomWord();
    inputText.value = "";   
    if (currentValue === innerTextWord) {
        
        addScore();
  
    } else {
      alert("틀렸어요😅");

    }
  }
  
  
  function init() {
    randomWord();
    inputText.addEventListener("input", validation);
  }
  init();
  
  
  
  
  ///////////
  
  
  
  const startBtn = document.querySelector('.startBtn');
  const teamBtn = document.querySelector('.teamBtn');
  const oxquizstartimg2 = document.querySelector('.oxquizstartimg2');
  
  const gameContainer = document.querySelector('.gameContainer'),
      clockTitle = gameContainer.querySelector('h2');
  
  // startBtn 클릭이벤트
  
  startBtn.addEventListener('click', startGame);

  function startGame() {
      gameContainer.classList.add('showing');
      gameContainer.classList.remove('hiding');
      startBtn.remove();
      teamBtn.remove();
      oxquizstartimg2.remove();
      startClock();
  }
  
  // 시간 설정
  
  let seconds = 0,
      minutes = 0,
      hours = 0;
  
  function startClock() {
      seconds++;
      if (seconds >= 60) {
          seconds = 0;
          minutes++;
          if (minutes >= 60) {
              minutes = 0;
              hours++;
          }
      }
      
      clockTitle.innerText = `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;
  
      timer();
  }
  function timer() {
      setTimeout(startClock, 1000);
  }
  
  
  };
return(
  <>
  <NavBar></NavBar>
  <Container 
    style={{display:'flex', justifyContent: 'center' ,width: '100%', overflow: 'hidden'}} className='oxquizstartbg2'>
    <div class="wrapper">
    <div class="container">
      <div className='oxquizstartimg2'></div>
      <div class="startBtn">
        <button>Single</button>
      </div>
      <div class="teamBtn">
          <button>team</button>
      </div>
      <div class="gameContainer hiding">
      <div class="clock">
        <h2 style={{marginTop: '90px', backgroundColor: 'white', fontWeight:'bold', }}> 00:00:00</h2>
      </div>
      <div class="scoreContainer">
        <h3 style={{marginTop: '15px', fontWeight:'bold', fontSize:'40px' }}>score<span>0</span> </h3>
      </div>
      <div onload="showImage()">
        <img style={{marginTop: '15px', width:'360px', height:'320px', borderRadius:'10px' }} id="introImg" border="0" /></div> 
      <div style={{color:'rgb(176, 212, 255)', fontSize: '1px'  }} class="wordContainer">
        <h1 style={{fontSize: 'small' }}>start</h1>
      </div>
      <div class="inputContainer">
          <form style={{marginTop: '16px', fontWeight:'bold', fontSize:'32px' }} class="inputForm">
              <input type="text"/>
          </form>
      </div>
      </div>
  </div>
  </div>
  </Container>
  </>
)
};
export default FingerQuiz;