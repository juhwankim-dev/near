import Data from './SignData.js';
import { useHistory, useParams, useNavigate } from 'react-router-dom';
import './SignPage.scss';
import vid from '../../../../assets/NIA_SL_WORD0687_SYN02_F.mp4';
import abc from '../../../../assets/abc.PNG';
import def from '../../../../assets/def.png';
import React, { useState, useEffect } from "react";
import axios from 'axios';
import './SignPage.scss';
import { useDispatch } from 'react-redux';
import NavBar from '../../NavBar/NavBar';
import { Link } from "react-router-dom";



function SignDetail2(){
    const [handDatas, setHandDatas] = useState([]);
    let id  = useParams();
    let [signcards, setsigncard] = useState(Data);
    let navigate = useNavigate();

    const getHandDatas = async () => {
        const json = await (
          await fetch(`https://yts.mx/api/v2/list_movies.json?minimum_rating=8.8&sort_by=year`)
        ).json();
        setHandDatas(json.data.movies);
      };
      useEffect(() => {
        getHandDatas();
      }, []); 

   
  return  (

    <div className='detail'> 
    
    <div className="flex-container">

    {handDatas.map((data, i) => 
    
    <h1
    key={i}
    id={data.id}
    > {data.title}</h1>
    )}


  
    {/* <button style={ {marginTop:'20px', height:'60px', width:'60px', fontSize:'35px'} } className="btn btn-danger">❤</button> 
    <h3 className='meaning' style={ {paddingTop: '33px', paddingLeft:'40px', fontWeight:'bold', fontSize:'30px'} }>물건이나 돈, 시간 따위를 함부로 쓰지 아니하다  </h3> */}
    {/* <button className="btn btn-primary" style={ {marginLeft:'265px'} }  onClick={ ()=>{navigate(`/sign`)} }>닫기</button>  */}
    {/* <div className='buttons'>
          <button className="btn btn-primary btn1"> &lt; </button> 
          <button className="btn btn-primary btn1"   onClick={ ()=>{navigate(`/sign`)}} > 목록 </button> 
          <button className="btn btn-primary btn1"> &gt;  </button> 
    </div>
    
    </div> */}
        
    {/* <video className="flex-item-video" src={  vid} type="video/mp4"  loop muted /> */}
    {/* autoPlay */}

    {/* <div className="flex-container"> 
    <video className="flex-item-video" src={  vid} type="video/mp4"  autoPlay loop muted /> */}
      {/* <img className="flex-item-img" src={abc} alt="" /> */}
      <div >
        {/* <img className='flex-item-img2' src={def} alt="" /> */}
      {/* <img className='flex-item-img' src="https://wis.seoul.go.kr/rest/file/download/p93598vytek4exoub5pirn8ehjnjjl40/1" alt="" />
      <img className='flex-item-img' src="https://wis.seoul.go.kr/rest/file/download/p93598vytek4exoub5pirn8ehjnjjl40/2" alt="" /> */}
      </div>
    </div>
    
    <div className="flex-container"> 

    </div>

    <div className="description" >
    [아깝다+절약]
    <br />
    오른 손바닥을 왼쪽 뺨에 두 번 댄 다음, 오른 주먹의 1지를 구부려 왼 손바닥에 대고 두 손을 동시에 안으로 당긴다.
    </div>
    <span>
      <br />
    </span>

    
    </div>

  )
};

export default SignDetail2;