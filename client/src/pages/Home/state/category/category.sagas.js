import { takeLatest, call, put, all } from 'redux-saga/effects';
import { fetchCategorySuccess, fetchCategoryFailure } from './category.actions';
import { categoryService } from '../../services/category-service';
import CategoryActionTypes from './category.types';

export function* fetchCategoryItemsAsync() {
  try {
    const res = yield categoryService.getCategories('/api/v1/categories').toPromise();
    yield put(fetchCategorySuccess(res.data));
  } catch (error) {
    yield put(fetchCategoryFailure(error));
  }
}

export function* fetchCategoryItemsStart() {
  yield takeLatest(
    CategoryActionTypes.FETCH_CATEGORY_START,
    fetchCategoryItemsAsync
  );
}

export function* categorySagas() {
  yield all([call(fetchCategoryItemsStart)]);
}
