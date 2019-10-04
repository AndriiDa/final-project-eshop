import React from 'react';
import {Route} from "react-router-dom";
import './App.scss';
import Header from "./components/Header/Header";
import Login from "./components/Login/Login";
import Navbar from "./components/Navbar/Navbar";
import SliderForGoods from "./components/SliderForGoods/SliderForGoods";
import ListOfGoods from "./components/ListOfGoods/ListOfGoods";
import Footer from "./components/Footer/Footer";

const App = (props) => {
  console.log('hi');
  return (
    <div>
      <Header />
      <Navbar />
      <Route path='/login' component={() => <Login />}/>
      <SliderForGoods/>
      <ListOfGoods/>
      <Footer/>
    </div>
    )
};

export default App;
