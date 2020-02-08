import React from 'react';
import {connect} from 'react-redux';
import {setOrder} from "../../redux/reducers/singleOrderPageReducer";
import {setIsFetchingInProgress} from "../../redux/reducers/commonTasksReducer";
import Order from "./Order";
import {withRouter} from "react-router-dom";
import LoadingIndicator from "../common/LoadingIndicator/LoadingIndicator";
import {productsApi} from "../../api/Api";

class OrderContainer extends React.Component {
    componentDidMount() {
        let id = this.props.match.params.productId;
        if (!id) {
            id = 1;
        }
        this.props.setIsFetchingInProgress(true);
        productsApi.getOrderById(id)
            .then(
                data => {
                    this.props.setIsFetchingInProgress(false);
                    this.props.setOrder(data);
                }
            );
    };

    render() {
        return <>
            {(this.props.setIsFetchingInProgress && !this.props.product) ? <LoadingIndicator/> :
                <Order product={this.props.product}
                />}

        </>;
    }
}

let mapStateToProps = (state) => {
    return {
        product: state.singleOrderPage.product,
        isFetchingInProgress: state.commonTasks.isFetchingInProgress
    }
};

export default connect(mapStateToProps, {
    setOrder, setIsFetchingInProgress
})(withRouter(OrderContainer));