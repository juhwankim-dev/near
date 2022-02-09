import NavBar from '../NavBar/NavBar';
import React, { useState, useEffect,  } from "react";
import { useNavigate, useParams  } from "react-router-dom";
import axios from 'axios';
import Paging from '../ContentPage/SignPage/Paging';



function Myvoca() {
  let navigate = useNavigate();


  const [vocas, setVocas] = useState([]);
  const id = JSON.parse(localStorage.getItem('user'));
  const realId = id.id
  const {i} = useParams();
  const handkeyy = parseInt(i)+1

  const getMyVocas = async () => {
    const json = await (
      await fetch(`/api/hand/bookmark/${realId}`)
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
      <div className="container">
      <div className='nemo'>
      <div className="button-effect" style={ {paddingTop: '33px'} }>
      {vocas.map((data, i) => (
      <button className="effect" type="button" key={i} onClick={() => { navigate(`${i}`); } }
      > {data.name.split('(')[0]}</button>)
      )}
      </div>
      </div>
      </div>
      <Paging></Paging>     
    </div>
  );
}

  export default Myvoca