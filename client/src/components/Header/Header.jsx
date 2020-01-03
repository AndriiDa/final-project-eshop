import React from "react";
import {NavLink} from "react-router-dom";
import styles from "./Header.module.scss";
import logo from "../../assets/images/danitlogo.jpeg";
import {connect} from "react-redux";
import {showPopup} from "../../store/actions/popup.action";
import {CONTENT_TYPES} from "../../utils/getPopUpContentByType.util";

const Header = (props) => {
    return (<div className={styles.header}>
            <div className={styles.logo}>
                <img className={styles.logoImage} src={logo} alt="logo"/>
                DAN.IT fs7 Final Project E-Shop
            </div>
            <div className={styles.loginBlock} onClick={() => props.showPopup(null, CONTENT_TYPES.signIn)}>
                {'Login'}
                {/*{ props.isLoggedIn ? props.loginName :*/}
                {/*<NavLink to={"/login"}>Login</NavLink> }*/}
            </div>
        </div>
    )
};

const mapStateToProps = store => {
    return {}
};

const mapDispatchToProps = dispatch => {
    return {
        showPopup: (value, contentType) => dispatch(showPopup(value, contentType)),
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(Header);
