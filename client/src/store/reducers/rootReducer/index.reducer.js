import {combineReducers} from 'redux';
import authReducer from '../auth.reducer'
import homePageReducer from "../homePage.reducer";
import productsPageReducer from "../productsPage.reducer";
import commentsPageReducer from "../commentsPage.reducer";
import sidebarReducer from "../sidebar.reducer";
import singleProductPageReducer from "../singleProductPage.reducer";
import commonTasksReducer from "../commonTasks.reducer";
import cartPageReducer from "../cartPage.reducer";
import signInReducer from "../signIn.reducer";
import popupReducer from "../popup.reducer";

const rootReducer = combineReducers({
    homePage: homePageReducer,
    productsPage: productsPageReducer,
    commentsPage: commentsPageReducer,
    sidebar: sidebarReducer,
    singleProductPage: singleProductPageReducer,
    commonTasks: commonTasksReducer,
    auth: authReducer,
    cartPage: cartPageReducer,
    signIn: signInReducer,
    popup: popupReducer
});

export default rootReducer

