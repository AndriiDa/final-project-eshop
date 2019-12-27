import React from "react";
import {NavLink} from "react-router-dom";

import styles from "./sidebar1.module.scss";

const Sidebar1 = (props) => {

    return (
        <nav className={styles.nav}>
            <div className={styles.item}>
                <NavLink exact to="/" activeClassName={styles.active}>Home</NavLink>
            </div>
            <div className={styles.item}>
                <NavLink to="/categories" activeClassName={styles.active}>Categories</NavLink>
            </div>
            <div className={styles.item}>
                <NavLink to="/brands" activeClassName={styles.active}>Brands</NavLink>
            </div>
            <div className={styles.item}>
                <NavLink to="/vendors" activeClassName={styles.active}>Vendors</NavLink>
            </div>
            <div className={styles.item}>
                <NavLink to="/products" activeClassName={styles.active}>Products</NavLink>
            </div>
            <div className={styles.item}>
                <NavLink to="/comments" activeClassName={styles.active}>Comments</NavLink>
            </div>
            <div className={styles.item}>
                <NavLink to="/oldhomeexample" activeClassName={styles.active}>Old Home</NavLink>
            </div>
        </nav>
    );
};

export default Sidebar1;
