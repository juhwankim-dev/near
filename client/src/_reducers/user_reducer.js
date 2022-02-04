//전 State와 지금 action 을 더해서 nextState를 만듬
import {
  LOGIN_USER, REGISTER_USER, AUTH_USER
} from '../_actions/types';




export default function (state = {}, action) {
  switch (action.type) {
    case LOGIN_USER: //action과 type 둘다 가졌으므로 다음 nextState를 만들기 위해서 return해줌
        return { ...state, loginSuccess: action.payload } //...state는 스프레이트 오퍼레이터(위에 빈 상태를 그대로 가져옴)
      break;
    case REGISTER_USER:
      return { ...state, success: action.payload }
      break;
    case AUTH_USER:
      return { ...state, userData: action.payload } //유저가 로그인상태인지 아닌지 등을 백엔드에서 보내주면 여기에서 데이터 받는거임
      break;
    default:
      return state;
  }
}
