import React from "react";
import ProductItem from "./ProductItem/ProductItem";
import styles from "./Products.module.scss";
import Paginator from "../common/Paginator/Paginator";

let Products = ({currentPage, onPageChanged, pageSize, totalItemsCount, ...props}) => {
    let productItems = props.products.map(product => {
        return <ProductItem key={product.id} product={product}
                            setProductActive={props.setProductActive}
                            setProductInactive={props.setProductInactive}
                            addProductToCart={props.addProductToCart}
                            deleteProductFromCart={props.deleteProductFromCart}
                            togglingAddRemoveCartButtonInProgress={props.togglingAddRemoveCartButtonInProgress}
                            cart={props.cart}
        />
    });
    return (
        <div>
            <h2 className={styles.header}>Products</h2>

            <Paginator currentPage={currentPage} onPageChanged={onPageChanged}
                       totalItemsCount={totalItemsCount} pageSize={pageSize}/>

            <div className={styles.products}>
                <div className={styles.productItems}>
                    {productItems}
                </div>
            </div>
        </div>
    );
};

export default Products;