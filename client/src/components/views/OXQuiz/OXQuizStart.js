import React from 'react';
import { Container } from 'react-bootstrap';
import './OXQuizStart.scss';
import NavBar from '../NavBar/NavBar'

const OXQuizStart = () => {
  return (
    <div> 
    <NavBar></NavBar>
    <Container 
    style={{
   display:'flex', justifyContent: 'center' ,width: '100%', overflow: 'hidden'}} 
   className='oxquizstartbg'>

    <div className='oxquizstartimg'>
    <div className='playbtn'>
      {/* <a href="https://i6d203.p.ssafy.io/oxquiz/start/cnt"> */}
      <a href="http://localhost:3000/oxquiz/start/cnt">
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

export default OXQuizStart;
