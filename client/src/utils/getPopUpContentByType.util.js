import SignInContent from '../components/PopUp/SignInContent/SignInContent'


export const CONTENT_TYPES = {
    signIn: 'signIn',
};

export const getPopUpContentByType = (contentType) => {

    switch (contentType) {
        case CONTENT_TYPES.signIn:
            return SignInContent;
        default:
            return null;
    }
};





