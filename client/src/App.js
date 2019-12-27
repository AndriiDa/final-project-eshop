import React from "react";
import {Route, Switch} from "react-router-dom";

import HomeComponent from "./components/Home/home.component";
import Header from "./components/Header/header";
import Footer from "./components/Footer/footer";
import CategoriesComponent from "./components/Categories/categories.component";
import BrandsComponent from "./components/Brands/brands.component";
import VendorsComponent from "./components/Vendors/vendors.component";
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
                <Route path="/categories" render={() => <CategoriesComponent/>}/>
                <Route path="/brands" render={() => <BrandsComponent/>}/>
                <Route path="/vendors" render={() => <VendorsComponent/>}/>
                <Route path="/products" exact render={() => <ProductsContainer/>}/>
                <Route path="/products/:productId?" render={() => <ProductContainer/>}/>
                <Route path="/comments" render={() => <CommentsContainer/>}/>
                <Route path="/oldhomeexample" render={() => <HomeComponent/>}/>
            </div>
            <Footer/>
        </div>
    );
};

export default App;
