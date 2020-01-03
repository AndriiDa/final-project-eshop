import axiosInstance from '../axios/instance'
import CookieService from '../../utils/CookieService.utils'

export const USER_SIGN_IN_SUCCESS = 'USER_SIGN_IN_SUCCESS';
export const USER_SIGN_IN_LOADING = 'USER_SIGN_IN_LOADING';
export const USER_SIGN_IN_CLEAR = 'USER_SIGN_IN_CLEAR';
export const USER_SIGN_IN_ERR = 'USER_SIGN_IN_ERR';

export const userSignInSuccess = () => {
    return {
        type: USER_SIGN_IN_SUCCESS,
    }
};

export const userSignInErr = (payload) => {
    return {
        type: USER_SIGN_IN_ERR,
        payload: payload
    }
};

export const userSignIn = (userLoginInfo) => {
    return async dispatch => {
        try {
            debugger
            const authenticate = await axiosInstance.post('auth/signin', userLoginInfo);
            const {accessToken, tokenType} = authenticate.data;
            CookieService.setCookie(tokenType, accessToken);
            axiosInstance.defaults.headers.common['Authorization'] = `${tokenType} ${accessToken}`;
            dispatch(userSignInSuccess());
        } catch (err) {
            dispatch(userSignInErr(err))
        }
    }
};
