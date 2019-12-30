import React from "react";
import styles from "./CartItem.module.scss";
import {NavLink} from "react-router-dom";

const CartItem = (props) => {
    return (
        <div className={styles.product}>
            <div className={styles.productInfo}>
                <div className={styles.productTitle}>
                    <div>
                        {props.product.title}
                    </div>
                    <div>
                        <NavLink to={'/products/' + props.product.id}>
                            <img src={props.product.urlThumb} className={styles.thumb} alt=""/>
                        </NavLink>
                    </div>
                </div>
                <div className={styles.productDesription}>
                    <div>
                        {props.product.description}
                    </div>
                    <div>
                        {
                            (props.isProductInCart)
                                ? <button onClick={() => {
                                    props.deleteProductFromCart()
                                }} className={styles.buttonCart}>Add to Cart</button>
                                : <button onClick={() => {
                                    props.addProductToCart()
                                }} className={styles.buttonCart}>Delete from Cart</button>
                        }
                    </div>
                </div>
            </div>
            <div className={styles.productDetails}>
               <span className={styles.productQuantity}>
                Item Qauntity: {props.product.quantity}
               </span>
                <span>
                Product is:
                    {
                        (props.product.isActive)
                            ? <button onClick={() => {
                                props.setProductInactive(props.product.id)
                            }} className={styles.button}>Active</button>
                            : <button onClick={() => {
                                props.setProductActive(props.product.id)
                            }} className={styles.button}>Inactive</button>
                    }
            </span>
            </div>
        </div>
    );
};

export default CartItem;
