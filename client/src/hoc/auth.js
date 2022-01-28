import React, { useEffect } from "react";
import { useDispatch } from 'react-redux';
import { auth } from '../_actions/user_action';

export default function (SpecificComponent, option, adminRoute = null) {

  //App.js페이지에서 해당 방법 사용 중 
  //option
  //null =>아무나 출입 가능한 페이지
  //true => 로그인한 유저만 출입이 가능한 페이지
  //false => 로그인한 유저는 출입 불가능한 페이지

  function AuthenticationCheck(props) {
    
    const dispatch = useDispatch();

    useEffect(() => {
      dispatch(auth()).then(response => {
        console.log(response)

        //로그인 하지 않은 상태
        if(!response.payload.isAuth) { //아마 백엔드에 isAuth자리에 뭐 써있는지 보고 바꿔야할듯
          if (option) {
            props.history.push('/login') //로그인 안한 상태에서 option이 true인 로그인한 유저만 출입 가능한 페이지를 들어가려고 하면 login페이지로 바로 보낸다
          }
        } else {
          //로그인 한 상태
          if(adminRoute && !response.payload.isAdmin) {
            props.history.push('/') //admin이 아닌데 admin만 들어갈 수 있는 페이지 들어가려고 하면 landingpage(메인/홈)페이지로 보냄
          } else {
            if(option === false)
            props.history.push('/') //로그인한 유저가 출입이 불가능한 곳을 들어가려고 할 때 landingpage(메인/홈)페이지로 보냄
          }
        }
      })
    },)

    return <SpecificComponent {...props} />; //백이랑 해보고 에러안나는지 확인해봐야함
  }
  return AuthenticationCheck
}