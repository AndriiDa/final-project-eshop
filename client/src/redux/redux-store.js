import {applyMiddleware, combineReducers, createStore} from "redux";
import homePageReducer from "../store/reducers/homePage.reducer";
import productsPageReducer from "../store/reducers/productsPage.reducer";
import singleProductPageReducer from "../store/reducers/singleProductPage.reducer";
import commentsPageReducer from "../store/reducers/commentsPage.reducer";
import sidebarReducer from "../store/reducers/sidebar.reducer";
import commonTasksReducer from "../store/reducers/commonTasks.reducer";
import authReducer from "../store/reducers/auth.reducer";
import cartPageReducer from "../store/reducers/cartPage.reducer";
import thunkMiddleware from "redux-thunk";

let reducers = combineReducers({
    homePage: homePageReducer,
    productsPage: productsPageReducer,
    commentsPage: commentsPageReducer,
    sidebar: sidebarReducer,
    singleProductPage: singleProductPageReducer,
    commonTasks: commonTasksReducer,
    auth: authReducer,
    cartPage: cartPageReducer,

});

let store = createStore(reducers, applyMiddleware(thunkMiddleware));

export default store;
window.store = store;
