package ra.sp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.sp.model.dto.CategoryDto;
import ra.sp.model.dto.CategoryUpdateDto;
import ra.sp.model.entity.Category;
import ra.sp.repository.ICategoryRepository;
import ra.sp.service.ICategoryService;

import java.util.List;

@Service
public class CategoryServiceImp implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int catId) {
        return categoryRepository.findById(catId);
    }

    @Override
    public boolean create(CategoryDto request) {
        Category category = new Category();
        category.setCatName(request.getCatName());
        category.setCatDesc(request.getCatDesc());
        return categoryRepository.save(category);
    }

    @Override
    public boolean update(CategoryUpdateDto request) {
        Category category = categoryRepository.findById(request.getCatId());
        category.setCatName(request.getCatName());
        category.setCatDesc(request.getCatDesc());
        category.setCatStatus(request.isCatStatus());
        return categoryRepository.update(category);
    }

    @Override
    public boolean delete(int catId) {
        return categoryRepository.delete(catId);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
