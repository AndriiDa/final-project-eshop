import React from 'react';
import {connect} from 'react-redux';
import {setProduct} from "../../redux/reducers/singleProductPageReducer";
import {setIsFetchingInProgress} from "../../redux/reducers/commonTasksReducer";
import * as axios from "axios/index";
import Product from "./Product";
import {withRouter} from "react-router-dom";
import Preloader from "../common/Preloader/Preloader";

class ProductContainer extends React.Component {
    componentDidMount() {
        let id = this.props.match.params.productId;
        if (!id) {
            id = 1;
        }
        this.props.setIsFetchingInProgress(true);
        axios.get(`http://localhost:9000/api/v1/products/${id}`).then(
            response => {
                this.props.setIsFetchingInProgress(false);
                this.props.setProduct(response.data);
            }
        );
    };
    render() {
        return <>
            { (this.props.setIsFetchingInProgress && !this.props.product) ? <Preloader/> : <Product product={this.props.product}
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