import React from "react";
import {Route} from "react-router-dom";

import Home from "./components/Home/Home";
import HeaderContainer from "./components/Header/HeaderContainer";
import Footer from "./components/Footer/Footer";
import Categories from "./components/Categories/Categories";
import Brands from "./components/Brands/Brands";
import Vendors from "./components/Vendors/Vendors";
import ProductsContainer from "./components/Products/ProductsContainer";
import ProductContainer from "./components/Product/ProductContainer";
import CommentsContainer from "./components/Comments/CommentsContainer";
import Sidebar1 from "./components/Sidebar1/Sidebar1";
import CartContainer from "./components/Cart/CartContainer";

import AppClasses from "./app.module.scss";
import PopUp from "./components/PopUp/PopUp";
import {getPopUpContentByType} from "./utils/getPopUpContentByType.util";
import {connect} from "react-redux";

const App = (props) => {
    return (
        <div className={[AppClasses.appWrapper].join(' ')}>
            {props.showPopup ? <div className={AppClasses.appWrapperPopup}>
                <PopUp content={getPopUpContentByType(props.contentType)}/>
            </div> : null}
            <HeaderContainer/>
            <Sidebar1/>
            <div className={AppClasses.appWrapperContent}>
                {/*<Route exact path="/acount" render={() => <Auth/>}/>*/}
                <Route exact path="/" render={() => <ProductsContainer/>}/>
                <Route path="/categories" render={() => <Categories/>}/>
                <Route path="/brands" render={() => <Brands/>}/>
                <Route path="/vendors" render={() => <Vendors/>}/>
                <Route path="/products" exact render={() => <ProductsContainer/>}/>
                <Route path="/products/:productId?" render={() => <ProductContainer/>}/>
                <Route path="/comments" render={() => <CommentsContainer/>}/>
                <Route path="/oldhomeexample" render={() => <Home/>}/>
                <Route path="/cart" render={() => <CartContainer/>}/>
            </div>
            <Footer/>
        </div>
    );
};

const mapStateToProps = store => {
    return {
        showPopup: store.popup.showPopup,
        contentType: store.popup.contentType,
    }
};

const mapDispatchToProps = dispatch => {
    return {}
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
