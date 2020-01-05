import axiosInstance from '../axios/instance'
import CookieService from '../../utils/CookieService.utils'

export const USER_SIGN_UP_SUCCESS = 'USER_SIGN_UP_SUCCESS';
export const USER_SIGN_UP_LOADING = 'USER_SIGN_UP_LOADING';
export const USER_SIGN_UP_CLEAR = 'USER_SIGN_UP_CLEAR';
export const USER_SIGN_UP_ERR = 'USER_SIGN_UP_ERR';

export const userSignUpSuccess = () => {
    return {
        type: USER_SIGN_UP_SUCCESS,
    }
};

export const userSignUpErr = (payload) => {
    return {
        type: USER_SIGN_UP_ERR,
        payload: payload
    }
};

export const userSignUp = (userRegisterInfo) => {
    return async dispatch => {
        try {
            debugger
            const register = await axiosInstance.post('auth/signup', userRegisterInfo);
            // const {accessToken, tokenType} = authenticate.data;
            // CookieService.setCookie(tokenType, accessToken);
            // axiosInstance.defaults.headers.common['Authorization'] = `${tokenType} ${accessToken}`;
            dispatch(userSignUpSuccess());
        } catch (err) {
            dispatch(userSignUpErr(err))
        }
    }
};
