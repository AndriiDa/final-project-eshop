import React from 'react'
import './sidebar.styles.scss';

const SidebarComponent = (props) => {
    return (
        <div className="sidenav">
            <a href="#about">About</a>
            <a href="#services">Services</a>
            <a href="#clients">Clients</a>
            <a href="#contact">Contact</a>
        </div>
    )
};

export default SidebarComponent;
