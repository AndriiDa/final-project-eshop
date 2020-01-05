import * as signUpActions from '../actions/signUp.action'
import {fromJS} from 'immutable';

const initState = fromJS({
    registerSuccess: false,
    err: null,
    // loading: false,
});

const reducer = (state = initState, action) => {
    switch (action.type) {
        case signUpActions.USER_SIGN_UP_SUCCESS:
            return state
                .set('registerSuccess', true);
        case signUpActions.USER_SIGN_UP_ERR:
            return state
                .set('err', fromJS(action.payload))
                .set('registerSuccess', fromJS(action.payload));
        default:
            return state;
    }
};

export default reducer;
