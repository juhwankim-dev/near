// react-stomp, SockJsClient 사용한 방법 1

import SockJS from "sockjs-client";
import Stomp from "stompjs";

var stompClient = null;

export function Connect() {

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

export function disConnect() {
  if (stompClient !== null) {
    const headers = {

      // disconnect에 쓰이는 headers
    };
    stompClient.disconnect(function () {
      // disconnect 후 실행하는 곳
    }, headers);
  }
}
