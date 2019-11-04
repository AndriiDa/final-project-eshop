package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
