import {combineReducers, createStore} from "redux";
import homePageReducer from "./reducers/homePageReducer";
import productsPageReducer from "./reducers/productsPageReducer";
import singleProductPageReducer from "./reducers/singleProductPageReducer";
import commentsPageReducer from "./reducers/commentsPageReducer";
import sidebarReducer from "./reducers/sidebarReducer";
import commonTasksReducer from "./reducers/commonTasksReducer";

let reducers = combineReducers({
    homePage: homePageReducer,
    productsPage: productsPageReducer,
    commentsPage: commentsPageReducer,
    sidebar: sidebarReducer,
    singleProductPage: singleProductPageReducer,
    commonTasks: commonTasksReducer
});

let store = createStore(reducers);

export default store;
window.store = store;