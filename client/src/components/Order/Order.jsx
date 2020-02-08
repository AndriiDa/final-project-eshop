import React from "react";
import styles from "./Order.module.scss";
import LoadingIndicator from "../common/LoadingIndicator/LoadingIndicator";

let Order = (props) => {
    if (!props.product) return <LoadingIndicator/>;
    return (
        <div className={styles.product}>
            <div className={styles.productInfo}>
                <div className={styles.productTitle}>
                    <div>
                        {props.product.title}
                    </div>
                    <div>
                        <img src={props.product.urlImg} className={styles.img} alt=""/>
                    </div>
                </div>
                <div className={styles.productDesription}>
                    {props.product.description}
                </div>
            </div>
            <div className={styles.productDetails}>
            <span>
            Item Qauntity: {props.product.quantity}
            </span>
                {/*<span>*/}
            {/*Product is:*/}
                    {/*{*/}
                        {/*(props.product.isActive)*/}
                            {/*? <button onClick={() => {*/}
                                {/*props.setProductInactive(props.product.id)*/}
                            {/*}} className={styles.button}>Active</button>*/}
                            {/*: <button onClick={() => {*/}
                                {/*props.setProductActive(props.product.id)*/}
                            {/*}} className={styles.button}>Inactive</button>*/}
                    {/*}*/}
            {/*</span>*/}
            </div>
        </div>
    );
};

export default Order;