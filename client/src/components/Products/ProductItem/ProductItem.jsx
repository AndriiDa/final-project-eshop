import React from "react";
import styles from "./ProductItem.module.scss";
import {NavLink} from "react-router-dom";
import {cartApi} from "../../../api/Api";

const ProductItem = (props) => {
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
                            (props.product.isProductInCart)
                                ? <button onClick={() => {

                                    cartApi.deleteItemFromCart(1, props.product.id)
                                        .then(response => {
                                            if (response.statusCode.toUpperCase() === 'OK') {
                                                props.deleteProductFromCart(props.product.id)
                                            }
                                        });

                                }} className={styles.buttonCart}>Delete From Cart</button>
                                : <button onClick={() => {

                                    cartApi.addItemToCart(1, props.product.id, 1)
                                        .then(response => {
                                            if (response.id) {
                                                props.addProductToCart(props.product.id);
                                            }
                                        })

                                }} className={styles.buttonCart}>Add to Cart</button>
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

                        export default ProductItem;
