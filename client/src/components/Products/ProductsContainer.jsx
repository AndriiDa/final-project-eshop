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
    deleteProductFromCart
} from "../../redux/reducers/productsPageReducer";
import Products from "./Products";
import Preloader from "../common/Preloader/Preloader";
import {productsApi, cartApi} from "../../api/Api";

class ProductsContainer extends React.Component {
    componentDidMount() {
        this.props.setIsLoadingInProgress(true);
        productsApi.getProducts(this.props.currentPage - 1, this.props.pageSize)
            .then(
                data => {
                    this.props.initializeProducts(
                        data.content.map(product => ({
                            ...product,
                            isProductInCart: false
                        }))
                    );
                    this.props.products.forEach(product => {
                        cartApi.existsByUserIdAndProductId(1, product.id)
                            .then(data => {
                                if (data.resultCode === "OK") {
                                    this.props.addProductToCart(product.id);
                                } else {
                                    this.props.deleteProductFromCart(product.id);
                                }
                            })
                            .catch(err => {
                                this.props.deleteProductFromCart(product.id);
                            });
                    });
                    this.props.setIsLoadingInProgress(false);
                    this.props.setTotalItemsCount(data.totalElements);
                }
            );
    }

    onPageChanged = (pageNumber) => {
        this.props.setCurrentPage(pageNumber);
        this.props.setIsLoadingInProgress(true);
        productsApi.getProducts(pageNumber - 1, this.props.pageSize)
            .then(
                data => {
                    this.props.initializeProducts(
                        data.content.map(product => ({
                            ...product,
                            isProductInCart: false
                        }))
                    );
                    this.props.products.forEach(product => {
                        cartApi.existsByUserIdAndProductId(1, product.id)
                            .then(data => {
                                if (data.resultCode === "OK") {
                                    this.props.addProductToCart(product.id);
                                } else {
                                    this.props.deleteProductFromCart(product.id);
                                }
                            })
                            .catch(err => {
                                this.props.deleteProductFromCart(product.id);
                            });
                    });
                    this.props.setIsLoadingInProgress(false);
                    this.props.setTotalItemsCount(data.totalElements);
                }
            );
    };

    render() {
        return <>
            {this.props.isLoadingInProgress ? <Preloader/> : null}
            <Products products={this.props.products}
                      setTotalItemsCount={this.props.setTotalItemsCount}
                      pageSize={this.props.pageSize}
                      currentPage={this.props.currentPage}
                      onPageChanged={this.onPageChanged}
                      setProductActive={this.props.setProductActive}
                      setProductInactive={this.props.setProductInactive}
                      addProductToCart={this.props.addProductToCart}
                      deleteProductFromCart={this.props.deleteProductFromCart}
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
            isProductInCart: state.productsPage.isProductInCart
        }
    };

export default connect(mapStateToProps, {
    setProductActive, setProductInactive, initializeProducts,
    setCurrentPage, setTotalItemsCount, setIsLoadingInProgress,
    addProductToCart, deleteProductFromCart
})

(
    ProductsContainer
)
;