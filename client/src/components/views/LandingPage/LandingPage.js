// rfce
import axios from 'axios';
import React from 'react';

function LandingPage(props) {

  // const onClickHandler =() => {
  //   axios.get('/api/users/logout')
  //     .then(response => {
  //       if(response.data.success) {
  //         props.history.push('/login') //logout이 정상적으로 되면 로그인페이지로 보내준다(향후 바꿔야함)
  //       } else {
  //         alert('로그아웃 하는데 실패 했습니다.')
  //       }
  //     })
  // }

  return (
    <div style={{
      display:'flex', justifyContent: 'center', alignItems: 'center'
      ,width: '100%', height: '100vh'
    }}>
      <h2>
        랜딩 페이지
      </h2>

      {/* <button onClick={onClickHandler}>
        로그아웃
      </button> */}
    </div>
  );
}

export default LandingPage;
