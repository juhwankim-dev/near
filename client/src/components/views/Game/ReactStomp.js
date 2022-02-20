// react-stomp, SockJsClient 사용한 방법 1

import React, { useRef } from 'react'
import SockJsClient from 'react-stomp';

export default function Client(){
    const $websocket = useRef(null); 
    let topics = ['/topic/'+userId];

    return (
        <div>       
            <SockJsClient
              url= 'https://i6d203.p.ssafy.io:8185/api/game/room'
              topics={topics}
              onMessage={msg => console.log(msg)}
              ref={$websocket}
            />
        </div>    
    )
}