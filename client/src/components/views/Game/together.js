// Room이랑 Roomdetail 합쳐놓은 파일

import React, {useRef, useEffect, useState} from 'react'; 
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import SockJS from 'sockjs-client';
// import Stomp from "@stomp/stompjs"

function Together(){
  // Room에 있던 코드 ----------
  let navigate = useNavigate();

  const [roomname, setRoomname] = useState('');
  const [chatrooms, setchatrooms] = useState([]);

  const onRoomnameHandler = (e) => {
    setRoomname(e.currentTarget.Value);
    console.log(roomname);
  };

  useEffect(() => {
    findAllRoom(); 
    }, []); 

//모바일 코드 @POST("api/game/room")
  //비동기 처리 해야하는가?
  const findAllRoom = () => {
    axios.get('/api/game/rooms').then(response => { chatrooms = response.data; });

  };

  const createRoom = () => {
    if("" === roomname) {
      alert("방 제목을 입력해 주십시요.");
      return;
  } else {
      // var params = new URLSearchParams();
      // params.append("name", roomname);
      axios.post('https://i6d203.p.ssafy.io:8185/api/game/room',
      {host: "이름",
      name: "이름",
      }
      )
      .then(
          response => {
              alert(response.data.name+"방 개설에 성공하였습니다.")
              roomname = '';
              findAllRoom();
          }
      )
      .catch( response => { alert("채팅방 개설에 실패하였습니다."); } );
  }
  };

  const enterRoom = (roomId) => {
    var sender = prompt('닉네임을 입력해 주세요.');
    if(sender != "") {
        localStorage.setItem('wschat.sender',sender);
        localStorage.setItem('wschat.roomId',roomId);
        // axios가 필요없는가?
        navigate('/api/game/room/enter/"+roomId');
        // location.href="/api/game/room/enter/"+roomId;
    }
  };

  // Roomdetail에 있던 코드  ---------
  
  // 다른 사이트에서 적어온 코드
  // const client = useRef({});
  // const connect = () => {
  //   client.current = new Stomp.Client({
  //     webSocketFactory: () => new SockJS("wss://i6d203.p.ssafy.io:8185/ws-stomp/websocket"),
  //   })
  // };
  
  
  // websocket & stomp initialize
  var Stomp = require('stompjs');
  var client = Stomp.overWS("wss://i6d203.p.ssafy.io:8185/ws-stomp/websocket");
  var sock = new SockJS("wss://i6d203.p.ssafy.io:8185/ws-stomp/websocket");
        var ws = Stomp.over(sock);
        var reconnect = 0;

  // useEffect
  useEffect(() => {
    setRoomId(localStorage.getItem('wschat.roomId'));
    setSender(localStorage.getItem('wschat.sender'));
    findRoom(); 
    }, []);      
  
  // 데이터 셋팅
  const [roomId, setRoomId] = useState('');
  const [room, setRoom] = useState({});
  const [sender, setSender] = useState('');
  const [message, setMessage] = useState('');
  const [messages, setMessages] = useState([]);



  // 함수 셋팅
  
  //방 조회
  const findRoom = () => {
    axios.get('/api/game/room'+ roomId).then(response => { room = response.data; });
  };

  const sendMessage = () => {
    ws.send("/pub/chat/message", {}, JSON.stringify({type:'TALK', roomId:this.roomId, sender:this.sender, message:this.message}));
    message = '';
  };

  const recvMessage = (recv) => {
  messages.unshift({"type":recv.type,"sender":recv.type=='ENTER'?'[알림]':recv.sender,"message":recv.message})
  };
  
  // 채팅 주고 받고 하는 부분인것 같음..? 
  
//   function connect(){
//     // pub/sub event
//     ws.connect({}, function(frame) {
//     ws.subscribe("/sub/chat/room/"+vm.$data.roomId, function(message) {
//         var recv = JSON.parse(message.body);
//         vm.recvMessage(recv);
//     });
//     ws.send("/pub/chat/message", {}, JSON.stringify({type:'ENTER', roomId:vm.$data.roomId, sender:vm.$data.sender}));
// }, function(error) {
//     if(reconnect++ <= 5) {
//         setTimeout(function() {
//             console.log("connection reconnect");
//             sock = new SockJS("wss://i6d203.p.ssafy.io:8185/ws-stomp/websocket");
//             ws = Stomp.over(sock);
//             connect();
//         },10*1000);
//     }
//   });
//   };
  
//   connect();

  return (
<>
  <div class="container" id="app" v-cloak>
          <div class="row">
              <div class="col-md-12">
                  <h3>채팅방 리스트</h3>
              </div>
          </div>
          <div class="input-group">
            <div class="input-group-prepend">
                <label class="input-group-text">방제목</label>
            </div>
              <input type="text" class="form-control"
              onChange={onRoomnameHandler} />
              
              {/* v-on:keyup.enter="createRoom"/> */}
              <div class="input-group-append">
                  <button class="btn btn-primary" type="button" onClick={()=>{createRoom()}}>
                  채팅방 개설</button>
              </div>
          </div>
          { chatrooms.map(function(name, roomId){
            return(
              <ul className='list-group'>
                <li className='list-group-item list-group-item-action' key={roomId} onClick={(roomId)=>enterRoom(roomId)}> 
                {name} </li>
              </ul>
            )
          }) }

          {/* <ul class="list-group">
              <li class="list-group-item list-group-item-action" 
              v-for="item in chatrooms" v-bind:key="item.roomId"  v-on:click="enterRoom(item.roomId)">
                  {{item.name}}
              </li>
          </ul> */}
      </div>
    </>
  );
};

export default Together;