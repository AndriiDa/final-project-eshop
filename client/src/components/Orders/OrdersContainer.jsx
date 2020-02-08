import React from 'react';
import {connect} from 'react-redux';

import Orders from "./Orders";
import LoadingIndicator from "../common/LoadingIndicator/LoadingIndicator";

import {compose} from "redux";

class OrdersContainer extends React.Component {
    componentDidMount() {
        //this.props.requestOrders(this.props.currentPage, this.props.pageSize);
        //this.props.checkOrdersInCart(this.props.products);
    }

    onPageChanged = (pageNumber) => {
        // this.props.setCurrentPage(pageNumber);
        // this.props.requestOrders(pageNumber, this.props.pageSize);
        // this.props.checkOrdersInCart(this.props.products);
    };

    render() {
        return <>
            {/*{this.props.isLoadingInProgress ? <LoadingIndicator/> : null}*/}
            <Orders
                // products={this.props.products}
                //     totalItemsCount={this.props.totalItemsCount}
                //     pageSize={this.props.pageSize}
                //     currentPage={this.props.currentPage}
                //     onPageChanged={this.onPageChanged}
                //     setProductActive={this.props.setProductActive}
                //     setProductInactive={this.props.setProductInactive}
                //     addProductToCart={this.props.addProductToCart}
                //     deleteProductFromCart={this.props.deleteProductFromCart}
                //     togglingAddRemoveCartButtonInProgress={this.props.togglingAddRemoveCartButtonInProgress}
                //     cart={this.props.cart}
                //     isLoggedIn={this.props.isLoggedIn}
            />
        </>;
    }
}

let
    mapStateToProps = (state) => {
        return {
            // products: getOrders(state),
            // pageSize: getPageSize(state),
            // totalItemsCount: getTotalItemsCount(state),
            // currentPage: getCurrentPageNumber(state),
            // isLoadingInProgress: getIsLoadingInProgress(state),
            // togglingAddRemoveCartButtonInProgress: getTogglingAddRemoveCartButtonInProgress(state),
            // cart: getCart(state),
            // isLoggedIn: state.auth.isLoggedIn
        }
    };

export default compose(
    connect(mapStateToProps, {
            // initializeOrders,
            // requestOrders, checkOrdersInCart, addProductToCart, deleteProductFromCart,
            // setProductActive, setProductInactive,
            // setCurrentPage, setTotalItemsCount, setIsLoadingInProgress
        }
    ))(OrdersContainer);