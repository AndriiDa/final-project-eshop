import React, {Component} from 'react';
import {Route, Switch, withRouter} from 'react-router-dom';
import {compose} from 'redux';
import {connect} from "react-redux";
import {initializeApp} from "../redux/reducers/appReducer";
import LoadingIndicator from "../components/common/LoadingIndicator/LoadingIndicator";
import "./App.scss";
//import {withSuspense} from '../hoc/withSuspense';

import Home from '../components/Home/Home';
import HeaderContainer from "../components/common/Header/HeaderContainer";
import AppFooter from '../components/common/Footer/AppFooter';
import Categories from '../components/Categories/Categories';
import ProductsContainer from '../components/Products/ProductsContainer';
import Sidebar1 from '../components/Sidebar1/Sidebar1';
import LoginPage from '../components/common/Login/Login';
import ProductContainer from '../components/Product/ProductContainer';
import CartContainer from '../components/Cart/CartContainer';
import Brands from '../components/Brands/Brands';
import Vendors from '../components/Vendors/Vendors';
import CommentsContainer from '../components/Comments/CommentsContainer';

class App extends Component {
    componentDidMount() {
        this.props.initializeApp();
    }

    render() {
        if (!this.props.initialized) {
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
                        <Route path="/brands" render={() => <Brands/>}/>
                        <Route path="/vendors" render={() => <Vendors/>}/>
                        <Route path="/products" exact render={() => <ProductsContainer/>}/>
                        <Route path="/products/:productId?" render={() => <ProductContainer/>}/>
                        <Route path="/comments" render={() => <CommentsContainer/>}/>
                        <Route path="/oldhomeexample" render={() => <Home/>}/>
                        <Route path="/cart" render={() => <CartContainer/>}/>

                        <Route path='/login' render={() => <LoginPage/>}/>
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