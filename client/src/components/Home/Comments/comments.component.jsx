import React from "react";
import CommentComponent from "./Comment/comment.component"
import s from './comments.module.scss';

const CommentsComponent = (props) => {

    let comments = props.comments;
    let commentItems = comments.map(comment => {
        return <CommentComponent comment={comment}/>
    });

    let addNewComment = () => {
        props.onAddComment();
    };

    let onCommentChange = (e) => {
        props.updateCommentText(e.target.value);
    };

    let removeComment = () => {
        alert("remove");
    };

    return (
        <div className={s.container}>
            <div className={s.commentarea}>
                <textarea onChange={onCommentChange} value={props.newCommentText}/>
                <button onClick={addNewComment} className={s.button}>Add</button>
                <button onClick={removeComment} className={s.button}>Remove</button>
            </div>
            <div>
                {commentItems}
            </div>
        </div>
    );
};

export default CommentsComponent;
