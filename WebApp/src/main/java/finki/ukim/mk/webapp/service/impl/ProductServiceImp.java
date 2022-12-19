package finki.ukim.mk.webapp.service.impl;

import finki.ukim.mk.webapp.model.Category;
import finki.ukim.mk.webapp.model.Exceptions.CategoryNotFoundException;
import finki.ukim.mk.webapp.model.Exceptions.ManufacturerNotFoundException;
import finki.ukim.mk.webapp.model.Manufacturer;
import finki.ukim.mk.webapp.model.Product;
import finki.ukim.mk.webapp.repository.jpa.CategoryRepository;
import finki.ukim.mk.webapp.repository.jpa.ManufacturerRepository;
import finki.ukim.mk.webapp.repository.jpa.ProductRepository;
import finki.ukim.mk.webapp.service.ProductService;
import finki.ukim.mk.webapp.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ShoppingCartService shoppingCartService;

    public ProductServiceImp(ProductRepository productRepository,
                             CategoryRepository categoryRepository,
                             ManufacturerRepository manufacturerRepository, ShoppingCartService shoppingCartService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }



    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity,
                                  Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer= this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));

        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository
                .save(new Product(name,price,quantity,category,manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
     this.productRepository.deleteById(id);

    }
}
