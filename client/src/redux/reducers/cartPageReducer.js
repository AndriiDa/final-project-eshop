import {cartApi} from "../../api/Api";

const INITIALIZE_CART = 'fs7-eshop/cart/INITIALIZE-CART';
const INCREASE_QUANTITY_IN_CART = 'fs7-eshop/cart/INCREASE-QUANTITY-IN-CART';
const DECREASE_QUANTITY_IN_CART = 'fs7-eshop/cart/DECREASE-QUANTITY-IN-CART';
const SET_QUANTITY_IN_CART = 'fs7-eshop/cart/SET-QUANTITY-IN-CART';

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
        case INCREASE_QUANTITY_IN_CART: {
            return {
                ...state,
                cart: state.cart.map(item => {
                    if (item.id === action.id) {
                        debugger;
                        return {
                            ...item,
                            quantity: item.quantity + 1
                        }
                    }
                    return item;
                })
            };
        }
        case DECREASE_QUANTITY_IN_CART: {
            return {
                ...state,
                cart: state.cart.map(item => {
                    if (item.id === action.id) {
                        return {
                            ...item,
                            quantity: item.quantity - 1
                        }
                    }
                    return item;
                })
            };
        }
        case SET_QUANTITY_IN_CART: {
            return {
                ...state,
                cart: state.cart.map(item => {
                    if (item.id === action.payload.id) {
                        return {
                            ...item,
                            quantity: action.payload.quantity
                        }
                    }
                    return item;
                })
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

export const increaseQuantityInCart = (id) => {
    return {
        type: INCREASE_QUANTITY_IN_CART,
        id: id
    }
};

export const decreaseQuantityInCart = (id) => {
    return {
        type: DECREASE_QUANTITY_IN_CART,
        id: id
    }
};

export const setQuantityInCart = (id, quantity) => {
    return {
        type: SET_QUANTITY_IN_CART,
        payload: {
            id: id,
            quantity: quantity
        }
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

export const submitItemQuantityInCart = (cart, quantity) => {
    return (dispatch) => {
        cartApi.submitItemQuantityInCart(cart, quantity)
            .then(
                data => {
                    dispatch(setQuantityInCart(cart.id, quantity));
                }
            );
    }
};

export default cartPageReducer;