import React, { useState, useEffect } from "react";
import axios from 'axios';


function FingerPage(){
  useEffect(() => {
    fetchApi();
  }, []);
  
  
  const BASE_URL = `https://hoonycode.loca.lt/api/finger/`;

  const fetchApi = async () => {
    const response = await axios.get(BASE_URL);
    console.log(response.data);
   
  }

  return (
    <div className="container">
        <div className="row">
        
        </div>
    </div>
  )
}

export default FingerPage;