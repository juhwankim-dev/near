// 지문자 자음 페이지 'finger/1'

import React, { useState, useEffect } from "react";
import NavBar from '../../NavBar/NavBar';
import { useNavigate } from 'react-router-dom';
import './FingerPage.scss';
import { Button, Modal} from 'react-bootstrap';
import ja from "./FingerData";


function FingerPage(){

  let navigate = useNavigate();
  const [imsi, setImsi] = useState([]);
  const [show, setShow] = useState(false);


  return (
    <div>
     <NavBar></NavBar>
     <div  >
     <button className="btn btn-primary btn1"  style={{marginTop: '30px', paddingLeft: '15px',  paddingRight: '15px',  marginBottom: '0px', marginLeft: '670px'}} onClick={ ()=>{navigate(`/finger/1`)}}> 자음 </button> 
     <button className="btn btn-primary btn1"  style={{marginTop: '30px', paddingLeft: '15px',  paddingRight: '15px',  marginBottom: '0px',}} onClick={ ()=>{navigate(`/finger/2`)}}> 모음 </button> 
     </div>
     <hr />
{/* 자음  */}    
<div className="container" style={{marginTop: '30px',}} > 
      <div className='nemo' style={{marginTop: '30px',}}> 
        <div className="button-effect flex-container row" style={ {paddingTop: '33px'} } >
          {ja.map((data,i) => {
            return ( 
              <div style={ {width: '200px',  margin: '5px auto', paddingLeft:'15px', margin: '15px'}}>
                <button  style={ {width: '170px', height: '210px',}}
                className="effect" type="button" key={i} onClick={() => {setShow(true); setImsi(data); } }  > 
                <img className="img" style={ {borderRadius:'2px', width: '125px', height: '120px', fontWeight: 'bold', paddingBottom:'0px'}} src={ '/img/'+ (i+1) +'.jpg' } />
                <h1 style={{ fontWeight:'bold' }}> 
                {data.name}  </h1> 
              </button>

              <Modal   
              size="lg" 
              aria-labelledby="contained-modal-title-vcenter"
              centered
              backdrop="static"
              keyboard={false}
              onHide={() => setShow(false) }  
              show={show} 
              >
              <Modal.Header closeButton onClick={() => setShow(false)}>
                <Modal.Title>{imsi.name}</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <div className="flex-container" >
                <img className="img" style={ {borderRadius:'2px', width: '200px', height: '200px', fontWeight: 'bold', paddingBottom:'0px'}} src={ '/img/'+ (imsi.image_path)  } />
                <h3 style={ { marginTop: '50px', paddingLeft: '5px', paddingRight: '5px' }}> {imsi.explanation} </h3>
                </div>
              </Modal.Body>
              <Modal.Footer>
                <Button variant="secondary" onClick={()=> {setImsi(ja[0])}}> 
                  이전
                </Button>
                <Button   variant="primary" onClick={()=> {setImsi(ja[2])}}  >
                  다음
                </Button>
              </Modal.Footer>
              </Modal>   
              </div>
              )
          })}
        </div>
      </div>
      </div>
    </div>
  );
}

export default FingerPage;

