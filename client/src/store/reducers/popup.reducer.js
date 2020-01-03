import * as ActionsTypePopup from '../actions/popup.action'

const initState = {
    showPopup: false,
    value: null,
    contentType: null
};

const popupReducer = (state = initState, action) => {
    switch (action.type) {
        case ActionsTypePopup.SHOW_POPUP:
            return {...state, showPopup: true, value: action.value, contentType: action.contentType};
        case ActionsTypePopup.CLOSE_POPUP:
            return {...state, ...initState};
        default:
            return state;
    }
};

export default popupReducer;
