import {applyMiddleware, combineReducers, createStore} from "redux";
import homePageReducer from "./reducers/homePageReducer";
import productsPageReducer from "./reducers/productsPageReducer";
import singleProductPageReducer from "./reducers/singleProductPageReducer";
import commentsPageReducer from "./reducers/commentsPageReducer";
import sidebarReducer from "./reducers/sidebarReducer";
import commonTasksReducer from "./reducers/commonTasksReducer";
import authReducer from "./reducers/authReducer";
import cartPageReducer from "./reducers/cartPageReducer";
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