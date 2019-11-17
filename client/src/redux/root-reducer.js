import { combineReducers } from 'redux';
import categoryReducer from '../pages/Home/state/category/category.reducer';

const rootReducer = combineReducers({
  category: categoryReducer
});

export default rootReducer;
