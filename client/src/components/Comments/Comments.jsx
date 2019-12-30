import React from 'react'
import styles from './Comments.module.scss';
import ProductItem from "./ProductItem/ProductItem";
import CommentItem from "./CommentItem/CommentItem";

const Comments = (props) => {

    let products = props.products;
    let comments = props.comments;
    let newBody = props.newBody;

    let productItems = products.map(product => {
        return <ProductItem id={product.id} name={product.name}/>
    });

    let commentItems = comments.map(comment => {
        return <CommentItem body={comment.body}/>
    });

    let addComment = () => {
        props.addNewProductComment();
    };

    let onCommentChange = (e) => {
        props.updateProductCommentBody(e.target.value);
    };

    return (
        <div className={styles.products}>
            <div className={styles.productItems}>
                {productItems}
            </div>
            <div className={styles.commentItems}>
                {commentItems}
            </div>
            <div className={styles.commentarea}>
                <textarea onChange={onCommentChange} value={newBody}></textarea>
                <button onClick={addComment} className={styles.button}>Add</button>
            </div>
        </div>
    );
};

export default Comments;