import React from 'react';
import styles from './Button.module.scss';

const Button = (props) => {
    return (
        <button disabled={props.togglingAddRemoveCartButtonInProgress
            .some(id => id === props.product.id)}
                onClick={() => {

                    props.deleteProductFromCart(1, props.product.id)

                }} className={styles.buttonCart}>Delete From Cart</button>
    )
};

export default Button;


