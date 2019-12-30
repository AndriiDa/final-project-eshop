import React from "react";
import ProductItem from "./CartItem/CartItem";
import styles from "./Cart.module.scss";

let Cart = (props) => {
    let productItems = props.products.map(product => {
        return <ProductItem key={product.id} product={product}
                            setProductActive={props.setProductActive}
                            setProductInactive={props.setProductInactive}
                            addProductToCart={props.addProductToCart}
                            deleteProductFromCart={props.deleteProductFromCart}
                            isProductInCart={props.isProductInCart}
        />
    });
    let pagesCount = Math.ceil(props.totalUsersCount / props.pageSize);
    let pages = [];
    for (let i = 1; i <= pagesCount; i++) {
        pages.push(i);
    }
    return (
        <div>
            <h2 className={styles.header}>Cart</h2>
            <div className={styles.pagination}>
                {pages.map(p => {
                    return <span key={p} className={props.currentPage === p ? styles.selected : styles.pageNumber}
                                 onClick={(e) => {
                                     props.onPageChanged(p)
                                 }}>{p}</span>
                })}
            </div>
            <div className={styles.products}>
                <div className={styles.productItems}>
                    {productItems}
                </div>
            </div>
        </div>
    );
};

export default Cart;