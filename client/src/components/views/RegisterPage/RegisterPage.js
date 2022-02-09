import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { registerUser } from '../../../_actions/user_action';
import toast, { Toaster } from 'react-hot-toast';
import '../LoginPage/Accounts.css';
import axios from 'axios';

function RegisterPage(props) {
  let navigate = useNavigate();
  const dispatch = useDispatch();

  const [Email, setEmail] = useState('');
  const [EmailCheck, setEmailCheck] = useState(false);
  const [Id, setId] = useState('');
  const [Password, setPassword] = useState('');
  const [ConfirmPasword, setConfirmPasword] = useState('');
  const [Nickname, setNickname] = useState('');

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
    } else if (pw.length < 8 || pw.length > 20) {
      toast.error('비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.');
      return false;
    } else if (pw.search(/\s/) !== -1) {
      toast.error('비밀번호는 공백 없이 입력해주세요.');
      return false;
    } else if (num < 0 || eng < 0 || spe < 0) {
      toast.error('영문,숫자, 특수문자를 혼합하여 입력해주세요.');
      return false;
    } else if (nickname.length < 1 || nickname.length > 6) {
      toast.error('닉네임은 1자리 ~ 6자리 이내로 입력해주세요.');
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


   // 이메일 중복 체크
   const emailCheck = () => {
    axios({
      method: 'get',
      url: `https://hoonycode.loca.lt/api/sign/checkemail/${Email}`,
    })
      .then((res) => {
        if (res.data.isDuple) {
          ;
        } else {
          toast.error('이미 가입한 메일입니다.');
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };
  

  const onSubmit = (e) => {
		e.preventDefault();
    if (Id === '' || Id === undefined || Id === null) {
      toast.error('ID를 입력하세요');
      return;
    }

    if (!check()) {
      return;
    }

		if (Password === ConfirmPasword) {

      axios.post('https://hoonycode.loca.lt/api/sign/signup', 
      JSON.stringify({  
        email: Email,
        uid: Id,
        password: Password,
        nickname: Nickname,
        type: 'none' }), 
      { headers: {
        "Content-Type": `application/json`,
      },
    })
    .then((res) => {
      console.log(res);
    });
		}
	};

  const onSubmitHandler = (e) => {
    e.preventDefault(); 
    if (Id === '' || Id === undefined || Id === null) {
      toast.error('ID를 입력하세요');
      return;
    }

    if (!check()) {
      return;
    }

    if (Email === '' || Email === undefined || Email === null) {
      toast.error('이메일을 입력하세요');
      return;
    }


    
    if (Password === ConfirmPasword) {
      const body = {
        email: Email,
        uid: Id,
        password: Password,
        nickname: Nickname,
        type: 'none',

      };
        

      dispatch(registerUser(body))
      .then(() => { 
        toast.success('회원가입이 완료되었습니다.');
        navigate("/login");
        
      });
    
    }else {
      toast.error('비밀번호가 일치하지 않습니다');
    }
   };

   return (
  <div>
  <form style={{
    display:'flex', justifyContent: 'center', alignItems: 'center'
    ,width: '100%', height: '100vh'}}
        onSubmit={onSubmitHandler}>
  <div class="login-wrap">
	<div class="login-html">
    <div><h1>N:ear</h1></div>
		<input id="tab-1" type="radio" name="tab" class="sign-in" onClick={()=>{ navigate('/login')}} /><label for="tab-1" class="tab">Sign In</label>
    {/* SIGN IN버튼 누를 경우 login페이지로 랜더링되게 변경 */}
		<input id="tab-2" type="radio" name="tab" class="sign-up" checked /><label for="tab-2" class="tab">Sign Up</label>
	 

     <div class="login-form">
   
        <div class="sign-up-htm">
          <div class="group">
            <label for="user" class="label">ID</label>
            <input id="user" type="text" class="input" placeholder="6~20자 내로 입력해주세요." onChange={onIdHandler}/>
          </div>
          <div class="group">
            <label for="pass" class="label">Password</label>
            <input id="pass" type="password" class="input" data-type="password" placeholder="문자, 숫자, 기호를 조합하여 8~20자 내로 입력해주세요."  onChange={onPasswordHanlder}/>
          </div>
          <div class="group">
            <label for="pass" class="label">Repeat Password</label>
            <input id="pass" type="password" class="input" data-type="password" placeholder="비밀번호를 확인해주세요" onChange={onConfirmPasswordHandler}/>
          </div>
          <div class="group">
            <label for="pass" class="label">Nickname</label>
            <input id="pass" type="text" class="input" placeholder="1~6자 내로 입력해주세요." onChange={onNicknameHanlder}/>
          </div>
          <div class="group">
            <label for="pass" class="label">Email Address</label>
            <input id="pass" type="email" class="input" placeholder="메일주소를 입력해주세요." onChange={onEmailHandler}/>
            <button onClick={emailCheck}>메일 중복 확인</button>
          </div>
          <div class="group">
            <input type="submit" class="button" value="Sign Up" />
          </div>
          <div class="hr"></div>
          <div class="foot-lnk">
            <label for="tab-1" onClick={()=>{ navigate('/login')}} >Already Member?</label>
          {/* Already Member? 버튼 누를 경우 login페이지로 랜더링되게 변경 */}
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


export default RegisterPage;