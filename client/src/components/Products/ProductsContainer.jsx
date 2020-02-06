import React from 'react';
import {connect} from 'react-redux';
import {
    setProductActive,
    setProductInactive,
    initializeProducts,
    setCurrentPage,
    setTotalItemsCount,
    setIsLoadingInProgress,
    addProductToCart,
    deleteProductFromCart,
    requestProducts,
    checkProductsInCart
} from "../../redux/reducers/productsPageReducer";
import Products from "./Products";
import LoadingIndicator from "../common/LoadingIndicator/LoadingIndicator";
import {
    getCart, getCurrentPageNumber,
    getIsLoadingInProgress,
    getPageSize, getProducts,
    getTogglingAddRemoveCartButtonInProgress,
    getTotalItemsCount
} from "../../redux/selectors/productsSelector";
import {compose} from "redux";

class ProductsContainer extends React.Component {
    componentDidMount() {
        this.props.requestProducts(this.props.currentPage, this.props.pageSize);
        this.props.checkProductsInCart(this.props.products);
    }

    onPageChanged = (pageNumber) => {
        this.props.setCurrentPage(pageNumber);
        this.props.requestProducts(pageNumber, this.props.pageSize);
        this.props.checkProductsInCart(this.props.products);
    };

    render() {
        return <>
            {this.props.isLoadingInProgress ? <LoadingIndicator/> : null}
            <Products products={this.props.products}
                      totalItemsCount={this.props.totalItemsCount}
                      pageSize={this.props.pageSize}
                      currentPage={this.props.currentPage}
                      onPageChanged={this.onPageChanged}
                      setProductActive={this.props.setProductActive}
                      setProductInactive={this.props.setProductInactive}
                      addProductToCart={this.props.addProductToCart}
                      deleteProductFromCart={this.props.deleteProductFromCart}
                      togglingAddRemoveCartButtonInProgress={this.props.togglingAddRemoveCartButtonInProgress}
                      cart={this.props.cart}
            />
        </>;
    }
}

let
    mapStateToProps = (state) => {
        return {
            products: getProducts(state),
            pageSize: getPageSize(state),
            totalItemsCount: getTotalItemsCount(state),
            currentPage: getCurrentPageNumber(state),
            isLoadingInProgress: getIsLoadingInProgress(state),
            togglingAddRemoveCartButtonInProgress: getTogglingAddRemoveCartButtonInProgress(state),
            cart: getCart(state)
        }
    };

export default compose(
    connect(mapStateToProps, {
            initializeProducts,
            requestProducts, checkProductsInCart, addProductToCart, deleteProductFromCart,
            setProductActive, setProductInactive,
            setCurrentPage, setTotalItemsCount, setIsLoadingInProgress
        }
    ))(ProductsContainer);