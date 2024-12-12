package ra.sp.repository;

import ra.sp.model.entity.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> findAll();
    Category findById(int catId);
    boolean save(Category category);
    boolean update(Category category);
    boolean delete(int catId);
    Category findByName(String catName);
}
