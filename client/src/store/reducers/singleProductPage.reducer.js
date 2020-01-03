const SET_PRODUCT = 'SET_PRODUCT';

let initialState = {
    product: null,
};

const singleProductPageReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_PRODUCT: {
            return {
                ...state,
                product: action.product
            };
        }
        default:
            return state;
    }
};

export const setProduct = (product) => {
    return {
        type: SET_PRODUCT,
        product: product
    }
};

export default singleProductPageReducer;