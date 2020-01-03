const ADD_COMMENT = 'ADD-COMMENT';
const UPDATE_NEW_COMMENT_TEXT = 'UPDATE-NEW-COMMENT-TEXT';

let initialState = {
    comments: [
        {
            id: 1, message: 'HomePage Comment 1',
            imgUrl: "https://www.thesun.co.uk/wp-content/uploads/2019/11/NINTCHDBPICT000537749349-e1573652120106.jpg",
            likeCount: 10, dislikeCount: 3
        },
        {
            id: 2, message: 'HomePage Comment 2',
            imgUrl: "https://www.thesun.co.uk/wp-content/uploads/2019/11/NINTCHDBPICT000537749349-e1573652120106.jpg",
            likeCount: 22, dislikeCount: 1
        },
        {
            id: 3, message: 'HomePageC omment 3',
            imgUrl: "https://www.thesun.co.uk/wp-content/uploads/2019/11/NINTCHDBPICT000537749349-e1573652120106.jpg",
            likeCount: 33, dislikeCount: 11
        },
        {
            id: 4, message: 'HomePage Comment 4',
            imgUrl: "https://www.thesun.co.uk/wp-content/uploads/2019/11/NINTCHDBPICT000537749349-e1573652120106.jpg",
            likeCount: 7, dislikeCount: 2
        },
        {
            id: 5, message: 'HomePage Comment 5',
            imgUrl: "https://www.thesun.co.uk/wp-content/uploads/2019/11/NINTCHDBPICT000537749349-e1573652120106.jpg",
            likeCount: 1, dislikeCount: 0
        }
    ],
    newCommentText: ''
};

const homePageReducer = (state = initialState, action) => {
    switch (action.type) {
        case ADD_COMMENT: {
            let newCommentId = state.comments.length;
            let newCommentItem = {
                id: ++newCommentId,
                message: state.newCommentText,
                imgUrl: "",
                likeCount: 0,
                dislikeCount: 0
            };
            return {
                ...state,
                newCommentText:'',
                comments: [...state.comments, newCommentItem]
            };
        }
        case UPDATE_NEW_COMMENT_TEXT: {
            return {
                ...state,
                newCommentText: action.newCommentText
            };
        }
        default:
            return state;
    }
};

export const addNewCommentActionCreator = () => {
    return {
        type: ADD_COMMENT
    };
};

export const updateCommentTextActionCreator = (text) => {
    return {
        type: UPDATE_NEW_COMMENT_TEXT,
        newCommentText: text
    }
};

export default homePageReducer;