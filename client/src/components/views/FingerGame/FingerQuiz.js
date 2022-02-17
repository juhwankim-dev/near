import React from 'react';
import { Container } from 'react-bootstrap';
import './FingerQuiz.scss';
import NavBar from '../NavBar/NavBar'

const FingerQuiz = () => {
  return (
    <div> 
    <NavBar></NavBar>
    <Container 
    style={{
   display:'flex', justifyContent: 'center' ,width: '100%', overflow: 'hidden'}} 
   className='oxquizstartbg'>

    <div className='oxquizstartimg2'>
    <div className='playbtn'>
      <a style={{ backgroundColor: '#5b9cff' }} href="/fingerquiz2">
    <span>play</span>
    </a>
    <span className="ok">play</span>
    </div></div> 
    </Container>
     
  </div>
    );
};
// background-size: 550px;
// //버튼 내부 이미지 사이즈 조정
// box-shadow: 0 1px 0 0 rgba(0, 0, 0, .1);
// height: 750px;
// right:13%;
// position: absolute;
// top: 300px;
// width: 575px;

export default FingerQuiz;
