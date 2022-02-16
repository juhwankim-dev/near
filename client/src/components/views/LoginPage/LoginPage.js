import React, { useState } from 'react';
import { withRouter, useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { loginUser } from '../../../_actions/user_action';
import toast, { Toaster } from 'react-hot-toast';
import './Accounts.css';
import { Container } from 'react-bootstrap';
import axios from 'axios';

function LoginPage() {
  let navigate = useNavigate();
  const dispatch = useDispatch();

  const [Id, setId] = useState('');
  const [Password, setPassword] = useState('');

  const onIdHandler = (e) => {
    setId(e.currentTarget.value);
  };
  const onPasswordHanlder = (e) => {
    setPassword(e.currentTarget.value);
  };
  
  const chkPW = () => {
    const pw = Password;
    const num = pw.search(/[0-9]/g);
    const eng = pw.search(/[a-z]/gi);
    const spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if (pw.length < 8 || pw.length > 20) {
      toast.error('잘못된 정보를 입력했습니다.');
      return false;
    } else if (pw.search(/\s/) !== -1) {
      toast.error('비밀번호는 공백 없이 입력해주세요.');
      return false;
    } else {
      return true;
    }
  };


  const onSubmitHandler = (e) => {
    e.preventDefault(); //버튼 눌렀을 때 새로고침 방지
    if (!chkPW()) {
      return;
    }

    const body = {
      uid: Id,
      password: Password,
      type: 'none',
    };
    
    // 기존 코드 
    dispatch(loginUser(body))
    .then((res) => {
        if(res.payload !== undefined) {
          toast.success('로그인 성공!')
          let config = 
          //  { "token": JSON.stringify(res.payload.data.token)}
          //  { "token": (JSON.stringify(res.payload.data.token)||'').replace(/\"/gi, "")}
          //  (JSON.stringify(res.payload.data.token)||'')
          JSON.stringify(res.payload.data.token);
        
          
        // console.log(res.payload.data);
        console.log(config);
          localStorage.clear();
          // console.log(JSON.stringify(res.payload));
          localStorage.setItem('user', JSON.stringify(res.payload.data));
          localStorage.setItem('userid', JSON.stringify(res.payload.data.id));
          console.log(JSON.stringify(res.payload.data));
          // localStorage.setItem('user', JSON.stringify(res.payload));
          navigate('/main');

          axios
          .post(`https://hoonycode2.loca.lt/api/sign/userInfo/`,
           {token:config.replace(/\"/gi, "")}) // 두번째 인자로 config가 들어감(보안과 관련된 옵션들)
          //  {token : JSON.stringify(res.payload.data.token)} ) // 두번째 인자로 config가 들어감(보안과 관련된 옵션들)
          .then(response => {
              // let userInfo = {
              //     id: response.data.data.uid,
              //     email: response.data.data.email,
              //     password: response.data.data.password,
              //     nickname: response.data.data.nickname,
              // }
              console.log(response)
              // commit("loginSuccess", userInfo)
            })


      } else {
        toast.error('잘못된 정보를 입력하셨습니다.');
      }
    })
    .catch((err) => {
      console.error(err);
    });


  };


  return (
    <div>
      <form style={{
    display:'flex', justifyContent: 'center', alignItems: 'center'
    ,width: '100%', height: '100vh'}}
        onSubmit={onSubmitHandler}
      >
  <div className="login-wrap">
	<div className="login-html">
    <div><h1 className='headerst'>N:ear</h1></div>
		<input id="tab-1" type="radio" name="tab" className="sign-in" checked/><label for="tab-1" className="tab">Sign In</label>
		<input id="tab-2" type="radio" name="tab" className="sign-up" onClick={()=>{ navigate('/register')}} /><label for="tab-2" className="tab">Sign Up</label>
    {/* SIGN UP버튼 누를 경우 register페이지로 랜더링되게 변경 */}
		<div className="login-form">
			<div className="sign-in-htm">
				<div className="group">
					<label for="user" className="label">ID</label>
					<input id="user" type="text" className="input" onChange={onIdHandler}/>
				</div>
				<div className="group">
					<label for="pass" className="label">Password</label>
					<input id="pass" type="password" className="input" data-type="password"  onChange={onPasswordHanlder}/>
				</div>
				
				<div className="group">
					<input type="submit" className="button" value="Sign In"/>
				</div>
        
				<div className="hr"></div>
				<div className="foot-lnk">
					<a href="#forgot">Forgot Password?</a>
				</div>
			</div>
		</div>
	</div>
  </div>
  </form>
  
      <Toaster
        position="top-center"
        reverseOrder={true}
        toastOptions={{
          duration: 1000,
          style: {
            border: '1px solid #713200',
            padding: '16px',
            margin: '10vh',
            color: '#713200',
          },
        }}
      />
</div>
);
}
export default LoginPage;
