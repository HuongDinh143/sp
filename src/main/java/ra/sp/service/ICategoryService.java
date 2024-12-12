package ra.sp.service;

import ra.sp.model.dto.CategoryDto;
import ra.sp.model.dto.CategoryUpdateDto;
import ra.sp.model.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(int catId);
    boolean create(CategoryDto request);
    boolean update(CategoryUpdateDto request);
    boolean delete(int catId);
    Category findByName(String name);
}
