import {connect} from 'react-redux';
import {addNewCommentActionCreator, updateCommentTextActionCreator}
    from "../../../store/reducers/homePage.reducer";
import Comments from "./Comments";

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

let CommentsContainer = connect(mapStateToProps, mapDispatchToProps)(Comments);

export default CommentsContainer;
