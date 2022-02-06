import React, { useState, useEffect } from "react";
import axios from 'axios';
import './SignPage.scss';
import { useDispatch } from 'react-redux';
import NavBar from '../../NavBar/NavBar';
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Sign from './Sign.js'



function SignListPage(props){
  const [handDatas, setHandDatas] = useState([]);
  const [modal, setModal] = useState(false);
  const dispatch = useDispatch();
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


  return (
    <div>
    <NavBar></NavBar>  

    <div className="container">
      <div className='nemo'> 
      
    <div className="button-effect"> 
    {handDatas.map((data, i) => 
    <Sign
    key={data.id}
    id={data.id}
    title={data.title}
    />
      )}

  
    </div>
     

      

      </div>
      

    </div>
    </div>
  )
          };

//    {handDatas.map((data, i) => 

    
//       <button className="effect" type="button" onClick={ ()=>{navigate(`detail/${i}`)} }
//       key={i}> {data.title}</button>
//       )}

export default SignListPage;