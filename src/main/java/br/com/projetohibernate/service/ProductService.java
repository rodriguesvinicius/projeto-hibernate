package br.com.projetohibernate.service;

import br.com.projetohibernate.dao.ProductDao;
import br.com.projetohibernate.entity.Product;

import java.util.List;

public class ProductService {

    private final ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDao();
    }

    public void save(Product product) {
        this.productDao.save(product);
    }

    public void update(Product product) {
        this.productDao.update(product);
    }

    public Product findById(Long id) {
        return this.productDao.findById(id);
    }

    public List<Product> findAll() {
        return this.productDao.findAll();
    }

    public void delete(Long id) {
        this.productDao.delete(id);
    }
}
