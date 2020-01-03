import homePageReducer from "../store/reducers/homePage.reducer";
import productsPageReducer from "../store/reducers/productsPage.reducer";
import sidebarReducer from "../store/reducers/sidebar.reducer";

let store = {
    _state: {
        productsPage: {
            products: [
                {id: 1, name: 'Product 1'},
                {id: 2, name: 'Product 2'},
                {id: 3, name: 'Product 3'},
                {id: 4, name: 'Product 4'},
                {id: 5, name: 'Product 5'}
            ],
            comments: [
                {id: 1, body: 'Comment 1'},
                {id: 2, body: 'Comment 2'},
                {id: 3, body: 'Comment 3'},
                {id: 4, body: 'Comment 4'},
                {id: 5, body: 'Comment 5'}
            ],
            newBody: ''
        },
        homePage: {
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
        },
        sidebar: {}

    },
    _callSubscriber() {
        console.log("state changed");
    },
    getState() {
        return this._state;

    },
    subscribe(observer) {
        this._callSubscriber = observer;
    },
    dispatch(action) {
        this._state.homePage = homePageReducer(this._state.homePage, action);
        this._state.productsPage = productsPageReducer(this._state.productsPage, action);
        this._state.sidebar = sidebarReducer(this._state.sidebar, action);

        this._callSubscriber(this._state);
    }
};

export default store;
window.store = store;

