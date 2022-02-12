//rafce
import React from 'react';
import './MainHeader.css';
import './MainHeaderSun.css';
import './MainHeaderText.scss';
import jQuery from "jquery";
import $ from 'jquery'; 
window.$ = window.jQuery = jQuery;

const MainHeader = () => {

  // text js
var
  words = ['당신의 곁에', 'N:ear','Communication Intelligence'],
  part,
  i = 0,
  offset = 0,
  len = words.length,
  forwards = true,
  skip_count = 0,
  skip_delay = 5,
  speed = 150;

var wordflick = function(){
  setInterval(function(){
      if (forwards) {
        if(offset >= words[i].length){
          ++skip_count;
          if (skip_count === skip_delay) {
            forwards = false;
            skip_count = 0;
          }
        }
      }
      else {
         if(offset === 0){
            forwards = true;
            i++;
            offset = 0;
            if(i >= len){
              i=0;
            } 
         }
      }
      part = words[i].substr(0, offset);
      if (skip_count === 0) {
        if (forwards) {
          offset++;
        }
        else {
          offset--;
        }
      }
    	$('.word').text(part);
  },speed);
};

$(document).ready(function(){
  wordflick();
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
    {/* <h2>당신의 곁에</h2> */}
    <h1 class="word"></h1>
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