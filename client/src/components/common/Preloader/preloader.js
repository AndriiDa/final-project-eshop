import React from 'react';

import preLoader from "../../../assets/images/preLoader.gif";

let Preloader = (props) => {
    return <div className={ { backgroundColor: 'white' } }>
        <img src={preLoader} alt=""/>
    </div>
};

export default Preloader;
