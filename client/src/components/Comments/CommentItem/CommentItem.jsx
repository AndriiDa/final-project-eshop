import React from "react";

import styles from "./CommentItem.module.scss";

const CommentItem =(props) => {
    return (
        <div className={styles.comment}>{props.body}</div>
    );
};

export default CommentItem;
