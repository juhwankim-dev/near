import React, { useState } from "react";
import Data from './SignData.js';

function SignCard(){
  let [signcards, setsigncard] = useState(Data);


  return (
    <div className="container">
        <div className="row">
          {
            signcards.map((a,i)=>{
             return <Card signcards={signcards[i]} key={i}/>
             console.log(signcards[i])
            })
          }
        </div>
    </div>
  )
}

function Card(props){
  return (
    // <div className="col-md-3">
    <div className="col-md-3">
      <h4>{ props.signcards.name }</h4>
    </div>
  )
}

export default SignCard;