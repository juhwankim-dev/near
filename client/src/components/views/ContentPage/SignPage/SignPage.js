// 구성: SignPage/SignCard/SignCardDetail

import React, { useState } from "react";
import { Pagination } from 'react-bootstrap';
import Data from './SignData.js';
import Paging from './Paging.js';
import SignCard from './SignCard.js';
// import './SignPage.css';
import './SignPage.scss';


function SignPage(){
  let [signcards, setsigncard] = useState(Data);
  let [modal, setmodal] = useState(false);
 
 
  return (
    <div className="container">
      <h1>수어 배우기</h1>
      <div className='nemo'> 
      
      { 
          modal === true 
          ? <Modal  Detail={signcards}/>
          : null
      }

        <div className="button-effect">
        { signcards.map((a,i)=>{
            return (
        <button className="effect" type="button"  onClick={ ()=>{setmodal(!modal)}}
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
        {/* <h4 className="pt-5">{props.signcards.name}</h4> */}
          <p>수화설명</p>
          <img src="" width="100%"/>
          <p>영상</p>
          <button className="btn btn-danger">단어장추가</button> 
          <div>
          <button className="btn btn-danger"> 이전 </button> 
          <button className="btn btn-danger"> 목록 </button> 
          <button className="btn btn-danger"> 다음  </button> 
          </div>
    </div>

  )
}


export default SignPage;