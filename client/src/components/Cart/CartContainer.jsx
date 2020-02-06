import React from 'react';
import {connect} from 'react-redux';
import {requestCart} from '../../redux/reducers/cartPageReducer';
//import {withRouter} from 'react-router-dom';
import Cart from './Cart';
import {compose} from 'redux';
import {withAuthRedirect} from '../../hoc/withAuthRedirect';

class CartContainer extends React.Component {
    componentDidMount() {
        this.props.requestCart(this.props.loginName);
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
        cart: state.cartPage.cart,
        loginName: state.auth.loginName
    }
};


export default compose(
    connect(mapStateToProps, {requestCart}),
    //withRouter,
    withAuthRedirect
)(CartContainer);