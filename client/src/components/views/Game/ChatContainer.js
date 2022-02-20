// ChatPresenter파일이 ChatContainer의 하위 컴포넌트
// ChatContainer 파일에서 소켓연결함

import { ChatPresenter } from "./ChatPresenter";
import React, { useEffect, useState } from "react";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';



let sockJS = new SockJS("https://i6d203.p.ssafy.io:8185/ws-stomp/websocket");
let stompClient = Stomp.over(sockJS);
stompClient.debug = () => { };

export const ChatContainer = ({}) => {
    const [contents, setContents] = useState([]);
    const [username, setUsername] = useState('');
    const [message, setMessage] = useState("");
    
    useEffect(() => {
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/roomId', (data) => {
                const newMessage = JSON.parse(data.body);
                addMessage(newMessage);
            });
        });
    }, [contents]); 

    const handleEnter = (username, content) => {
        const newMessage = { username, content };
        stompClient.send("/hello", {}, JSON.stringify(newMessage));
        setMessage("");
    };
    const addMessage = (message) => {
        setContents(prev => [...prev, message]);
    };
      return (
    <div className={"container"}>
      <ChatPresenter
        contents={contents}
        handleEnter={handleEnter}
        message={message}
        setMessage={setMessage}
        username={username}
        setUsername={setUsername}
      />
    </div>
      )
};
