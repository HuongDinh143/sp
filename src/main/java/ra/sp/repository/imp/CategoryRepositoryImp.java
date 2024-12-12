package ra.sp.repository.imp;

import org.springframework.stereotype.Repository;
import ra.sp.model.entity.Category;
import ra.sp.repository.ICategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CategoryRepositoryImp implements ICategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("from Category", Category.class).getResultList();
    }

    @Override
    public Category findById(int catId) {
        return entityManager.find(Category.class, catId);
    }

    @Override
    public boolean save(Category category) {
        try {
            entityManager.persist(category);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        try {
            entityManager.merge(category);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int catId) {
        try {
            Category category = findById(catId);
            entityManager.remove(category);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category findByName(String catName) {
        try {
            return entityManager.createQuery("from Category where catName=:name", Category.class)
                    .setParameter("name", catName).getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }
}
