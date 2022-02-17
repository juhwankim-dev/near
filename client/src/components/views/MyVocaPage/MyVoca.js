import NavBar from '../NavBar/NavBar';
import React, { useState, useEffect,  } from "react";
import { useNavigate, useParams  } from "react-router-dom";
import axios from 'axios';
import {Button } from 'react-bootstrap';




function Myvoca() {
  let navigate = useNavigate();

 
 
  const [vocas, setVocas] = useState([]);
  const id = JSON.parse(localStorage.getItem('user'));
  const realId = id.id
  const {i} = useParams();
  const handkeyy = parseInt(i)+1

  const getMyVocas = async () => {
    const json = await (
      await fetch(`https://i6d203.p.ssafy.io:8185/api/hand/bookmark/${realId}`)
    ).json();
    setVocas(json.data);
  };
  console.log(vocas);
  useEffect(() => {
    getMyVocas();
  }, []); 


  

  return (
    <div>
      <NavBar></NavBar>
    
      <div style={{ width: '100%', height:'600px', marginLeft: '400px',}} className="container">
      <div style={{ marginTop: '120px', width: '1700px', height:'800px',  }} className='nemo'> 
      <div style={{ paddingTop: '130px' }} className="button-effect"> 
      {vocas.map((data, i) => (
      <button style={{width:'24%', height:'100px', fontSize:'40px', margin:'18px'   }}  className="effect" type="button" key={i} onClick={() => { navigate(`${i}`); } }
      > {data.name.split('(')[0]}</button>)
      )}
      </div>
      </div>
      <div style={{marginLeft: '1230px',}} >  
      <Button  style={{ width: '120px', height: '60px', fontSize: '30px', fontWeight:'bold', marginTop: '10px', marginLeft: '350px' ,paddingLeft: '15px',  paddingRight: '15px',  marginBottom: '0px',}} onClick={ ()=>{setVocas([]); } } >clear</Button>
      </div>
      </div>  
    </div>
  
  );
}

  export default Myvoca