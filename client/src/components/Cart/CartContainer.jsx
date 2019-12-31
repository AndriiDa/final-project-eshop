import React from 'react';
import {connect} from 'react-redux';
import {initializeCart} from "../../redux/reducers/cartPageReducer";
import {withRouter} from "react-router-dom";
import {cartApi} from "../../api/Api";
import Cart from "./Cart";

class CartContainer extends React.Component {
    componentDidMount() {
        cartApi.getCartByLoginName("ivanov")
            .then(
                data => {
                    this.props.initializeCart(data.content);
                }
            );
    };
    render() {
        return <>
            <Cart cart={this.props.cart}
                />

        </>;
    }
}

let mapStateToProps = (state) => {
    return {
        cart: state.cartPage.cart
    }
};

export default connect(mapStateToProps, {
    initializeCart
})(withRouter(CartContainer));