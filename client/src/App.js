import React from "react";
import { BrowserRouter, Route } from "react-router-dom";
import "./App.scss";
import HeaderComponent from "./components/header/header.component";
import Login from "./components/login/login.component";
import Navbar from "./components/navbar/navbar.component";
import Slider from "./components/slider/slider.component";
import Goods from "./components/goods/goods.component";
import FooterComponent from "./components/footer/footer.component";

const App = () => {
  console.log("hi");
  return (
    <BrowserRouter>
      <div>
        <HeaderComponent />
        <Navbar />
        <Route path="/login" component={() => <Login />} />
        <Slider />
        <Goods />
        <FooterComponent />
      </div>
    </BrowserRouter>
  );
};

export default App;
