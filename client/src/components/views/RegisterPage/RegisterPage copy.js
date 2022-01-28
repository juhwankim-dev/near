import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { registerUser } from '../../../_actions/user_action';

function RegisterPage(props) {
  const dispatch = useDispatch();

  const [Email, setEmail] = useState('');
  const [Id, setId] = useState('');
  const [Password, setPassword] = useState('');
  const [ConfirmPassword, setConfirmPassword] = useState('');
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


  const onEmailHandler = (event) => {
    setEmail(event.currentTarget.value) //email (ID로 바꿔야함 )state를 바꿔준다
  }
  const onNameHandler = (event) => {
    setName(event.currentTarget.value) 
  }
  const onPasswordHandler = (event) => {
    setPassword(event.currentTarget.value) 
  }
  const onConfirmPasswordHandler = (event) => {
    setConfirmPassword(event.currentTarget.value) 
  }

  const onSubmitHandler = (event) => {
    event.preventDefault(); //버튼 눌렀을 때 새로고침 방지

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

    if (Password !== ConfirmPassword) {
      return alert('비밀번호와 비밀번호 확인은 같아야 합니다.')
    }


    if (Password === ConfirmPassword) {
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
  }

    //같지않을 경우 아래로 진입 못함
    // let body = {
    //   email: Email,
    //   name: Name,
    //   password: Password,
    // }

    // dispatch(registerUser(body))
    //   .then(response => {
    //     if(response.payload.success) {
    //       props.history.push('/login') //회원가입 성공 했을 경우 로그인으로 페이지 이동
    //     } else {
    //         alert('"Failed to sign up"')
    //     }
    //   })
  

    <div style={{
      display:'flex', justifyContent: 'center', alignItems: 'center'
      ,width: '100%', height: '100vh'
    }}>
      <form style={{ display:'flex', flexDirection:'column'}}
        onSubmit={onSubmitHandler}
      >
        <label>Email</label>
        <input type="email" value={Email} onChange={onEmailHandler} /> 

        <label>Name</label>
        <input type="text" value={Name} onChange={onNameHandler} /> 
        {/* Name값은 text */}

        <label>Password</label>
        <input type="password" value={Password} onChange={onPasswordHandler} /> 

        {/* 타이핑을 할때 onChange가 바뀌고 State를 바꿔준다 그 후 value를 바꿔준다. */}
        <label>ConfirmPassword</label>
        <input type="confirmpassword" value={ConfirmPassword} onChange={onConfirmPasswordHandler} />
        <br />
        <button type="submit">
          Sign up
        </button>
      </form>
    </div>
 
}


export default RegisterPage;
