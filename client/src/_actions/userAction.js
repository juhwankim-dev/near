import {
  REGISTER_USER,
  LOGIN_USER,
  LOGOUT_USER,
} from './types';
import { request } from '../utils/axios';


export function registerUser(dataToSubmit) {
  const data = request('POST', `https://hoonycode.loca.lt/api/sign/signup`, dataToSubmit);
  return {
    type: REGISTER_USER,
    payload: data,
  };
}


export function loginUser(dataToSubmit) {
  const data = request('POST', `/https://hoonycode.loca.lt/api/sign/login`, dataToSubmit);

  return {
    type: LOGIN_USER,
    payload: data,
  };
}

export function logoutUser() {
  const data = request('GET', `/logout`);

  return {
    type: LOGOUT_USER,
    payload: data,
  };
}