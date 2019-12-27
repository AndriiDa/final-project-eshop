import React from "react";

import s from "./commentItem.module.scss";

const CommentItem =(props) => {
    return (
        <div className={s.comment}>{props.body}</div>
    );
};

export default CommentItem;
