import React, { useState } from 'react';
import { withRouter, useHistory } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { loginUser } from '../../_actions/userAction';
import toast from 'react-hot-toast';


function LoginPage(props) {
  let history = useHistory();

  const [Id, setId] = useState('');
  const [Password, setPassword] = useState('');
  const dispatch = useDispatch();
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
    e.preventDefault();
    if (!chkPW()) {
      return;
    }
    // 로그인을 진행하기위해서
    // 첫번째 useDispatch(액션) 을 활용해서 액션을 dispatch해준다
    const body = {
      id: Id,
      password: Password,
    };
    console.log(body);
    dispatch(loginUser(body))
      .then((res) => {
        if (res.payload !== undefined) {
          localStorage.clear();
          localStorage.setItem('user', JSON.stringify(res.payload));
          props.history.push('/select');
          window.location.reload();
        } else {
          toast.error('잘못된 정보를 입력하셧습니다.');
        }
      })
      .catch((err) => {
        console.error(err);
      });
  };

  return (
    <>
      <div className="form-container sign-in-container">
        <form className="acccount__form" onSubmit={onSubmitHandler}>
          <h1>로그인</h1>
          
          <span className="account__span"></span>
          <input
            className="account__input"
            type="text"
            value={Id}
            placeholder="ID"
            onChange={onIdHandler}
          />
          <input
            className="account__input"
            type="password"
            value={Password}
            placeholder="Password"
            onChange={onPasswordHanlder}
          />
          <button className="account__button" type="submit">
            로그인
          </button>
        <br />
        <button onClick={()=>{ history.push('/signup')}} className="btn btn-primary">회원가입</button>
        </form>
      </div>
    </>
  );
}

export default withRouter(LoginPage);