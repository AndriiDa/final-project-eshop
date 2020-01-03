import React from 'react';
import s from './closeIcon.module.scss'

const CloseIcon = ({onClick}) => {
    return <div className={s.close} onClick={onClick}/>
};

export default CloseIcon;