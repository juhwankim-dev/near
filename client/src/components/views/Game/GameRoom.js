import React, {useRef, useState } from 'react'; 
import { useNavigate, useParams } from 'react-router-dom';
import { useEffect } from 'react';
import axios from 'axios';
import { Connect } from './ReactStomp';
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import { Button } from 'react-bootstrap';

function GameRoom(){

  // 데이터 셋팅
  const {i} = useParams();
  const [roomId, setRoomId] = useState('');
  const [room, setRoom] = useState({});
  const [sender, setSender] = useState('');
  const [message, setMessage] = useState('');
  const [messages, setMessages] = useState([]);
  const [roomdata, setRoomdata] = useState([]);
  const nickname = JSON.parse(localStorage.getItem('userNickname'));
  const [ready, setReady] = useState(false);
  const [userList, setUserList] = useState([]);
  

  // useEFfect, 컴포넌트 마운트 시 바로 시작 
  useEffect(() => {
    enter(); 
    roomInfo();
    }, []); 


  // 함수

  var stompClient = null;

  const enter = () => {
    const headers = {
      // connect, subscribe에 쓰이는 headers
    };
    var socket = new SockJS(`https://i6d203.p.ssafy.io:8185/ws-stomp/`);
    stompClient = Stomp.over(socket);
  
    stompClient.connect(
      headers,
      frame => {
        stompClient.subscribe(`/sub/chat/room/${i}`,
          (res) => {       
            const data = JSON.parse(res.body.message);
            recvMessage(data)
          },
          headers,
        );
        stompClient.send("/pub/room/message", {}, JSON.stringify({type:'ENTER', roomId: i, sender: "고우영"}));
      },
      () => {
        // disconnect 시 실행 되는 곳
      }
    );
  };

  const out = () => {
    const headers = {
      // connect, subscribe에 쓰이는 headers
    };
    var socket = new SockJS(`https://i6d203.p.ssafy.io:8185/ws-stomp/`);
    stompClient = Stomp.over(socket);
  
    stompClient.connect(
      headers,
      frame => {
        stompClient.subscribe(`/sub/chat/room/${i}`,
          (res) => {       
            const data = JSON.parse(res.body);
          },
          headers
        );
        stompClient.send("/pub/room/message", {}, JSON.stringify({type:'OUT', roomId: i, sender: "고우영"}));
      },
      () => {
        // disconnect 시 실행 되는 곳
      }
    );
  };

  const start = () => {
    const headers = {
      // connect, subscribe에 쓰이는 headers
    };
    var socket = new SockJS(`https://i6d203.p.ssafy.io:8185/ws-stomp/`);
    stompClient = Stomp.over(socket);
  
    stompClient.connect(
      headers,
      frame => {
        stompClient.subscribe(`/sub/chat/room/${i}`,
          (res) => {       
            const data = JSON.parse(res.body);
          },
          headers
        );
        stompClient.send("/pub/room/message", {}, JSON.stringify({type:'START', roomId: i, sender: "고우영"}));

      },
      () => {
        // disconnect 시 실행 되는 곳
      }
    );
  };

  const recvMessage = (recv) => {
    messages.unshift({"type":recv.type,"sender":recv.type=='ENTER'?'[알림]':recv.sender,"message":recv.message})};


  const enterRoom = () => {
    
    axios.get(`https://i6d203.p.ssafy.io:8185/api/game/room/enter/${i}`)
    .then(res => {
      if (res.data === 'game/roomEnter') {
        roomdata.userList.push(nickname);
      }}

     
    )
  };

  const roomInfo = () => {
    axios.get(`https://i6d203.p.ssafy.io:8185/api/game/room/${i}`)
    // .then( res => console.log(res.data.data) )
    .then( (res) => {setRoomdata(res.data.data); setUserList(res.data.data.userList);} );
  };






    
  return (
<div>
  <h1>대기방</h1>
  <h1>방 이름: {roomdata.name}</h1>
  <h1>방장: {roomdata.host}</h1>
  <h1>유저: {roomdata.userList}</h1>
  <div>
    {userList.map(function(a){
      return(
        <div>{a}</div>
      )
    })}
  </div>
  <h1>{roomdata.userCount} / 4</h1>
  <Button onClick={()=>{start(); setReady(!ready);  }}> START</Button>
  <div>
  { 
         ready === true 
         ? <h1>ready</h1>
         : null
      }
  </div>
  <br />
  <br />
  <Button onClick={()=>{out()}}>나가기</Button>

</div>

  );
};

export default GameRoom;

