const IS_FETCHING_IN_PROGRESS = 'IS-FETCHING-IN-PROGRESS';

let initialState = {
    isFetchingInProgress: false
};

const commonTasksReducer = (state = initialState, action) => {
    switch (action.type) {
        case IS_FETCHING_IN_PROGRESS: {
            return {
                ...state,
                isFetchingInProgress: action.isFetchingInProgress
            };
        }
        default:
            return state;
    }
};

export const setIsFetchingInProgress = (isFetchingInProgress) => {
    return {
        type: IS_FETCHING_IN_PROGRESS,
        isFetchingInProgress: isFetchingInProgress
    }
};

export default commonTasksReducer;