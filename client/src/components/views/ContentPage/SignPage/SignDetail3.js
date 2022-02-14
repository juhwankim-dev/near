import React, {useState, useEffect} from 'react';
import Data from './SignData.js';
import { useHistory,  useNavigate, useParams } from 'react-router-dom';
import './SignPage4.scss';
import vid from '../../../../assets/NIA_SL_WORD0687_SYN02_F.mp4';
import abc from '../../../../assets/abc.PNG';
import def from '../../../../assets/def.png';
import axios from 'axios';



function SignDetail3(props){
  
  useEffect(() => {
    getHandDatas();
  }, []); 

  const [handDatas, setHandDatas] = useState([]);
  const [handKey, setHandKey] = useState(null);
  const {i} = useParams();
  const handkeyy = parseInt(i)+1
  let navigate = useNavigate();
  const id = JSON.parse(localStorage.getItem('user'));
  // const id = JSON.parse(localStorage.getItem('user'));
  const realId = id.id
  console.log(realId);
  


 const addBookmark = () => {
  axios
  .post(`https://hoonycode2.loca.lt/api/hand/bookmark`,
   {handcontent_key: parseInt(i)+1,
   id: realId}) // 두번째 인자로 config가 들어감(보안과 관련된 옵션들)
  //  {token : JSON.stringify(res.payload.data.token)} ) // 두번째 인자로 config가 들어감(보안과 관련된 옵션들)
  .then(response => {
    })
  }

  const getHandDatas = async () => {
    const json = await (
      await fetch(`https://hoonycode2.loca.lt/api/hand/`)
    ).json();
    setHandDatas(json.data);
    setHandKey(json.data[i])
  };

  
  console.log(handKey);
  console.log(handkeyy);

  // const name = JSON.stringify(handDatas[i]?.name).replace(/\"/gi, "");

  

var name = (JSON.stringify(handDatas[i]?.name)||'').replace(/\"/gi, "");


  return (
    <div style={{ width:'1400px' }} className='detail'> 
    <div className="flex-container row">

      <div className="flex-container"  style={{ width:'1500px' }} >
      <h1 style={ {  paddingTop:'5px', paddingLeft:'35px', fontWeight:'bold', color:'black', width:'1000px' }} className="title">{(JSON.stringify(handDatas[i]?.name)||'').replace(/\"/gi, "").split('(')[0]}</h1>
      <button style={ {paddingTop:'0px', marginTop:'30px', marginLeft:'0px', height:'52px', width:'60px', fontSize:'35px', } } className="btn btn-danger"
      onClick={ ()=>{addBookmark()}} >❤</button> 
      
      
      <div className='buttons'>
          <button className="btn btn-primary btn1"> &lt; </button> 
          <button className="btn btn-primary btn1"  onClick={ ()=>{navigate(`/sign`)}}>  </button> 
          {/* <button className="btn btn-primary btn1"   onClick={ ()=>{navigate(`/sign`)}} > 목록 </button>  */}
          <button className="btn btn-primary btn1"> &gt;  </button> 
    </div>
    </div>
    <div>
    <h3 className='meaning' style={{ paddingLeft:'35px', fontWeight:'bolder', color:'black'  } }>{(JSON.stringify(handDatas[i]?.mean)||'').replace(/\"/gi, "")}</h3>
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

    <div style={{ width:'1320px' }} className="description" >
    <div style={{ fontSize:'27px', color:'black' }}>{(JSON.stringify(handDatas[i]?.movement)||'').replace(/\"/gi, "")}</div>
    <br />
    <div style={{ fontSize:'27px', color:'black' }}>{(JSON.stringify(handDatas[i]?.explanation)||'').replace(/\"/gi, "")}</div>
    </div>
    </div>
    </div>
  )};

export default SignDetail3;
