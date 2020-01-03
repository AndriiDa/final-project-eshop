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
    getProducts,
    checkProductsInCart
} from "../../store/reducers/productsPage.reducer";
import Products from "./Products";
import Preloader from "../common/Preloader/Preloader";

class ProductsContainer extends React.Component {
    componentDidMount() {
        this.props.getProducts(this.props.currentPage, this.props.pageSize);
        this.props.checkProductsInCart(this.props.products);
    }

    onPageChanged = (pageNumber) => {
        this.props.setCurrentPage(pageNumber);
        this.props.getProducts(pageNumber, this.props.pageSize);
        this.props.checkProductsInCart(this.props.products);
    };

    render() {
        return <>
            {this.props.isLoadingInProgress ? <Preloader/> : null}
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
            products: state.productsPage.products,
            pageSize: state.productsPage.pageSize,
            totalItemsCount: state.productsPage.totalItemsCount,
            currentPage: state.productsPage.currentPage,
            isLoadingInProgress: state.productsPage.isLoadingInProgress,
            togglingAddRemoveCartButtonInProgress: state.productsPage.togglingAddRemoveCartButtonInProgress,
            cart: state.productsPage.cart
        }
    };

export default connect(mapStateToProps, {
    initializeProducts,
    getProducts, checkProductsInCart, addProductToCart, deleteProductFromCart,
    setProductActive, setProductInactive,
    setCurrentPage, setTotalItemsCount, setIsLoadingInProgress
})
(
    ProductsContainer
)
;
