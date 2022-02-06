import React, {useState, useEffect} from 'react';
import Data from './SignData.js';
import { useHistory,  useNavigate, useParams } from 'react-router-dom';
import './SignPage.scss';
import vid from '../../../../assets/NIA_SL_WORD0687_SYN02_F.mp4';
import abc from '../../../../assets/abc.PNG';
import def from '../../../../assets/def.png';
import axios from 'axios';


function SignDetail3(props){
  const [handDatas, setHandDatas] = useState([]);
  const {i} = useParams();
  let navigate = useNavigate();


  
  // useEffect(() => {
  //   axios
  //     .get(`https://hoonycode.loca.lt/api/hand/`)
  //     .then((json)=> {
  //     	setHandDatas(JSON.stringfy(json.data));
  //     })
  //     console.log(handDatas)
  //     // .catch((err)=> {})
  // }, []); 


  const getHandDatas = async () => {
    const json = await (
      await fetch(`https://hoonycode.loca.lt/api/hand/`)
    ).json();
    
    // console.log(json)
    setHandDatas(json.data);
  };console.log(setHandDatas);

  // const name = JSON.stringify(handDatas[i]?.name).replace(/\"/gi, "");

  

var name = (JSON.stringify(handDatas[i]?.name)||'').replace(/\"/gi, "");
console.log(name);

  
  useEffect(() => {
    getHandDatas();
  }, []); 


  // const data = JSON.stringify(handDatas[i]);
  // console.log(data);

  return (
    <div className='detail'> 
    <div className="flex-container row">

      <div className="flex-container" >
      <h1 style={ {paddingLeft:'20px', fontWeight:'bold'}} className="title">{(JSON.stringify(handDatas[i]?.name)||'').replace(/\"/gi, "")}</h1>
      <button style={ {marginTop:'20px', height:'60px', width:'60px', fontSize:'35px'} } className="btn btn-danger">❤</button> 
      <h3 className='meaning' style={ {paddingTop: '33px', paddingLeft:'15px', fontWeight:'bold', fontSize:'30px'} }>
      {(JSON.stringify(handDatas[i]?.mean)||'').replace(/\"/gi, "")}</h3>
      
      <div className='buttons'>
          <button className="btn btn-primary btn1"> &lt; </button> 
          <button className="btn btn-primary btn1"  onClick={ ()=>{navigate(`/sign`)}}> 목록 </button> 
          {/* <button className="btn btn-primary btn1"   onClick={ ()=>{navigate(`/sign`)}} > 목록 </button>  */}
          <button className="btn btn-primary btn1"> &gt;  </button> 
    </div>
    </div>
    
    <div className="flex-container "> 
    {/* <video className="flex-item-video" src={vid} type="video/mp4"  autoPlay loop muted /> */}
      {/* <img className="flex-item-img" src={abc} alt="" /> */}
      <div >
        {/* <img className='flex-item-img2' src={def} alt="" /> */}
      {/* <img className='flex-item-img' src="https://wis.seoul.go.kr/rest/file/download/p93598vytek4exoub5pirn8ehjnjjl40/1" alt="" />
      <img className='flex-item-img' src="https://wis.seoul.go.kr/rest/file/download/p93598vytek4exoub5pirn8ehjnjjl40/2" alt="" /> */}
      </div>
    </div>

    <div className="description" >
    <div>{(JSON.stringify(handDatas[i]?.movement)||'').replace(/\"/gi, "")}</div>
    <br />
    <div>{(JSON.stringify(handDatas[i]?.explanation)||'').replace(/\"/gi, "")}</div>
    </div>
   
       
      {/* <h1>{JSON.stringify(data)}</h1>
      <h1>{JSON.stringify(data.name).replace(/\"/gi, "")}</h1> 
      <h1>{JSON.stringify(handDatas[i].name).replace(/\"/gi, "")}</h1> 
      <h1>{JSON.stringify(handDatas[i].explanation).replace(/\"/gi, "")}</h1>
      <h1>{JSON.stringify(handDatas[i].mean).replace(/\"/gi, "")}</h1> 
      <h1>{JSON.stringify(handDatas[i].name).replace(/\"/gi, "")}</h1>  */}
      {/* {handDatas}  */}
      {/* <h1>{i}번째</h1> */}
    </div>
    </div>
  )};

export default SignDetail3;
