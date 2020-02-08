import React from 'react';
import styles from './Left.module.scss';

const Left = (props) => {
    return (
        <button className={styles.triangleLeft}
                onClick={() => {
                    props.onClickF(props.item, props.item.quantity - 1)
                }}
        >
        </button>
    )
};

export default Left;