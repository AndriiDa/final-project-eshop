import SignInContent from '../components/PopUp/SignInContent/SignInContent'
import SignUpContent from '../components/PopUp/SignUpContent/SignUpContent'


export const CONTENT_TYPES = {
    signIn: 'signIn',
    signUp: 'signUp',
};

export const getPopUpContentByType = (contentType) => {

    switch (contentType) {
        case CONTENT_TYPES.signIn:
            return SignInContent;
        case CONTENT_TYPES.signUp:
            return SignUpContent;
        default:
            return null;
    }
};





