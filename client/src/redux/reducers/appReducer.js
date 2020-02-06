import {getLoggedInUserData} from './authReducer';

const INITIALIZED_SUCCESS = 'fs7-eshop/app/INITIALIZED_SUCCESS';

let initialState = {
    initialized: false,
};

const appReducer = (state = initialState, action) => {
    switch (action.type) {
        case INITIALIZED_SUCCESS:
            return {
                ...state,
                initialized: true
            };

        default:
            return state;
    }
};

export const initializedSuccess = () => ({type: INITIALIZED_SUCCESS});

export const initializeApp = () => (dispatch) => {
    let promise = dispatch(getLoggedInUserData());
    Promise.all([promise])
        .then(() => {
            dispatch(initializedSuccess());
        })
        .catch((e)=> {
            dispatch(initializedSuccess());
        });
};

export default appReducer;