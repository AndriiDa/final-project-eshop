import CategoryActionTypes from './category.types';

export const fetchCategoryStarted = () => ({
  type: CategoryActionTypes.FETCH_CATEGORY_START
});

export const fetchCategorySuccess = data => ({
  type: CategoryActionTypes.FETCH_CATEGORY_SUCCESS,
  payload: data
});

export const fetchCategoryFailure = error => ({
  type: CategoryActionTypes.FETCH_CATEGORY_FAILURE,
  payload: error
});
