import { ReactHttpClient } from "@salilvnair/react-httpclient";

class CategoryService {
  constructor() {
    this.categoryService = new ReactHttpClient();
  }

  getCategories(url) {
    return this.categoryService.get(url);
  }
}

// eslint-disable-next-line import/prefer-default-export
export const categoryService = new CategoryService();
