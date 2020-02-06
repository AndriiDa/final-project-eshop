import {ACCESS_TOKEN} from '../../constants';
import {usersApi} from '../../api/Api';
import {stopSubmit} from 'redux-form';

const SET_USER_DATA = 'fs7-eshop/auth/SET_USER_DATA';

let initialState = {
    userId: null,
    email: null,
    loginName: null,
    isLoggedIn: false,
};

const authReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_USER_DATA:
            return {
                ...state,
                ...action.payload
            };
        default:
            return state;
    }
};

export const setLoggedInUserData = (userId, email, loginName, isLoggedIn) => ({
    type: SET_USER_DATA, payload:
        {userId, email, loginName, isLoggedIn}
});

export const getLoggedInUserData = () => (dispatch) => {
    if (!localStorage.getItem(ACCESS_TOKEN)) {
        return dispatch(setLoggedInUserData(null, null, null, false));
    }
    usersApi.authMe()
        .then(response => {
            let {id, email, loginName} = response.data;
            return dispatch(setLoggedInUserData(id, email, loginName, true));
        })
        .catch(() => {
            return dispatch(setLoggedInUserData(null, null, null, false));
        });
};

export const login = (usernameOrEmail, password, rememberMe) => async (dispatch) => {
    usersApi.login(usernameOrEmail, password)
        .then(response => {
                if (response.data.tokenType === 'Bearer') {
                    // success, get auth data
                    localStorage.setItem(ACCESS_TOKEN, response.data.accessToken);
                    dispatch(getLoggedInUserData());
                }
            })
        .catch(err => {
            let message = err.response.data.message;
            dispatch(stopSubmit('login', {_error: message}));
        });
};

export const logout = () => (dispatch) => {
    localStorage.removeItem(ACCESS_TOKEN);
    return dispatch(setLoggedInUserData(null, null, null, false));
};

export default authReducer;