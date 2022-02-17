import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import NavBar from '../NavBar/NavBar';
import axios from 'axios';

function MyProfile(){
  const [profile, setProfile] = useState([]);
  const [myProfile, setMyProfile] = useState([]);
  const loginUser = JSON.parse(localStorage.getItem('user'));
  const token = (JSON.parse(localStorage.getItem('user'))).token;

  axios.post(`http://i6d203.p.ssafy.io:8185/api/sign/userInfo/`,
           {token: token})
          .then((res)=> {
            setMyProfile(res.data.data);

            });

  
  return(
    <div> <NavBar></NavBar>

    <div>아이디: {myProfile.uid}</div>
    <div>닉네임: {myProfile.nickname}</div>
    </div>
  )
};

export default MyProfile;