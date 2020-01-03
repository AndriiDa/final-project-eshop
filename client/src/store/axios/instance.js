import axios from 'axios';
import {API_BASE_URL} from "../../constants";
import CookieService from '../../utils/CookieService.utils'
import {isAfter} from 'date-fns';

let axiosInstance = axios.create({
    baseURL: API_BASE_URL,
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json , multipart/form-data',
    withCredentials: true,
});

// axiosInstance.interceptors.request.use(config => {
//     const token = CookieService.getCookie('Bearer');
//     if (token) {
//         // if (isAfter(new Date(new Date().toUTCString()), new Date(token.expire)) && !flag) {
//         //     flag = true;
//         //     return axios
//         //         .post('/api/TokenAuth/RefreshToken',
//         //             {refreshToken: token.refreshToken},
//         //             {baseURL: url, withCredentials: true, headers: {Authorization: `Bearer ${token.accessToken}`}}
//         //         )
//         //         .then(({data}) => {
//         //             flag = false;
//         //             const {accessToken, refreshToken, expireInSeconds} = data.result;
//         //             CookieService.setCookie('Bearer', {accessToken, refreshToken, expireInSeconds});
//         //             config.headers.common.Authorization = `Bearer ${accessToken}`;
//         //             clientAxios.defaults.headers.common.Authorization = `Bearer ${accessToken}`;
//         //             return config;
//         //         })
//         //         .catch(err => {
//         //             return Promise.reject(err)
//         //         })
//         // }
//     }
//     return config;
// }, err => Promise.reject(err));


export default axiosInstance;
