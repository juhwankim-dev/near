import React from 'react';
import './OXQuizStartCnt.scss'

const OXQuizStartCnt = () => {
  
  alert("❤카메라 권한을 허용해주세요~ ❤ \n ❤동작이 맞을 경우 배경이 초록색🟩으로 바뀌어요❤ \n ❤해당 동작을 최소 5번 이상 반복 연습 해주세요❤ \n ❤💯 100점을 향해 🚀🚀🚀🚀❤");
  
  setTimeout(function() {
    // window.location.href = 'https://i6d203.p.ssafy.io/oxquiz';
    window.location.href = 'http://localhost:3000/oxquiz';
  },3500);
 
  return (
  <div>
<div className="count">
  <div className="count__colored-blocks">
    <div className="count__colored-blocks-rotater">
      <div className="count__colored-block"></div>
      <div className="count__colored-block"></div>
      <div className="count__colored-block"></div>
    </div>
    <div className="count__colored-blocks-inner"></div>
    <div className="count__text">Ready</div>
  </div>
  <div className="count__inner">
    <svg className="count__numbers" viewBox="0 0 100 100">
      <defs>
        <path className="count__num-path-1" d="M40,28 55,22 55,78"/>
        <path className="count__num-join-1-2" d="M55,78 55,83 a17,17 0 1,0 34,0 a20,10 0 0,0 -20,-10"/>
        <path className="count__num-path-2" d="M69,73 l-35,0 l30,-30 a16,16 0 0,0 -22.6,-22.6 l-7,7"/>
        <path className="count__num-join-2-3" d="M28,69 Q25,44 34.4,27.4"/>
        <path className="count__num-path-3" d="M30,20 60,20 40,50 a18,15 0 1,1 -12,19"/>
      </defs>
      <path className="count__numbers-path" 
            d="M-10,20 60,20 40,50 a18,15 0 1,1 -12,19 
               Q25,44 34.4,27.4
               l7,-7 a16,16 0 0,1 22.6,22.6 l-30,30 l35,0 L69,73 
               a20,10 0 0,1 20,10 a17,17 0 0,1 -34,0 L55,83 
               l0,-61 L40,28" />
    </svg>
  </div>
</div>
  </div>
    );
};

export default OXQuizStartCnt;
