import React from 'react';
import CartItem from './CartItem/CartItem';
import styles from './Cart.module.scss';

let Cart = (props) => {
    let cartItems = props.cart.map(item => {
        return <CartItem key={item.id} cartItem={item}
                         addProductToCart={props.addProductToCart}
                         deleteProductFromCart={props.deleteProductFromCart}
                         isProductInCart={props.isProductInCart}
                         increaseQuantityInCart={props.increaseQuantityInCart}
                         decreaseQuantityInCart={props.decreaseQuantityInCart}
                         submitItemQuantityInCart={props.submitItemQuantityInCart}
        />
    });
    // let pagesCount = Math.ceil(props.totalUsersCount / props.pageSize);
    // let pages = [];
    // for (let i = 1; i <= pagesCount; i++) {
    //     pages.push(i);
    // }
    return (
        <div>
            <h2 className={styles.header}>Cart</h2>
            {/*<div className={styles.pagination}>*/}
            {/*{pages.map(p => {*/}
            {/*return <span key={p} className={props.currentPage === p ? styles.selected : styles.pageNumber}*/}
            {/*onClick={(e) => {*/}
            {/*props.onPageChanged(p)*/}
            {/*}}>{p}</span>*/}
            {/*})}*/}
            {/*</div>*/}
            <div className={styles.cartSection}>
                <div className={styles.cart}>
                    <div className={styles.cartItems}>
                        {cartItems}
                    </div>
                </div>
                <div className={styles.buttonBlock}>
                    <button>Checkout</button>
                </div>
            </div>
        </div>
    );
};

export default Cart;