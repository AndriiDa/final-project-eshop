package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findByName(String name);

  Page<Category> findCategoriesByParentCategoryNull(Pageable pageable);

  List<Category> findCategoriesByParentCategoryNull();

}
