// 구성: SignPage/SignCard/SignCardDetail

import React, { useState } from "react";
import { Pagination } from 'react-bootstrap';
import Data from './SignData.js';
import Paging from './Paging.js';
import SignCard from './SignCard.js';
import { useNavigate } from "react-router-dom";
// import './SignPage.css';
import './SignPage.scss';
import vid from '../../../../assets/NIA_SL_WORD0687_SYN02_F.mp4';
import abc from '../../../../assets/abc.PNG';

function SignPage(){
  let [signcards, setsigncard] = useState(Data);
  let [modal, setmodal] = useState(false);
  let navigate = useNavigate();
  
  function handleClick() {
    navigate("/home");
  }
 
  return (
    
    <div className="container">
      {/* <h1>수어 배우기</h1> */}
      <div className='nemo'> 
      
      { 
          modal === true 
          ? <Modal  Detail={signcards}/>
          : null
      }

        <div className="button-effect">
        { signcards.map((a,i)=>{
            return (
        <button className="effect" type="button"  onClick={ ()=>{navigate(`detail`)}}
        key={i}>{a.name} 
        </button>
        )})}
        </div> 
    </div>   
    <Paging></Paging>          
 
   </div>
   )}
   
function Modal(props){
  return (

    <div class='kan'> 
    
    <div className="flex-container">
    <h1 style={ {paddingLeft:'20px'} }>아끼다</h1>
    <button className="btn btn-danger">단어장추가</button> 
    <h4 style={ {paddingTop: '33px', paddingLeft:'30px'} }>물건이나 돈, 시간 따위를 함부로 쓰지 아니하다</h4>
    <button className="btn btn-primary" style={ {marginLeft:'265px'} }>닫기</button> 
    </div>
        
    {/* <video className="flex-item-video" src={  vid} type="video/mp4"  loop muted /> */}
    {/* autoPlay */}

    <div className="flex-container"> 
    <video className="flex-item-video" src={  vid} type="video/mp4"   loop muted />
      <img className="flex-item-img" src={abc} alt="" />
    </div>
    
    <div className="flex-container"> 

    </div>

    <div className="description">
    [아깝다+절약]
    <br />
오른 손바닥을 왼쪽 뺨에 두 번 댄 다음, 오른 주먹의 1지를 구부려 왼 손바닥에 대고 두 손을 동시에 안으로 당긴다.
</div>
    <div style={ {paddingTop:'30px'} }>
          <button className="btn btn-danger"> 이전 </button> 
          <button className="btn btn-danger"> 목록 </button> 
          <button className="btn btn-danger"> 다음  </button> 
          </div>
    </div>

  )
}


export default SignPage;