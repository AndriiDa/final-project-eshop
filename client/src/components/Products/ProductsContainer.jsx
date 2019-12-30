import React from 'react';
import {connect} from 'react-redux';
import {
    setProductActive,
    setProductInactive,
    initializeProducts,
    setCurrentPage,
    setTotalUsersCount,
    setIsLoadingInProgress,
    addProductToCart,
    deleteProductFromCart
} from "../../redux/reducers/productsPageReducer";
import Products from "./Products";
import Preloader from "../common/Preloader/Preloader";
import {productsApi} from "../../api/Api";

class ProductsContainer extends React.Component {
    componentDidMount() {
        this.props.setIsLoadingInProgress(true);
        productsApi.getProducts(this.props.currentPage - 1, this.props.pageSize)
            .then(
                data => {
                    this.props.setIsLoadingInProgress(false);
                    this.props.initializeProducts(data.content);
                    this.props.setTotalUsersCount(data.totalElements);
                }
            );
    };

    onPageChanged = (pageNumber) => {
        this.props.setCurrentPage(pageNumber);
        this.props.setIsLoadingInProgress(true);
        productsApi.getProducts(pageNumber - 1, this.props.pageSize)
            .then(
                data => {
                    this.props.setIsLoadingInProgress(false);
                    this.props.initializeProducts(data.content);
                    this.props.setTotalUsersCount(data.totalElements);
                }
            );
    };

    render() {
        return <>
            {this.props.isLoadingInProgress ? <Preloader/> : null}
            <Products products={this.props.products}
                      totalUsersCount={this.props.totalUsersCount}
                      pageSize={this.props.pageSize}
                      currentPage={this.props.currentPage}
                      onPageChanged={this.onPageChanged}
                      setProductActive={this.props.setProductActive}
                      setProductInactive={this.props.setProductInactive}
                      addProductToCart={this.props.addProductToCart}
                      deleteProductFromCart={this.props.deleteProductFromCart}
                      isProductInCart={this.props.isProductInCart}
            />
        </>;
    }
}

let mapStateToProps = (state) => {
    return {
        products: state.productsPage.products,
        pageSize: state.productsPage.pageSize,
        totalUsersCount: state.productsPage.totalUsersCount,
        currentPage: state.productsPage.currentPage,
        isLoadingInProgress: state.productsPage.isLoadingInProgress,
        isProductInCart: state.productsPage.isProductInCart
    }
};

export default connect(mapStateToProps, {
    setProductActive, setProductInactive, initializeProducts,
    setCurrentPage, setTotalUsersCount, setIsLoadingInProgress,
    addProductToCart, deleteProductFromCart
})(ProductsContainer);