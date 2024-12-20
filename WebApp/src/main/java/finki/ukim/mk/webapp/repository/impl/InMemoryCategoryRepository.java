package finki.ukim.mk.webapp.repository.impl;

import finki.ukim.mk.webapp.bootstrap.DataHolder;
import finki.ukim.mk.webapp.model.Category;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository {

    public List<Category> findAll(){
        return DataHolder.categories;
    }

    public Category save(Category c){
        if(c==null || c.getName()==null || c.getName().isEmpty()){
            return null;
        }
        DataHolder.categories.removeIf(r -> r.getName().equals(c.getName()));
        DataHolder.categories.add(c);
        return c;
    }

    public Optional<Category> findByName(String name) {
        return DataHolder.categories.stream().filter(r -> r.getName().equals(name)).findFirst();
    }
    public Optional<Category> findById(Long id) {
        return DataHolder.categories.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public List<Category> search(String text){
        return DataHolder.categories.stream().filter(r->r.getName().contains(text)
                ||r.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public void delete(String categoryName){
        if(categoryName==null) return;

        DataHolder.categories.removeIf(r->r.getName().equals(categoryName));
    }
}
