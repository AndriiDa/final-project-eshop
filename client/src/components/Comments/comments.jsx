import React from "react";

import CommentItem from "./CommentItem/commentItem";
import ProductItem from "./ProductItem/productItem";

import s from "./comments.module.scss";

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
        <div className={s.products}>
            <div className={s.productItems}>
                {productItems}
            </div>
            <div className={s.commentItems}>
                {commentItems}
            </div>
            <div className={s.commentarea}>
                <textarea onChange={onCommentChange} value={newBody}></textarea>
                <button onClick={addComment} className={s.button}>Add</button>
            </div>
        </div>
    );
};

export default Comments;