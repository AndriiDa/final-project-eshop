import React from 'react';
import styles from './AppHeader.module.scss';
import headerLogo from '../../../assets/images/danitlogo.jpeg';
import {NavLink} from "react-router-dom";

const AppHeader = ({loginName, isLoggedIn, logout}) => {
    return <div className={styles.header}>
        <div>
            <img className={styles.logoImage} src={headerLogo} alt="logo"/>
            <span>DAN.IT FS7 E-Shop Final Project</span>
        </div>
        <div className={styles.loginBlock}>
            {
                isLoggedIn
                    ? <div>{loginName} - <button onClick={logout}>Log out</button></div>
                    : <NavLink to={'/login'}>Login</NavLink>
            }

        </div>
    </div>
};

export default AppHeader;