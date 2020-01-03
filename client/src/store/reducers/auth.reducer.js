const SET_AUTH_USER_DATA = 'SET-AUTH-USER-DATA';

let initialState = {
    id: null,
    loginName: null,
    email: null,
    isLoggedIn: false
};

const authReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_AUTH_USER_DATA: {
            return {
                ...state,
                ...action.data,
                isLoggedIn: true
            };
        }

        default:
            return state;
    }
};

export const setAuthUserData = (id, loginName, email) => {
    return {
        type: SET_AUTH_USER_DATA,
        data: { id, loginName, email }
    };
};

export default authReducer;