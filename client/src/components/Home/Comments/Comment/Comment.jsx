import React from "react";
import s from './Comment.module.scss';

const Comment = (props) => {
    return (
        <div className={s.item}>
            <img src={props.comment.imgUrl}
                 alt=""/>
            {props.comment.message}
            <div>
                <span>like</span>{props.comment.likeCount}
                <span>dislike</span>{props.comment.dislikeCount}
            </div>
        </div>
    );
};

export default Comment;
