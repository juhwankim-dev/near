import React, { useState, useEffect } from "react";
import axios from 'axios';
import './SignPage.scss';
import { useDispatch } from 'react-redux';
import NavBar from '../../NavBar/NavBar';


function SignPageTwo(props){
  const dispatch = useDispatch();

  useEffect(() => {
    fetchApi();
  }, []);
  

  const fetchApi = async () => {
    const response = await axios.get(`https://hoonycode.loca.lt/api/hand/`);
    console.log(response.data);
    
  }

  return (
    <div>
    <NavBar></NavBar>  
    <div className="container">
      <div className='nemo'> </div>
      <div></div>




        <div className="row">
        
        </div>
    </div>
    </div>
  )
}

export default SignPageTwo;