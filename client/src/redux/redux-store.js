import {applyMiddleware, combineReducers, createStore} from "redux";
import homePageReducer from "./reducers/homePageReducer";
import productsPageReducer from "./reducers/productsPageReducer";
import singleProductPageReducer from "./reducers/singleProductPageReducer";
import commentsPageReducer from "./reducers/commentsPageReducer";
import sidebarReducer from "./reducers/sidebarReducer";
import commonTasksReducer from "./reducers/commonTasksReducer";
import authReducer from "./reducers/authReducer";
import cartPageReducer from "./reducers/cartPageReducer";
import appReducer from "./reducers/appReducer";
import { reducer as formReducer } from 'redux-form'
import thunkMiddleware from "redux-thunk";

let reducers = combineReducers({
    homePage: homePageReducer,
    productsPage: productsPageReducer,
    commentsPage: commentsPageReducer,
    sidebar: sidebarReducer,
    singleProductPage: singleProductPageReducer,
    commonTasks: commonTasksReducer,
    cartPage: cartPageReducer,
    app: appReducer,
    auth: authReducer,
    form: formReducer

});

let store = createStore(reducers, applyMiddleware(thunkMiddleware));

export default store;
window.store = store;