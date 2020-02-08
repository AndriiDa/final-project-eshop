import React from 'react';
import styles from './CartItem.module.scss';
import Right from "../../common/buttons/Right/Right";
import Left from "../../common/buttons/Left/Left";

const CartItem = (props) => {
    return (
        <div className={styles.cart}>
            <div className={styles.item}>
                Cart_Id: {props.cartItem.id}
            </div>
            <div className={styles.item}>
                User_Id: {props.cartItem.userId}
            </div>

            <div className={styles.item}>
                Product_Id: {props.cartItem.productId}
            </div>
            <div className={styles.quantityBlock}>
                <p className={styles.quantityBlockText}>quantity:</p>
                <Left className={styles.leftArrow}
                      onClickF={props.submitItemQuantityInCart}
                      item={props.cartItem}
                />
                <span className={styles.quantityBlockNumber}>
                    {props.cartItem.quantity}
                </span>
                <Right className={styles.leftArrow}
                       onClickF={props.submitItemQuantityInCart}
                       item={props.cartItem}
                />
            </div>
        </div>
    );
};

export default CartItem;
