import React from 'react';
import styles from './Right.module.scss';

const Right = (props) => {
    return (
        <button className={styles.triangleRight}
                onClick={() => {
                    props.onClickF(props.item, props.item.quantity + 1)
                }}
        >
        </button>
    )
};

export default Right;