export const SHOW_POPUP = 'SHOW_POPUP';
export const CLOSE_POPUP = 'CLOSE_POPUP';

export const showPopup = (value, contentType) => {
    return dispatch => {
        dispatch({
            type: SHOW_POPUP,
            value,
            contentType
        })
    }
};

export const closePopup = () => {
    return dispatch => {
        dispatch({
            type: CLOSE_POPUP,
        })
    }
};
