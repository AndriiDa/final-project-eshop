const SET_PRODUCT_ACTIVE = 'SET-PRODUCT-ACTIVE';
const SET_PRODUCT_INACTIVE = 'SET-PRODUCT-INACTIVE';
const INITIALIZE_PRODUCTS = 'INITIALIZE-PRODUCTS';
const SET_CURRENT_PAGE = 'SET-CURRENT-PAGE';
const SET_TOTAL_USERS_COUNT = 'SET-TOTAL-USERS-COUNT';
const IS_LOADING_IN_PROGRESS = 'IS-LOADING-IN-PROGRESS';
const ADD_PRODUCT_TO_CART = 'ADD-PRODUCT-TO-CART';
const DELETE_PRODUCT_FROM_CART = 'DELETE-PRODUCT-FROM-CART';

let initialState = {
    products: [
        // {id: 1, name: 'Product 1'},
        // {id: 2, name: 'Product 2'},
        // {id: 3, name: 'Product 3'},
        // {id: 4, name: 'Product 4'},
        // {id: 5, name: 'Product 5'}
    ],
    product: null,
    pageSize: 3,
    totalUsersCount: 1,
    currentPage: 1,
    isLoadingInProgress: false,
    isProductInCart: false
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
        case SET_TOTAL_USERS_COUNT: {
            return {
                ...state,
                totalUsersCount: action.totalUsersCount
            };
        }
        case IS_LOADING_IN_PROGRESS: {
                return {
                    ...state,
                    isLoadingInProgress: action.isLoadingInProgress
                };
        }
        case ADD_PRODUCT_TO_CART: {
            return {
                ...state,
                isProductInCart: true
            };
        }
        case DELETE_PRODUCT_FROM_CART: {
            return {
                ...state,
                isProductInCart: false
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

export const setTotalUsersCount = (totalUsersCount) => {
    return {
        type: SET_TOTAL_USERS_COUNT,
        totalUsersCount: totalUsersCount
    }
};

export const setIsLoadingInProgress = (isLoadingInProgress) => {
    return {
        type: IS_LOADING_IN_PROGRESS,
        isLoadingInProgress: isLoadingInProgress
    }
};

export const addProductToCart = () => {
    return {
        type: ADD_PRODUCT_TO_CART,
    }
};

export const deleteProductFromCart = () => {
    return {
        type: DELETE_PRODUCT_FROM_CART,
    }
};

export default productsPageReducer;