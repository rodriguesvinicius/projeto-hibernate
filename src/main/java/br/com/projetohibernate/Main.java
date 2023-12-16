package br.com.projetohibernate;

import br.com.projetohibernate.entity.Photo;
import br.com.projetohibernate.entity.Product;
import br.com.projetohibernate.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

       ProductService productService = new ProductService();

        Product newProduct = new Product();
        newProduct.setName("Playstation 4");
        productService.save(newProduct);

        newProduct.setPhotos(createPhotos(newProduct));
        productService.update(newProduct);

        List<Product> productList = productService.findAll();
        for (Product readProduct : productList) {
            System.out.println(readProduct);
        }

        newProduct.setName("Playstation 5");
        productService.update(newProduct);

        Product findProduct = productService.findById(1L);
        System.out.println("Produto atualizado" + findProduct);

        productService.delete(1L);
    }

    private static List<Photo> createPhotos(Product product) {
        List<Photo> photos = new ArrayList<>();
        Photo photo = new Photo();
        photo.setDescription("photo 1");
        photo.setProduct(product);
        photos.add(photo);
        return photos;
    }
}