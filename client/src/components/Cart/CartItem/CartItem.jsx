import React from "react";
import styles from "./CartItem.module.scss";

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
            <div className={styles.item}>
                quantity: {props.cartItem.quantity}
            </div>
        </div>
    );
};

export default CartItem;
