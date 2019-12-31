const INITIALIZE_CART = 'INITIALIZE-CART';

let initialState = {
    cart: []
};

const cartPageReducer = (state = initialState, action) => {
    switch (action.type) {
        case INITIALIZE_CART: {
            return {
                ...state,
                cart: [...action.cart]
            };
        }
        default:
            return state;
    }
};

export const initializeCart = (cart) => {
    return {
        type: INITIALIZE_CART,
        cart: cart
    }
};

export default cartPageReducer;