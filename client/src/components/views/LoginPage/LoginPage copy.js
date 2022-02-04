import React, { useState } from 'react';
import { withRouter, useHistory } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { loginUser } from '../../../_actions/user_action';
import toast, { Toaster } from 'react-hot-toast';
import './Account.css';

function LoginPage(props) {
  // let history = useHistory();
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

  // 기존 코드 
  dispatch(loginUser(body))
    .then(()=> {
       toast.success('로그인 성공!');
       navigate("/main");
       })
    .catch((err) => {
      console.error(err);
    });
  };
    // Axios.post('/api/user/login', body)
    // .then(Response => {
    // }) actions로 옮겨준다.
  


  // 기존 코드 
  dispatch(loginUser(body))
    .then(response => {
      if(response.payload.loginSuccess) {
        props.history.push('/') //로그인 성공 했을 경우 메인페이지(홈/처음)으로 이동
      } else {
        console.log(response)
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
      {/* <div className="form-container sign-in-container"> */}
      <div className="">
        {/* <form className="acccount__form" onSubmit={onSubmitHandler}> */}
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
        {/* <button onClick={()=>{ history.push('/signup')}} className="btn btn-primary">회원가입</button> */}
        </form>
      </div>
    </>
  );


  // const onSubmit = (e) => {
	// 	e.preventDefault();

  //     axios.post('https://hoonycode.loca.lt/api/sign/login', 
  //     {  
  //       uid: Id,
  //       password: Password,
  //       type: 'none',
  //     },

  //     )
  //   .then((res) => {
  //     console.log(res);
    
  //     }  )};

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



// 로그인을 진행하기위해서
// 첫번째 useDispatch(액션) 을 활용해서 액션을 dispatch해준다

// dispatch(loginUser(body))
//     .then(response => {
//         if(response.payload.loginSuccess) {
//         // localStorage.clear();
//         // localStorage.setItem('user', JSON.stringify(res.payload));
//         props.history.push('/');
//         // props.history.push('/select');
//         // window.location.reload();
//       } else {
//         toast.error('잘못된 정보를 입력하셧습니다.');
//       }
//     })
//     .catch((err) => {
//       console.error(err);
//     });
// };


//   const onSubmit = (e) => {
// 		e.preventDefault();


//       axios.post('https://hoonycode.loca.lt/api/sign/login', 
//       JSON.stringify({  
//         uid: Id,
//         password: Password,
//  }), 
//       { headers: {
//         "Content-Type": `application/json`,
//       },
//     })
//     .then((res) => {
//       console.log(res);
//       }  )};

 
// 기존 코드 
// dispatch(loginUser(body))
// .then(()=> {
//    toast.success('로그인 성공!');
//    navigate("/main");
//    })
// .catch((err) => {
//   console.error(err);
// });


// Axios.post('/api/user/login', body)
// .then(Response => {
// }) actions로 옮겨준다.
  