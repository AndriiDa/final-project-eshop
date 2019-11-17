import CategoryActionTypes from './category.types';

const INITIAL_STATE = {
  categoryItems: []
};

const categoryReducer = (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case CategoryActionTypes.FETCH_CATEGORY_SUCCESS: {
      return {
        ...state,
        categoryItems: action.payload
      };
    }
    default: {
      return state;
    }
  }
};

export default categoryReducer;
