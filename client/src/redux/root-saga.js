import { all, call } from 'redux-saga/effects';
import { categorySagas } from '../pages/Home/state/category/category.sagas';

export default function* rootSaga() {
  yield all([call(categorySagas)]);
}
