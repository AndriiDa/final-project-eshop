import React from "react";
import PropTypes from "prop-types";
import {NavLink} from "react-router-dom";

import AccordionWrapperComponent from "../../modules/Accordion/accordion-wrapper.component";
//import { InnerContent, MenuItem } from "./sidebar.styles";
import s from "./sidebar1.module.scss";

const Sidebar1 = () => {
    // const handleClickInChild = item => {
    //   window.console.log(item);
    // };

    return (
        <nav className={s.nav}>
            <div className={s.item}>
                <NavLink exact to="/" activeClassName={s.active}>Home</NavLink>
            </div>
            <div className={s.item}>
                <NavLink to="/categories" activeClassName={s.active}>Categories</NavLink>
            </div>
            <div className={s.item}>
                <NavLink to="/brands" activeClassName={s.active}>Brands</NavLink>
            </div>
            <div className={s.item}>
                <NavLink to="/vendors" activeClassName={s.active}>Vendors</NavLink>
            </div>
            <div className={s.item}>
                <NavLink to="/products" activeClassName={s.active}>Products</NavLink>
            </div>
            <div className={s.item}>
                <NavLink to="/comments" activeClassName={s.active}>Comments</NavLink>
            </div>
            <div className={s.item}>
                <NavLink to="/oldhomeexample" activeClassName={s.active}>Old Home</NavLink>
            </div>
        </nav>
    );
};

Sidebar1.defaultProps = {
    categoryItems: []
};

Sidebar1.propTypes = {
    categoryItems: PropTypes.instanceOf(Array)
};

export default Sidebar1;
