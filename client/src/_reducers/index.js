import { combineReducers } from "redux"; //combineReducer을 사용해서 rootReducer 하나로 합쳐줌
import user from './user_reducer';

const rootReducer = combineReducers({
  user
})

export default rootReducer;
