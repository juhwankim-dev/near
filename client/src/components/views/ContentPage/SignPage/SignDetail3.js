import React, {useState, useEffect} from 'react';
import { useHistory,  useNavigate, useParams } from 'react-router-dom';
import './SignPage4.scss';
import vid from '../../../../assets/NIA_SL_WORD0687_SYN02_F.mp4';
import abc from '../../../../assets/abc.PNG';
import def from '../../../../assets/def.png';
import axios from 'axios';
import NavBar from '../../NavBar/NavBar';
import {Button } from 'react-bootstrap';



function SignDetail3(props){
  
  useEffect(() => {
    getHandDatas();
  }, []); 

  const [heart, setHeart] = useState('🤍');
  const [handDatas, setHandDatas] = useState([]);
  const [handKey, setHandKey] = useState(null);
  const {i} = useParams();
  const handkeyyy = parseInt(i)+1
  const handkeyy = parseInt(i)-1
  const [id, setId] = useState(0);
  let navigate = useNavigate();
  // const id = JSON.parse(localStorage.getItem('user'));
  // const id = JSON.parse(localStorage.getItem('user'));
  // const realId = id.id
  // console.log(realId);
  console.log(id);
  




const changeColor = () => {
  setHeart('🧡')
}



 const addBookmark = () => {
  //  setId(JSON.parse(localStorage.getItem('user')).id);
   
  axios
  .post(`https://i6d203.p.ssafy.io:8185/api/hand/bookmark`,
   {handcontent_key: parseInt(i)+1,
   id: JSON.parse(localStorage.getItem('user')).id}) // 두번째 인자로 config가 들어감(보안과 관련된 옵션들)
  //  {token : JSON.stringify(res.payload.data.token)} ) // 두번째 인자로 config가 들어감(보안과 관련된 옵션들)
  .then(response => {
    console.log(response)})
    setHeart('🖤')
  }

  const getHandDatas = async () => {
    const json = await (
      await fetch(`https://i6d203.p.ssafy.io:8185/api/hand/`)
    ).json();
    setHandDatas(json.data);
    setHandKey(json.data[i])
  };

  
  console.log(handKey);
  console.log(handkeyy);

  // const name = JSON.stringify(handDatas[i]?.name).replace(/\"/gi, "");



var name = (JSON.stringify(handDatas[i]?.name)||'').replace(/\"/gi, "");


  return (
    <div><NavBar></NavBar> 
    <div style={{ width:'1700px', marginTop:'20px',  }} className='detail'> 
    <div style={{marginTop: '100px', height:'1100px' }} className="flex-container row">

      <div className="flex-container"  style={{ width:'1700px' }} >
      <h1 style={ {  paddingTop:'10px', paddingLeft:'30px', fontWeight:'bold', color:'black', width:'82%' }} className="title">{(JSON.stringify(handDatas[i]?.name)||'').replace(/\"/gi, "").split('(')[0]}</h1>
      <Button style={{paddingTop:'0px', marginTop:'30px', marginLeft:'0px', paddingLeft:'7px', height:'52px', width:'60px', fontSize:'35px', }} className="btn btn-danger"
      onClick={ ()=>{addBookmark()}} >{heart}</Button>      
      
      <div className='buttons'>
          <button style={{paddingTop:'0px',  height:'52px', width:'60px',}} className="btn btn-primary btn1" onClick={ ()=>{navigate(`/sign/${handkeyy}`)}}> &lt; </button> 
          <button style={{paddingTop:'0px',  height:'52px', width:'70px',}} className="btn btn-primary btn1"  onClick={ ()=>{navigate(`/sign`)}}> 목록 </button> 
          {/* <button className="btn btn-primary btn1"   onClick={ ()=>{navigate(`/sign`)}} > 목록 </button>  */}
          <button style={{paddingTop:'0px',  height:'52px', width:'60px',}} className="btn btn-primary btn1" onClick={ ()=>{navigate(`/sign/${handkeyyy}`)}}> &gt;  </button> 
    </div>
    </div>
    
    <div>
    <h3 className='meaning' style={{ width:'1400px', paddingLeft:'30px', fontWeight:'bolder', color:'black'  } }>{(JSON.stringify(handDatas[i]?.mean)||'').replace(/\"/gi, "")}</h3>
    </div>
    
    <div className="flex-container "> 
    <video style={{  marginTop:'10px', height: '700px', width:'65%' }} className="flex-item-video" src={(JSON.stringify(handDatas[i]?.video_path)||'').replace(/\"/gi, "")} type="video/mp4"  autoPlay loop muted />
      <div>
      <img style={{  marginTop:'10px', marginLeft:'15px', height: '700px', width:'93%' }} className='flex-item-img' src={(JSON.stringify(handDatas[i]?.image_path)||'').replace(/\"/gi, "")}/>
      </div>
    </div>

    <div style={{ marginTop:'15px', width:'1650px' }} className="description" >
    <div style={{ fontSize:'27px', color:'black' }}>{(JSON.stringify(handDatas[i]?.movement)||'').replace(/\"/gi, "")}</div>
    <div style={{ marginTop:'10px', fontSize:'27px', color:'black' }}>{(JSON.stringify(handDatas[i]?.explanation)||'').replace(/\"/gi, "")}</div>
    </div>
    </div>
    </div>
    </div>
  )};

export default SignDetail3;
