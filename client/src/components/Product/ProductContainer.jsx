import React from 'react';
import {connect} from 'react-redux';
import {setProduct} from "../../store/reducers/singleProductPage.reducer";
import {setIsFetchingInProgress} from "../../store/reducers/commonTasks.reducer";
import Product from "./Product";
import {withRouter} from "react-router-dom";
import Preloader from "../common/Preloader/Preloader";
import {productsApi} from "../../api/Api";

class ProductContainer extends React.Component {
    componentDidMount() {
        let id = this.props.match.params.productId;
        if (!id) {
            id = 1;
        }
        this.props.setIsFetchingInProgress(true);
        productsApi.getProductById(id)
            .then(
                data => {
                    this.props.setIsFetchingInProgress(false);
                    this.props.setProduct(data);
                }
            );
    };

    render() {
        return <>
            {(this.props.setIsFetchingInProgress && !this.props.product) ? <Preloader/> :
                <Product product={this.props.product}
                />}

        </>;
    }
}

let mapStateToProps = (state) => {
    return {
        product: state.singleProductPage.product,
        isFetchingInProgress: state.commonTasks.isFetchingInProgress
    }
};

export default connect(mapStateToProps, {
    setProduct, setIsFetchingInProgress
})(withRouter(ProductContainer));
