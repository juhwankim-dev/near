import React, { useState } from 'react';
import { withRouter, useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { loginUser } from '../../../_actions/user_action';
import toast, { Toaster } from 'react-hot-toast';
import './Account.css';

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
      id: Id,
      password: Password,
    };


    console.log(body); 
    
    // 기존 코드 
    dispatch(loginUser(body))
      .then(response => {
        if(response.payload.loginSuccess) {
          props.history.push('/') //로그인 성공 했을 경우 메인페이지(홈/처음)으로 이동
        } else {
          toast.error('잘못된 정보를 입력하셨습니다.');
        }
      })
      .catch((err) => {
        console.error(err);
      });
    };


    // Axios.post('/api/user/login', body)
    // .then(Response => {
    // }) actions로 옮겨준다.
  
  

  return (
    <>
      <div style={{
      display:'flex', justifyContent: 'center', alignItems: 'center'
      ,width: '100%', height: '100vh'}}>
        <form style={{ display:'flex', flexDirection:'column'}}
        onSubmit={onSubmitHandler}>
          <h1>로그인</h1>
          <span className=""></span>
          <input
            className=""
            type="text"
            value={Id}
            placeholder="ID"
            onChange={onIdHandler}
          />
          <input
            className=""
            type="password"
            value={Password}
            placeholder="Password"
            onChange={onPasswordHanlder}
          />
          <button className="account__button" type="submit">
            로그인
          </button>
        <br />
        {/* <button onClick={()=>{ history.push('/signup')}} className="btn btn-primary">회원가입</button> */}
        </form>
        <button onClick={()=>{ navigate('/register')}} className="btn btn-primary">회원가입</button>

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
    </>
  );
}
  // return (
  //   <div style={{
  //     display:'flex', justifyContent: 'center', alignItems: 'center'
  //     ,width: '100%', height: '100vh'
  //   }}>
  //     <form style={{ display:'flex', flexDirection:'column'}}
  //       onSubmit={onSubmitHandler}
  //     >
  //       <label>Email</label>
  //       <input type="type" value={Id} onChange={onIdHandler} /> 
  //       {/* 타이핑을 할때 onChange가 바뀌고 State를 바꿔준다 그 후 value를 바꿔준다. */}
  //       <label>Password</label>
  //       <input type="password" value={Password} onChange={onPasswordHanlder} />
  //       <br />
  //       <button type="submit">
  //         Login
  //       </button>
  //     </form>

  //     <Toaster
  //       position="top-center"
  //       reverseOrder={true}
  //       toastOptions={{
  //         duration: 1000,
  //         style: {
  //           border: '1px solid #713200',
  //           padding: '16px',
  //           margin: '10vh',
  //           color: '#713200',
  //         },
  //       }}
  //     />

  //   </div>
  // );
  //     }
  //   }

export default LoginPage;
