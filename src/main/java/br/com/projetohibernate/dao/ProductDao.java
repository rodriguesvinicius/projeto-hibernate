package br.com.projetohibernate.dao;

import br.com.projetohibernate.entity.Product;

public class ProductDao extends GenericDao<Product, Long> {
    public ProductDao() {
        super(Product.class);
    }
}
