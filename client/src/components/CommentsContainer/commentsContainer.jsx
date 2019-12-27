import React from 'react';
import {connect} from 'react-redux';
import {
    addNewProductCommentActionCreator, addNewProductCommentOnCommentsPageActionCreator,
    updateProductCommentBodyActionCreate, updateProductCommentBodyOnCommentsPageActionCreate
} from "../../redux/reducers/commentsPageReducer";
import Comments from "../Comments/comments";

let mapStateToProps = (state) => {
    return {
        products: state.commentsPage.products,
        comments: state.commentsPage.comments,
        newBody: state.commentsPage.newBody
    }
};

let mapDispatchToProps = (dispatch) => {
    return {
        addNewProductComment: () => {
            dispatch(addNewProductCommentOnCommentsPageActionCreator())
        },
        updateProductCommentBody: (body) => {
            dispatch(updateProductCommentBodyOnCommentsPageActionCreate(body));
        }
    }
};

let CommentsContainer = connect(mapStateToProps, mapDispatchToProps)(Comments);

export default CommentsContainer;