import React, { useState, useEffect } from "react";
import axios from 'axios';
import './SignPage4.scss';
import { useDispatch } from 'react-redux';
import NavBar from '../../NavBar/NavBar';
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import SignDetail3 from './SignDetail3.js'
import Paging from './Paging.js';
import {Pagination} from 'react-bootstrap';

function SignPage(){

  useEffect(() => {
    getHandDatas(); 
  }, []); 
  
  let navigate = useNavigate();
  const [handDatas, setHandDatas] = useState([]);
  const [modal, setModal] = useState(false);



  // const dispatch = useDispatch();




  
  const getHandDatas = async () => {
    const json = await (
      await fetch(`https://hoonycode2.loca.lt/api/hand/`)
    ).json();
    console.log(json.data);
    setHandDatas(json.data);
  };

    

  return (
    <div>
      <NavBar></NavBar>  

      <div className="container">
      <div className='nemo'> 
      <div className="button-effect"> 
      {handDatas.map((data, i) => {
        return (
        <button className="effect" key={i} type="button" onClick={() => { navigate(`${i}`); } }> {data.name.split('(')[0]}
        </button>
        )}
      )}
      </div>
      </div>
      </div>
      <Paging></Paging>  
    </div>
  )
}



export default SignPage;