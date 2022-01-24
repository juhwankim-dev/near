import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { registerUser } from '../../../_actions/user_action';
import toast, { Toaster } from 'react-hot-toast';

function RegisterPage(props) {
  const dispatch = useDispatch();

  const [Email, setEmail] = useState('');
  const [Id, setId] = useState('');
  const [Password, setPassword] = useState('');
  const [ConfirmPasword, setConfirmPasword] = useState('');
  const [Nickname, setNickname] = useState('');

  // const [Email, setEmail] = useState("") //initialState처음에 빈칸이라서 ""
  // const [Name, setName] = useState("")
  // const [Password, setPassword] = useState("") //initialState처음에 빈칸이라서 ""
  // const [ConfirmPassword, setConfirmPassword] = useState("")

  const check = () => {
    const id = Id;
    const pw = Password;
    const nickname = Nickname;
    const num = pw.search(/[0-9]/g);
    const eng = pw.search(/[a-z]/gi);
    const spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if (id.length < 6 || id.length > 15) {
      toast.error('ID는 6자리 ~ 15자리 이내로 입력해주세요.');
      return false;
    // } else if (num < 0 || eng <0) {
    //   toast.error('영문, 숫자를 혼합하여 입력해주세요.');
    //   return false;
    } else if (nickname.length < 1 || nickname.length > 6) {
      toast.error('닉네임은 1자리 ~ 6자리 이내로 입력해주세요.');
      return false;
    } else if (pw.length < 8 || pw.length > 20) {
      toast.error('비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.');
      return false;
    } else if (pw.search(/\s/) !== -1) {
      toast.error('비밀번호는 공백 없이 입력해주세요.');
      return false;
    } else if (num < 0 || eng < 0 || spe < 0) {
      toast.error('영문,숫자, 특수문자를 혼합하여 입력해주세요.');
      return false;
    } else {
      return true;
    }
  };


  const onEmailHandler = (e) => {
    setEmail(e.currentTarget.value);
  };

  const onIdHandler = (e) => {
    setId(e.currentTarget.value);
  };

  const onPasswordHanlder = (e) => {
    setPassword(e.currentTarget.value);
  };
  const onNicknameHanlder = (e) => {
    setNickname(e.currentTarget.value);
  };

  const onConfirmPasswordHandler = (e) => {
    setConfirmPasword(e.currentTarget.value);
  };



  const onSubmitHandler = (e) => {
    e.preventDefault();
    if (Email === '' || Email === undefined || Email === null) {
      toast.error('이메일을 입력하세요');
      return;
    }

    if (Id === '' || Id === undefined || Id === null) {
      toast.error('ID를 입력하세요');
      return;
    }

    if (!check()) {
      return;
    }

    if (Password === ConfirmPasword) {
      const body = {
        email: Email,
        id: Id,
        password: Password,
        nickname: Nickname,
      };


      dispatch(registerUser(body))
        .then(response => {
          if(response.payload.success) {
            toast.success('회원가입이 완료되었습니다.');
            props.history.push('/login'); //회원가입 성공 했을 경우 로그인으로 페이지 이동
          }});
        } else {
            toast.error('비밀번호가 일치하지 않습니다');
              // alert('"Failed to sign up"')
          }
        
  };

  return (
    <div style={{
      display:'flex', justifyContent: 'center', alignItems: 'center'
      ,width: '100%', height: '100vh'
    }}>
      <form style={{ display:'flex', flexDirection:'column'}}
        onSubmit={onSubmitHandler}
      >
        <h1>회원가입</h1>
          <input
            className="account__input"
            type="email"
            placeholder="Email"
            value={Email}
            onChange={onEmailHandler}
          />
          <input
            className="account__input"
            type="text"
            placeholder="ID"
            value={Id}
            onChange={onIdHandler}
          />
          <input
            className="account__input"
            type="text"
            placeholder="Nickname"
            value={Nickname}
            onChange={onNicknameHanlder}
          />
          <input
            className="account__input"
            type="password"
            placeholder="Password"
            value={Password}
            onChange={onPasswordHanlder}
          />
          <input
            className="account__input"
            type="password"
            placeholder="Password Comfirmation"
            value={ConfirmPasword}
            onChange={onConfirmPasswordHandler}
          />
          <button className="account__button" type="submit" onClick={()=>{
            
          }} >
            회원가입
          </button>
        {/* <label>Email</label>
        <input type="email" value={Email} onChange={onEmailHandler} /> 

        <label>Name</label>
        <input type="text" value={Name} onChange={onNameHandler} /> 
        

        <label>Password</label>
        <input type="password" value={Password} onChange={onPasswordHandler} /> 

       
        <label>ConfirmPassword</label>
        <input type="confirmpassword" value={ConfirmPassword} onChange={onConfirmPasswordHandler} />
        <br />
        <button type="submit">
          Sign up
        </button> */}
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

export default RegisterPage;
