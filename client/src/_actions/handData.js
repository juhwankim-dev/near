import { GET_HAND_DATA } from './types';
import axios from "axios";
// import { request } from '../utils/axios';

export function getHandDataAction() {
  const data = request(
    'GET',
    `https://hoonycode.loca.lt/api/hand/`,
    {},
  );

  return {
    type: GET_FEED_DATA,
    payload: data,
  };
}
