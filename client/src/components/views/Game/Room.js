// 이게 영훈이가 줬던 코드 리액트로 바꾼거 
// 채팅방 개설, 채팅방리스트, 채팅방 입장

import React, {useRef, useState} from 'react'; 
import { useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


function Room () {
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
  )

}

export default Room;