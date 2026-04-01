package org.manish.authentication.Service;

import jakarta.persistence.EntityExistsException;
import org.manish.authentication.dto.ProductRequestDTO;
import org.manish.authentication.dto.ProductResponseDTO;
import org.manish.authentication.entity.Product;
import org.manish.authentication.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper ;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductResponseDTO> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .toList();
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = modelMapper.map(productRequestDTO, Product.class);

        product.setStock(productRequestDTO.getStock()!= null ? productRequestDTO.getStock() : 0);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponseDTO.class);
    }

    public ProductResponseDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not found"));
        return modelMapper.map(product, ProductResponseDTO.class);

    }
}
