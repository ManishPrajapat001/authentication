package org.manish.authentication.controller;

import org.manish.authentication.Service.ProductService;
import org.manish.authentication.dto.ApiResponse;
import org.manish.authentication.dto.ProductRequestDTO;
import org.manish.authentication.dto.ProductResponseDTO;
import org.manish.authentication.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    fetches all products
    @GetMapping
    public ApiResponse<List<ProductResponseDTO>> getProducts() {
        return new ApiResponse<>(true, "Success", productService.getAllProduct());
    }

    @PostMapping
    public ApiResponse<ProductResponseDTO> createProduct(ProductRequestDTO productRequestDTO) {

        return new ApiResponse<>(true, "Product created successfully", productService.createProduct(productRequestDTO));
    }

    @GetMapping("/{product_id}")
    public ApiResponse<ProductResponseDTO> getProductById(Long product_id) {
        return new ApiResponse<>(true, "Success", productService.getProductById(product_id));
    }

}
