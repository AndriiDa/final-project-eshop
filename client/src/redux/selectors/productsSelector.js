import {createSelector} from 'reselect';

const getProductsSelector = (state) => {
    return state.productsPage.products;
};

export const getProducts = createSelector (getProductsSelector,
    (products) => {
    return products.filter(item => true);
});

export const getPageSize = (state) => {
    return state.productsPage.pageSize;
};

export const getTotalItemsCount = (state) => {
    return state.productsPage.totalItemsCount;
};

export const getCurrentPageNumber = (state) => {
    return state.productsPage.currentPageNumber;
};

export const getIsLoadingInProgress = (state) => {
    return state.productsPage.isLoadingInProgress;
};

export const getTogglingAddRemoveCartButtonInProgress = (state) => {
    return state.productsPage.togglingAddRemoveCartButtonInProgress;
};

export const getCart = (state) => {
    return state.productsPage.cart;
};