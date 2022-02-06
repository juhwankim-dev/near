import React, { useState, useEffect } from "react";
import axios from 'axios';
import './SignPage.scss';
import { useDispatch } from 'react-redux';
import NavBar from '../../NavBar/NavBar';
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import SignDetail3 from './SignDetail3.js'


function SignPageTwo(){
  
  const [handDatas, setHandDatas] = useState([]);
  const [modal, setModal] = useState(false);
  const [a, setA] = useState(false);

  // const dispatch = useDispatch();
  let navigate = useNavigate();

  const getHandDatas = async () => {
    const json = await (
      await fetch(`https://hoonycode.loca.lt/api/hand/`)
    ).json();
    setHandDatas(json.data);
  };
  console.log(setHandDatas);
  useEffect(() => {
    getHandDatas();
  }, []); 


  // useEffect(() => {
  //   fetch('https://hoonycode.loca.lt/api/hand/')
  //   // fetch(`https://yts.mx/api/v2/list_movies.json?minimum_rating=8.8&sort_by=year')
  //   .then((res) => res.json() )
  //   .then((json) =>  setHandDatas(json.data.movies)) }, []);
  //   console.log(handDatas);
  

// const fetchApi = async () => {
//   const response = await axios.get(`https://hoonycode.loca.lt/api/hand/`);
  
//   console.log(response.data);
// }

// useEffect(() => {
//   fetchApi();
// }, []);

// useEffect(() => {
//   axios.get(`https://hoonycode.loca.lt/api/hand/`)
//   .then((res) => res.json())
//   .then((json) =>  setHandDatas(json.data)) 

//   console.log(setHandDatas)
// }, []);


// const fetchApi = () => {
//   axios.get(`https://hoonycode.loca.lt/api/hand/`)
//   .then((result)=>{ setHandDatas([result.data]) })
//   console.log(setHandDatas)
//   .catch(()=>{ '요청실패시실행할코드' });
// }

// useEffect(() => {
//   fetchApi();
// }, []);


  return (
    <div>
    <NavBar></NavBar>  

    <div className="container">
      <div className='nemo'> 
      
    <div className="button-effect"> 
    {handDatas.map((data, i) => (
    
    <>
        <button className="effect" type="button" key={i} onClick={() => { navigate(`${i}`); } }
        // <button className="effect" type="button" key={i} onClick={() => { navigate(`${data.handcontent_key}`); } }
        > {data.name.split('(')[0]}</button></>)

      
      )}

    { 
        modal === true 
        ? <Modal/>
        : null
    }
    </div>
     

      
      </div>
      

    </div>
    </div>
  )
}


function Modal(props){
  return (

    <div class='kan'> 
    
    <div className="flex-container">
    <h1 style={ {paddingLeft:'20px'} }>{}</h1>
    <button className="btn btn-danger">단어장추가</button> 
    <h4 style={ {paddingTop: '33px', paddingLeft:'30px'} }>물건이나 돈, 시간 따위를 함부로 쓰지 아니하다</h4>
    <button className="btn btn-primary" style={ {marginLeft:'265px'} }>닫기</button> 
    </div>
        
    {/* <video className="flex-item-video" src={  vid} type="video/mp4"  loop muted /> */}
    {/* autoPlay */}

    <div className="flex-container"> 
    {/* <video className="flex-item-video" src={  vid} type="video/mp4"   loop muted />
      <img className="flex-item-img" src={abc} alt="" /> */}
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


export default SignPageTwo;

// const fetchApi = async () => {
//   const response = await axios.get(`https://hoonycode.loca.lt/api/hand/`);
//   console.log(response.data);
// }

// useEffect(() => {
//   fetchApi();
// }, []);


{/* <div className="button-effect"> 
{handData.map((data, i) => 
 
  <button className="effect" type="button" onClick={ ()=>{setModal(true)} }
  key={i}> {data.name}</button>
  )}

{ 
    modal === true 
    ? <Modal/>
    : null
}
  

</div> */}