import React, { useState } from 'react';
import { withRouter } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { registerUser } from '../../_actions/userAction';
import toast from 'react-hot-toast';
import axios from 'axios';




function RegisterPage(props) {
  const [Email, setEmail] = useState('');
  const [Id, setId] = useState('');
  const [Password, setPassword] = useState('');
  const [ConfirmPasword, setConfirmPasword] = useState('');
  const [Nickname, setNickname] = useState('');
  const dispatch = useDispatch();

  // const [isActive, setActive] = useState(false);
  // const toggleClass = () => {
  //   setActive(!isActive);
  //   if (isActive) {
  //     toast.success('로그인 페이지 입니다.');
  //   } else {
  //     toast.success('회원가입 페이지 입니다.');
  //   }
  // };

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

      dispatch(registerUser(body)).then((res) => {
        
        toast.success('회원가입이 완료되었습니다.');
        // props.toggleClass();
        // axios.get(`https://hoonycode.loca.lt:8185/api/login `);
      });
    } else {
      toast.error('비밀번호가 일치하지 않습니다');
    }
  };

  return (
    <>
      <div className="form-container sign-up-container">
        <form className="acccount__form" onSubmit={onSubmitHandler}>
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
          <button className="account__button" type="submit"onClick={()=>{
            
          }} >
            회원가입
          </button>
        </form>
      </div>
    </>
  );
}

export default withRouter(RegisterPage);