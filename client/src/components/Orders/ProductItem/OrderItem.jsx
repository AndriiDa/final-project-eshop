import React from "react";
import styles from "./ProductItem.module.scss";
import {NavLink} from "react-router-dom";

const OrderItem = (props) => {
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
                    {props.isLoggedIn}
                    {(props.isLoggedIn) &&
                    <div>
                        {(props.cart.some(id => id === props.product.id))
                            ? <button disabled={props.togglingAddRemoveCartButtonInProgress
                                .some(id => id === props.product.id)}
                                      onClick={() => {

                                          props.deleteProductFromCart(1, props.product.id)

                                      }} className={styles.buttonCart}>Delete From Cart</button>
                            : <button disabled={props.togglingAddRemoveCartButtonInProgress
                                .some(id => id === props.product.id)}
                                      onClick={() => {

                                          props.addProductToCart(1, props.product.id)

                                      }} className={styles.buttonCart}>Add to Cart</button>
                        }
                    </div>
                    }
                </div>
            </div>
            <div className={styles.productDetails}>
                            <span className={styles.productQuantity}>
                            Available Qauntity: {props.product.quantity}
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

export default OrderItem;
