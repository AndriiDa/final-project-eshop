import {cartApi, productsApi} from "../../api/Api";

const SET_PRODUCT_ACTIVE = 'SET-PRODUCT-ACTIVE';
const SET_PRODUCT_INACTIVE = 'SET-PRODUCT-INACTIVE';
const INITIALIZE_PRODUCTS = 'INITIALIZE-PRODUCTS';
const SET_CURRENT_PAGE = 'SET-CURRENT-PAGE';
const SET_TOTAL_ITEMS_COUNT = 'SET-TOTAL-ITEMS-COUNT';
const IS_LOADING_IN_PROGRESS = 'IS-LOADING-IN-PROGRESS';
const TOGGLING_ADD_REMMOVE_CART_BUTTON_IN_PROGRESS = 'TOGGLING-ADD-REMMOVE-CART-BUTTON-IN-PROGRESS';
const IS_PRODUCT_IN_CART = 'IS_PRODUCT_IN_CART';

let initialState = {
    products: [],
    product: null,
    pageSize: 3,
    totalItemsCount: 1,
    currentPage: 1,
    isLoadingInProgress: false,
    togglingAddRemoveCartButtonInProgress: [],
    cart: []
};

const productsPageReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_PRODUCT_ACTIVE: {
            return {
                ...state,
                products: state.products.map(item => {
                    if (item.id === action.productId) {
                        return {
                            ...item,
                            isActive: true
                        }
                    }
                    return item;
                })
            };
        }
        case SET_PRODUCT_INACTIVE: {
            return {
                ...state,
                products: state.products.map(item => {
                    if (item.id === action.productId) {
                        return {
                            ...item,
                            isActive: false
                        }
                    }
                    return item;
                })
            };
        }
        case INITIALIZE_PRODUCTS: {
            return {
                ...state,
                //products: [...state.products, ...action.products]
                products: [...action.products]
            };
        }
        case SET_CURRENT_PAGE: {
            return {
                ...state,
                currentPage: action.currentPage
            };
        }
        case SET_TOTAL_ITEMS_COUNT: {
            return {
                ...state,
                totalItemsCount: action.totalItemsCount
            };
        }
        case IS_LOADING_IN_PROGRESS: {
            return {
                ...state,
                isLoadingInProgress: action.isLoadingInProgress
            };
        }
        case TOGGLING_ADD_REMMOVE_CART_BUTTON_IN_PROGRESS: {
            return {
                ...state,
                togglingAddRemoveCartButtonInProgress: action.togglingIsInProgress
                    ? [...state.togglingAddRemoveCartButtonInProgress, action.productId]
                    : state.togglingAddRemoveCartButtonInProgress.filter(id => id !== action.productId)
            };
        }
        case IS_PRODUCT_IN_CART: {
            return {
                ...state,
                cart: action.isProductInCart
                    ? [...state.cart, action.productId]
                    : state.cart.filter(id => id !== action.productId)
            };
        }
        default:
            return state;
    }
};

export const setProductActive = (id) => {
    return {
        type: SET_PRODUCT_ACTIVE,
        productId: id
    };
};

export const setProductInactive = (id) => {
    return {
        type: SET_PRODUCT_INACTIVE,
        productId: id
    }
};

export const initializeProducts = (products) => {
    return {
        type: INITIALIZE_PRODUCTS,
        products: products
    }
};

export const setCurrentPage = (currentPage) => {
    return {
        type: SET_CURRENT_PAGE,
        currentPage: currentPage
    }
};

export const setTotalItemsCount = (totalItemsCount) => {
    return {
        type: SET_TOTAL_ITEMS_COUNT,
        totalItemsCount: totalItemsCount
    }
};

export const setIsLoadingInProgress = (isLoadingInProgress) => {
    return {
        type: IS_LOADING_IN_PROGRESS,
        isLoadingInProgress: isLoadingInProgress
    }
};

export const setTogglingAddRemoveCartButtonInProgress = (togglingIsInProgress, productId) => {
    return {
        type: TOGGLING_ADD_REMMOVE_CART_BUTTON_IN_PROGRESS, togglingIsInProgress, productId
    };
};

export const setProductForCart = (isProductInCart, productId) => {
    return {
        type: IS_PRODUCT_IN_CART, isProductInCart, productId
    };
};

export const getProducts = (currentPage, pageSize) => {
    return (dispatch) => {
        dispatch(setIsLoadingInProgress(true));
        productsApi.getProducts(currentPage - 1, pageSize)
            .then(
                data => {
                    dispatch(initializeProducts(data.content));
                    data.content.forEach(product => {
                        dispatch(isProductInCart(product.id))
                    });

                    dispatch(setTotalItemsCount(data.totalElements));

                    dispatch(setIsLoadingInProgress(false));
                }
            );
    };
};

export const checkProductsInCart = (products) => {
    return (dispatch) => {
        products.forEach(product => {
            cartApi.existsByUserIdAndProductId(1, product.id)
                .then(data => {
                    if (data.resultCode === "OK") {
                        dispatch(setProductForCart(true, product.id));
                    } else {
                        dispatch(setProductForCart(false, product.id));
                    }
                })
                .catch(err => {
                    dispatch(setProductForCart(false, product.id));
                });
        });
    };
};

export const isProductInCart = (productId) => {
    return (dispatch) => {
        cartApi.existsByUserIdAndProductId(1, productId)
            .then(data => {
                if (data.resultCode === "OK") {
                    dispatch(setProductForCart(true, productId));
                } else {
                    dispatch(setProductForCart(false, productId));
                }
            })
            .catch(err => {
                dispatch(setProductForCart(false, productId));
            });
    };
};

export const addProductToCart = (userId, productId) => {
    return (dispatch) => {
        dispatch(setTogglingAddRemoveCartButtonInProgress(true, productId));
        cartApi.addItemToCart(userId, productId, 1)
            .then(response => {
                if (response.id) {
                    dispatch(setProductForCart(true, productId));
                }
                dispatch(setTogglingAddRemoveCartButtonInProgress(false, productId));
            })
    };
};

export const deleteProductFromCart = (userId, productId) => {
    return (dispatch) => {
        dispatch(setTogglingAddRemoveCartButtonInProgress(true, productId));
        cartApi.deleteItemFromCart(userId, productId)
            .then(response => {
                if (response.statusCode.toUpperCase() === 'OK') {
                    dispatch(setProductForCart(false, productId));
                }
                dispatch(setTogglingAddRemoveCartButtonInProgress(false, productId));
            });
    };
};

export default productsPageReducer;