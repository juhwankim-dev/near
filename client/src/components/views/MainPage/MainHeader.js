//rafce
import React from 'react';
import './MainHeader.css';
import './MainHeaderSun.css';


const MainHeader = () => {

  
  return (
    
    <div>
    <div class="header">

    <div class="inner-header flex">

	<div class="stars"></div>
	{/* <div class="arm left">
		<div>
			<div></div>
		</div>
	</div>
	<div class="arm right">
		<div>
			<div></div>
		</div>
	</div> */}
	<div class="sun">
		<div class="face">
			<div class="eyes"></div>
			<div class="mouth"></div>
		</div>
	</div>
    <h1>당신의 곁에 N:ear</h1>
    <div>
    <div>
</div>
    </div>
    </div>
    
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
    

    <div class="content flex">
      <p>흠</p>
    </div>

      
    </div>
  );
};

export default MainHeader;
