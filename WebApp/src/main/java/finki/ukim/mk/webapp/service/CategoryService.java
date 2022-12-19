package finki.ukim.mk.webapp.service;

import finki.ukim.mk.webapp.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(String name,String description);
    Category updated(String name,String descrption);
    void delete(String name);
    List<Category> listCategories();
    List<Category> searchCategories(String searchText);
}
