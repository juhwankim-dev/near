import React, { useState, useEffect } from "react";
import axios from 'axios';
import './SignPage4.scss';
import { useDispatch } from 'react-redux';
import NavBar from '../../NavBar/NavBar';
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import SignDetail3 from './SignDetail3.js'
import ReactDOM from 'react-dom';
import Paginator from 'react-hooks-paginator';
import './Paging.css'; 

function SignPage(){

  useEffect(() => {
    getHandDatas(); 
  }, []); 

    
  let navigate = useNavigate();
  const [handDatas, setHandDatas] = useState([]);
  const [modal, setModal] = useState(false);

  const pageLimit = 12;
  const [offset, setOffset] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [data, setData] = useState([]);
  const [currentData, setCurrentData] = useState([]);


  useEffect(() => {
    setCurrentData(data.slice(offset, offset + pageLimit));
  }, [offset, data]);
  // const dispatch = useDispatch();




  
  const getHandDatas = async () => {
    const json = await (
      await fetch(`https://hoonycode2.loca.lt/api/hand/`)
    ).json();
    console.log(json.data);
    setHandDatas(json.data);
    setData(json.data);
    console.log(data);
    // setData(handDatas);
  };

    

  return (
    <div>
      <NavBar></NavBar>  

      <div style={{ width: '100%', height:'830px', marginLeft: '400px' }} className="container">
      <div style={{ marginTop: '120px', width: '1700px', height:'800px',  }} className='nemo'> 
      <div style={{ paddingTop: '130px' }} className="button-effect"> 
      {currentData.map((data, i) => {
        return (
        <button style={{width:'24%', height:'100px', fontSize:'40px', margin:'18px'   }} className="effect" key={i} type="button" onClick={() => { navigate(`${i}`); } }> {data.name.split('(')[0]}
        </button>
        )}
      )}
      </div>
      </div>
      </div>
      {/* <Paging></Paging>   */}
      <div>
        
      {/* <ul>
        {currentData.map((data, i) => (
          <li key={i}>{data.name}</li>
        ))}
      </ul> */}

      <Paginator
        totalRecords={data.length}
        pageLimit={pageLimit}
        pageNeighbours={2}
        setOffset={setOffset}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
      />
    </div>
    </div>
  )
}



export default SignPage;