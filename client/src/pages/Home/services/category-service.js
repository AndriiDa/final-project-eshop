import { ReactHttpClient } from '@salilvnair/react-httpclient';

class CategoryService {
  constructor() {
    this.categoryService = new ReactHttpClient();
  }

  getCategories(url) {
    return this.categoryService.get(url);
  }
}

export const categoryService = new CategoryService();
