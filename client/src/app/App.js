import React, {Component} from 'react';
import {Route, Switch, withRouter} from 'react-router-dom';
import {compose} from 'redux';
import {connect} from "react-redux";
import {initializeApp} from "../redux/reducers/appReducer";
import LoadingIndicator from "../components/common/LoadingIndicator/LoadingIndicator";
import "./App.scss";
import {withSuspense} from '../hoc/withSuspense';

import Home from '../components/Home/Home';
import HeaderContainer from "../components/common/Header/HeaderContainer";
import AppFooter from '../components/common/Footer/AppFooter';
import Categories from '../components/Categories/Categories';
import ProductsContainer from '../components/Products/ProductsContainer';
import Sidebar1 from '../components/Sidebar1/Sidebar1';


//import {usersApi} from '../api/Api';
//import {ACCESS_TOKEN} from '../constants';

//import Login from "../components/common/Login/Login";

const ProductContainer = React.lazy(() => import('../components/Product/ProductContainer'));
const CartContainer = React.lazy(() => import('../components/Cart/CartContainer'));
const Brands = React.lazy(() => import('../components/Brands/Brands'));
const Vendors = React.lazy(() => import('../components/Vendors/Vendors'));
const CommentsContainer = React.lazy(() => import('../components/Comments/CommentsContainer'));

class App extends Component {
    componentDidMount() {
        //this.props.initializeApp();
    }

    render() {
        // if (!this.props.initialized) {
        //     return <LoadingIndicator />
        // }
        if (false) {
            return <LoadingIndicator />
        }
        return (
            <div className="app-wrapper">
                <HeaderContainer/>
                <Sidebar1/>
                <div className="app-wrapper-content">
                    <Switch>
                        <Route exact path="/" render={() => <ProductsContainer/>}/>
                        <Route path="/categories" render={() => <Categories/>}/>
                        <Route path="/brands" render={withSuspense(Brands)}/>
                        <Route path="/vendors" render={withSuspense(Vendors)}/>
                        <Route path="/products" exact render={() => <ProductsContainer/>}/>
                        <Route path="/products/:productId?" render={withSuspense(ProductContainer)}/>
                        <Route path="/comments" render={withSuspense(CommentsContainer)}/>
                        <Route path="/oldhomeexample" render={() => <Home/>}/>
                        <Route path="/cart" render={withSuspense(CartContainer)}/>

                        {/*<Route path="/login"*/}
                        {/*render={(props) => <Login onLogin={this.handleLogin} {...props} />}></Route>*/}
                        {/*<Route path="/signup" component={Signup}></Route>*/}


                    </Switch>
                </div>
                <AppFooter/>
            </div>
        );
    }
}

const mapStateToProps = (state) => ({
    initialized: state.app.initialized
});

export default compose(
    withRouter,
    connect(mapStateToProps, {initializeApp})
)(App);