const ADD_PRODUCT_COMMENT_ON_COMMENTS_PAGE = 'ADD-PRODUCT-COMMENT-ON-COMMENTS-PAGE';
const UPDATE_PRODUCT_COMMENT_BODY_ON_COMMENTS_PAGE = 'UPDATE-PRODUCT-COMMENT-BODY-ON-COMMENTS-PAGE';

let initialState = {
    products: [
        {id: 1, name: 'Product 1'},
        {id: 2, name: 'Product 2'},
        {id: 3, name: 'Product 3'},
        {id: 4, name: 'Product 4'},
        {id: 5, name: 'Product 5'}
    ],
    comments: [
        {id: 1, body: 'Comment 10'},
        {id: 2, body: 'Comment 11'},
        {id: 3, body: 'Comment 12'},
        {id: 4, body: 'Comment 13'},
        {id: 5, body: 'Comment 14'}
    ],
    newBody: ''
};

const commentsPageReducer = (state = initialState, action) => {
    switch (action.type) {
        case ADD_PRODUCT_COMMENT_ON_COMMENTS_PAGE: {
            let newProductCommentId = state.comments.length;
            let newProductCommentItem = {
                id: ++newProductCommentId,
                body: state.newBody,
            };
            return {
                ...state,
                newBody: '',
                comments: [...state.comments, newProductCommentItem]
            };
        }
        case UPDATE_PRODUCT_COMMENT_BODY_ON_COMMENTS_PAGE: {
            return {
                ...state,
                newBody: action.body
            };
        }
        default:
            return state;
    }
};

export const addNewProductCommentOnCommentsPageActionCreator = () => {
    return {
        type: ADD_PRODUCT_COMMENT_ON_COMMENTS_PAGE
    };
};

export const updateProductCommentBodyOnCommentsPageActionCreate = (body) => {
    return {
        type: UPDATE_PRODUCT_COMMENT_BODY_ON_COMMENTS_PAGE,
        body: body
    }
};

export default commentsPageReducer;