package finki.ukim.mk.webapp.service.impl;

import finki.ukim.mk.webapp.model.Category;
import finki.ukim.mk.webapp.repository.impl.InMemoryCategoryRepository;
import finki.ukim.mk.webapp.repository.jpa.CategoryRepository;
import finki.ukim.mk.webapp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category create(String name, String description) {
        if(name==null||name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c= new Category(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Category updated(String name, String description){
        if(name==null||name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c= new Category(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if(name==null||name.isEmpty()){
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}
