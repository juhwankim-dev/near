// stomp 사용 

import SockJS from 'sockjs-client';
import StompJs from '@stomp/stompjs';

const client = new StompJs.Client({
  brokerURL: 'wss://i6d203.p.ssafy.io:8185/ws-stomp/websocket',
  debug: function(str){
    console.log(str);
  }
});

client.onConnect = function(frame){

};

client.onStompError = function(frame){
  console.log('Broker reported error:' + frame.headers['message']);
  
};

client.activate();
