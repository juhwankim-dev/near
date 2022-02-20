//rafce
import React from 'react';
import './MainHeader.css';
import './MainHeaderSun.css';
import './MainHeaderText.css';
// import jQuery from "jquery";
// import $ from 'jquery'; 
// window.$ = window.jQuery = jQuery;

const MainHeader = () => {

//   // text js
  document.addEventListener('DOMContentLoaded',function(event){
    var dataText = [ "당신의 곁에", "N:ear", "AI 수어 학습"];
    
    function typeWriter(text, i, fnCallback) {
      if (i < (text.length)) {
       document.querySelector("#word").innerHTML = text.substring(0, i+1) +'<span aria-hidden="true"></span>';
  
        setTimeout(function() {
          typeWriter(text, i + 1, fnCallback)
        }, 100);
      }
      else if (typeof fnCallback == 'function') {
        setTimeout(fnCallback, 700);
      }
    }
     function StartTextAnimation(i) {
       if (typeof dataText[i] == 'undefined'){
          setTimeout(function() {
            StartTextAnimation(0);
          }, 800);
       }
      else if (i < dataText[i].length) {
       typeWriter(dataText[i], 0, function(){
         StartTextAnimation(i + 1);
       });
      }
    }
    StartTextAnimation(0);
  });

  return (
    
    <div>
    {/* 해 */}
    <div class="header">
    <div class="inner-header flex">

	<div class="stars"></div>
	<div class="sun">
		<div class="face">
			<div class="eyes"></div>
			<div class="mouth"></div>
		</div>
	</div>

    <h1 id="word" className='h11'><span className='h12'>Hello, Near!</span></h1>
    <div>
    <div>
</div>
    </div>
    </div>

    {/* 파도 */}
    <div>
    <svg class="waves" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink"
    viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
    <defs>
    <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
    </defs>
    <g class="parallax">
    <use xlinkHref="#gentle-wave" x="48" y="0" fill="rgba(255,255,255,0.7" />
    <use xlinkHref="#gentle-wave" x="48" y="3" fill="rgba(255,255,255,0.5)" />
    <use xlinkHref="#gentle-wave" x="48" y="5" fill="rgba(255,255,255,0.3)" />
    <use xlinkHref="#gentle-wave" x="48" y="7" fill="#fff" />
    </g>
    </svg>
    </div>
    </div>
    
    {/* <div class="content flex">
    </div> */}
    </div>
  );
};

export default MainHeader;