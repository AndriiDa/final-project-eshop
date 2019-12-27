import React from 'react';
import {connect} from 'react-redux';
import {addNewCommentActionCreator, updateCommentTextActionCreator}
    from "../../../redux/reducers/homePageReducer";
import CommentsComponent from "../Comments/comments.component";

let mapStateToProps = (state) => {
    return {
        comments: state.homePage.comments,
        newCommentText: state.homePage.newCommentText
    }
};

let mapDispatchToProps = (dispatch) => {
    return {
        updateCommentText: (body) => {
            dispatch(updateCommentTextActionCreator(body))
        },
        onAddComment: () => {
            dispatch(addNewCommentActionCreator())
        }
    }
};

let CommentsContainer = connect(mapStateToProps, mapDispatchToProps)(CommentsComponent);

export default CommentsContainer;
