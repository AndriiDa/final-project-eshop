import React from "react";
import {NavLink} from "react-router-dom";
import styles from "./Header.module.scss";
import logo from "../../assets/images/danitlogo.jpeg";

const Header = (props) => {
    return (<div className={styles.header}>
            <div className={styles.logo}>
                <img className={styles.logoImage} src={logo} alt=""/>
                DAN.IT fs7 Final Project E-Shop
            </div>
            <div className={styles.loginBlock}>
                { props.isLoggedIn ? props.loginName :
                <NavLink to={"/login"}>Login</NavLink> }
            </div>
        </div>
    )
};

export default Header;