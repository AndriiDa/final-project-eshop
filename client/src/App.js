import React from "react";
import {Route} from "react-router-dom";

import Home from "./components/Home/home";
import Header from "./components/Header/header";
import Footer from "./components/Footer/footer";
import Categories from "./components/Categories/categories";
import Brands from "./components/Brands/brands";
import Vendors from "./components/Vendors/vendors";
import ProductsContainer from "./components/ProductsContainer/productsContainer";
import ProductContainer from "./components/ProductContainer/productContainer";
import CommentsContainer from "./components/CommentsContainer/commentsContainer";
import Sidebar1 from "./components/Sidebar1/sidebar1";

import "./App.scss";

const App = () => {
    return (
        <div className="app-wrapper">
            <Header/>
            <Sidebar1/>
            <div className="app-wrapper-content">
                <Route exact path="/" render={() => <ProductsContainer/>}/>
                <Route path="/categories" render={() => <Categories/>}/>
                <Route path="/brands" render={() => <Brands/>}/>
                <Route path="/vendors" render={() => <Vendors/>}/>
                <Route path="/products" exact render={() => <ProductsContainer/>}/>
                <Route path="/products/:productId?" render={() => <ProductContainer/>}/>
                <Route path="/comments" render={() => <CommentsContainer/>}/>
                <Route path="/oldhomeexample" render={() => <Home/>}/>
            </div>
            <Footer/>
        </div>
    );
};

export default App;
