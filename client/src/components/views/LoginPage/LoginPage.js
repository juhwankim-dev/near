import React, { useState } from 'react';
import { withRouter, useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { loginUser } from '../../../_actions/user_action';
import toast, { Toaster } from 'react-hot-toast';
import './Accounts.css';
import { Container } from 'react-bootstrap';
import axios from 'axios';

function LoginPage(props) {
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
          localStorage.clear();
          localStorage.setItem('user', JSON.stringify(res.payload));
          navigate('/main');
      } else {
        toast.error('잘못된 정보를 입력하셧습니다.');
      }
    })
    .catch((err) => {
      console.error(err);
    });


  };


  return (
    <div>
      <form
        onSubmit={onSubmitHandler}
      >
  <div class="login-wrap">
	<div class="login-html">
    <div><h1>N:ear</h1></div>
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked/><label for="tab-1" class="tab">Sign In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up" onClick={()=>{ navigate('/register')}} /><label for="tab-2" class="tab">Sign Up</label>
    {/* SIGN UP버튼 누를 경우 register페이지로 랜더링되게 변경 */}
		<div class="login-form">
			<div class="sign-in-htm">
				<div class="group">
					<label for="user" class="label">ID</label>
					<input id="user" type="text" class="input" onChange={onIdHandler}/>
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<input id="pass" type="password" class="input" data-type="password"  onChange={onPasswordHanlder}/>
				</div>
				<div class="group">
					<input id="check" type="checkbox" class="check" />
					<label for="check"><span class="icon"></span> Keep me Signed in</label>
				</div>
				<div class="group">
					<input type="submit" class="button" value="Sign In"/>
				</div>
        
				<div class="hr"></div>
				<div class="foot-lnk">
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
