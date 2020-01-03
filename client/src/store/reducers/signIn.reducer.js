import * as signInActions from '../actions/signIn.action'
import {fromJS} from 'immutable';

const initState = fromJS({
    loginSuccess: false,
    err: null,
    // loading: false,
});

const reducer = (state = initState, action) => {
    switch (action.type) {
        case signInActions.USER_SIGN_IN_SUCCESS:
            return state
                .set('loginSuccess', true);
        case signInActions.USER_SIGN_IN_ERR:
            return state
                .set('err', fromJS(action.payload))
                .set('loginSuccess', fromJS(action.payload));
        default:
            return state;
    }
};

export default reducer;
