// react-stomp, SockJsClient 사용한 방법 2


import React, {useRef} from 'react'; 
import SockJsClient from 'react-stomp'; 

function Sock () { 
    const $websocket = useRef (null); 
    const handleMsg = msg => { console.log (msg); }; 
    const handleClickSendTo = () => { $websocket.current.sendMessage ('/sendTo'); }; 
    const handleClickSendTemplate = () => { $websocket.current.sendMessage ('/Template'); }; 
    return ( 
    <div> 
    <SockJsClient 
    url="https://i6d203.p.ssafy.io:8185/ws-stomp/websocket" 
    topics={['/topics/sendTo', '/topics/template', '/topics/api']} 
    onMessage={msg => { console.log (msg); }} ref={$websocket} /> 
    <button onClick={handleClickSendTo}>SendTo</button> 
    <button onClick={handleClickSendTemplate}>SendTemplate</button> 
    </div> 
    ); 
} 
    
export default Sock;

