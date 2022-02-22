import React, {useRef, useState} from 'react'; 
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Connect } from './ReactStomp';
import SockJS from "sockjs-client";
import Stomp from "stompjs";

function GameRoomList(){
  var stompClient = null;

  const Connect = () => {


    const headers = {
      // connect, subscribe에 쓰이는 headers
    };
  
    var socket = new SockJS(`https://i6d203.p.ssafy.io:8185/ws-stomp/`);
    stompClient = Stomp.over(socket);
  
    stompClient.connect(
      headers,
      frame => {
  
        stompClient.subscribe(
          "<subscribe하는 topic>",
          () => {
            // subscribe 후 실행하는 곳
          },
          headers
        );
      },
      () => {
        // disconnect 시 실행 되는 곳
      }
    );
  }
  
  let navigate = useNavigate();

  const [roomname, setRoomname] = useState('');
  const [chatrooms, setchatrooms] = useState([]);

  const onRoomnameHandler = (e) => {
  setRoomname(e.currentTarget.Value);
  console.log(roomname);
  };

  // useEFfect 
  useEffect(() => {
    Connect(); findAllRooms(); 
    }, []); 

  // ** websocket & stomp initialize

  // 함수
  const findAllRoom = () => {
    axios.get("api/game/rooms")
    .then(res => console.log(res));


    // .then(response => { chatrooms = response.data; });
    // axios.get('https://i6d203.p.ssafy.io:8185/ws-stomp/websocket/api/game/rooms').then(response => { chatrooms = response.data; });
  };


  // const enterRoom = () => {
    
  // };

  const findAllRooms = async () => {
    const json = await (
      await fetch(`https://i6d203.p.ssafy.io:8185/api/game/rooms`)
    ).json();
    console.log(json.data);
    
    setchatrooms(json.data);
    console.log(chatrooms);
    
  };

  const createRoom = () => {
    if("" === roomname) {
      alert("방 제목을 입력해 주십시요.");
      return;
  } else {
      // var params = new URLSearchParams();
      // params.append("name", roomname);
      axios.post('https://i6d203.p.ssafy.io:8185/api/game/room',
      // axios.post('https://i6d203.p.ssafy.io:8185/ws-stomp/websocket/api/game/room',
      // axios.post('https://i6d203.p.ssafy.io:8185/api/game/room',
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
        axios.get(`https://i6d203.p.ssafy.io:8185/api/game/room/enter/${roomId}`)
        navigate(`/GameRoom/${roomId}`);
        
        
        // location.href="/api/game/room/enter/"+roomId;
    }
  };
  
return (
  <>
   {/* <div className="container" id="app" v-cloak> */}
   <div className="container" id="app">
      <div className="row">
          <div className="col-md-12">
              <h3>게임방 리스트</h3>
          </div>
      </div>
      <div className="input-group">
        <div className="input-group-prepend">
            <label className="input-group-text">방제목</label>
        </div>
          <input type="text" className="form-control"
          onChange={onRoomnameHandler} />
          
           {/* v-on:keyup.enter="createRoom"/> */}
          <div className="input-group-append">
              <button className="btn btn-primary" type="button" onClick={()=>{createRoom()}}>
              만들기</button>
          </div>
      </div>
      { chatrooms.map((data, i) => {
        return(
          <ul className='list-group'>
            <li classNamee='list-group-item list-group-item-action' key={i} onClick={(roomId) => { enterRoom(data.roomId)} }> 
            {data.name} </li>
          </ul>
        )}
      )}

      {/* <ul className="list-group">
          <li className="list-group-item list-group-item-action" 
          v-for="item in chatrooms" v-bind:key="item.roomId"  v-on:click="enterRoom(item.roomId)">
              {{item.name}}
          </li>
      </ul> */}
  </div>
  </>
)

};

export default GameRoomList;