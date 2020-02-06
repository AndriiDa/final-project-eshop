import {cartApi} from "../../api/Api";

const INITIALIZE_CART = 'fs7-eshop/cart/INITIALIZE-CART';

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

export const requestCart = (loginName) => {
    return (dispatch) => {
        cartApi.getCartByLoginName(loginName)
            .then(
                data => {
                    dispatch(initializeCart(data.content));
                }
            );
    }
};

export default cartPageReducer;