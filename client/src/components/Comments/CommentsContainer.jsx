import {connect} from 'react-redux';
import {
    addNewProductCommentOnCommentsPageActionCreator,
    updateProductCommentBodyOnCommentsPageActionCreate
} from "../../store/reducers/commentsPage.reducer";
import Comments from "./Comments";

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
