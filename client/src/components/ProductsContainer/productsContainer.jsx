import React from 'react';
import {connect} from 'react-redux';
import {
    setProductActive,
    setProductInactive,
    initializeProducts,
    setCurrentPage,
    setTotalUsersCount,
    setIsLoadingInProgress
} from "../../redux/reducers/productsPageReducer";
import Products from "../Products/products";
import * as axios from "axios/index";
import Preloader from "../common/Preloader/preloader";

class ProductsContainer extends React.Component {
    componentDidMount() {
        this.props.setIsLoadingInProgress(true);
        axios.get(`http://localhost:9000/api/v1/products/?page=${this.props.currentPage - 1}&size=${this.props.pageSize}`).then(
            response => {
                this.props.setIsLoadingInProgress(false);
                this.props.initializeProducts(response.data.content);
                this.props.setTotalUsersCount(response.data.totalElements);
            }
        );
    };

    onPageChanged = (pageNumber) => {
        this.props.setCurrentPage(pageNumber);
        this.props.setIsLoadingInProgress(true);
        axios.get(`http://localhost:9000/api/v1/products/?page=${pageNumber - 1}&size=${this.props.pageSize}`).then(
            response => {
                this.props.setIsLoadingInProgress(false);
                this.props.initializeProducts(response.data.content);
                this.props.setTotalUsersCount(response.data.totalElements);
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
        isLoadingInProgress: state.productsPage.isLoadingInProgress
    }
};

export default connect(mapStateToProps, {
    setProductActive, setProductInactive, initializeProducts, setCurrentPage, setTotalUsersCount, setIsLoadingInProgress
})(ProductsContainer);