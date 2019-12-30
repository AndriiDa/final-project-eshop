import React from 'react';
import {connect} from 'react-redux';
import {setProduct} from "../../redux/reducers/singleProductPageReducer";
import {setIsFetchingInProgress} from "../../redux/reducers/commonTasksReducer";
import Product from "../Product/Product";
import {withRouter} from "react-router-dom";
import Preloader from "../common/Preloader/Preloader";
import {cartApi} from "../../api/Api";

class CartContainer extends React.Component {
    componentDidMount() {
        let id = this.props.match.params.productId;
        if (!id) {
            id = 1;
        }
        this.props.setIsFetchingInProgress(true);
        cartApi.getCartByLoginName('ivanov')
            .then(data => {
                this.props.setIsFetchingInProgress(false);
                this.props.setProduct(data);
            }
        );
    };
    render() {
        return <>
            { (this.props.setIsFetchingInProgress && !this.props.product) ? <Preloader/> : <Cart product={this.props.product}
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
})(withRouter(CartContainer));