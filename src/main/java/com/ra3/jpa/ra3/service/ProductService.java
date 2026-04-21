package com.ra3.jpa.ra3.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ra3.jpa.ra3.dto.ProductRequestDto;
import com.ra3.jpa.ra3.dto.ProductResponseDto;
import com.ra3.jpa.ra3.mapper.ProductMapper;
import com.ra3.jpa.ra3.model.Condition;
import com.ra3.jpa.ra3.model.Product;
import com.ra3.jpa.ra3.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductMapper productMapper;


    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(productMapper.toResponseDto(p));
        }
        return result;
    }

    public Optional<ProductResponseDto> findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return Optional.of(productMapper.toResponseDto(product.get()));
        }
        return Optional.empty();
    }

    public ProductResponseDto create(ProductRequestDto dto) {
        Product product = productMapper.toEntity(dto);
        Product saved = productRepository.save(product);
        return productMapper.toResponseDto(saved);
    }

    public Optional<ProductResponseDto> update(Long id, ProductRequestDto dto) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product product = existing.get();
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setStock(dto.getStock());
            product.setPrice(dto.getPrice());
            product.setRating(dto.getRating());
            product.setCondition(dto.getCondition());
            return Optional.of(productMapper.toResponseDto(productRepository.save(product)));
        }
        return Optional.empty();
    }

    public Optional<ProductResponseDto> updateStock(Long id, Integer stock) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product product = existing.get();
            product.setStock(stock);
            return Optional.of(productMapper.toResponseDto(productRepository.save(product)));
        }
        return Optional.empty();
    }

    public Optional<ProductResponseDto> updatePrice(Long id, BigDecimal price) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product product = existing.get();
            product.setPrice(price);
            return Optional.of(productMapper.toResponseDto(productRepository.save(product)));
        }
        return Optional.empty();
    }

    public boolean deleteById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ProductResponseDto> softDelete(Long id) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product product = existing.get();
            product.setStatus(false);
            return Optional.of(productMapper.toResponseDto(productRepository.save(product)));
        }
        return Optional.empty();
    }

    public List<ProductResponseDto> findByNamePrefix(String prefix) {
        List<Product> products = productRepository.findByStatusTrueAndNameStartingWith(prefix);
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(productMapper.toResponseDto(p));
        }
        return result;
    }

    public List<ProductResponseDto> findByOrder(String camp, String order) {
        List<Product> products;
        if ("rating".equalsIgnoreCase(camp)) {
            products = "asc".equalsIgnoreCase(order) ? productRepository.findByStatusTrueOrderByRatingAsc() : productRepository.findByStatusTrueOrderByRatingDesc();
        } else {
            products = "asc".equalsIgnoreCase(order) ? productRepository.findByStatusTrueOrderByPriceAsc() : productRepository.findByStatusTrueOrderByPriceDesc();
        }
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(productMapper.toResponseDto(p));
        }
        return result;
    }

    public List<ProductResponseDto> findByCondition(Condition condition) {
        List<Product> products = productRepository.findByStatusTrueAndCondition(condition);
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(productMapper.toResponseDto(p));
        }
        return result;
    }

    public List<ProductResponseDto> findByPriceBetween(BigDecimal priceMin, BigDecimal priceMax) {
        List<Product> products = productRepository.findByPriceBetween(priceMin, priceMax);
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(productMapper.toResponseDto(p));
        }
        return result;
    }

    public List<ProductResponseDto> findTop5BestValue() {
        List<Product> products = productRepository.findTop5BestValue(PageRequest.of(0, 5));
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(productMapper.toResponseDto(p));
        }
        return result;
    }

    public List<ProductResponseDto> findByRatingBetween(BigDecimal ratingMin, BigDecimal ratingMax) {
        List<Product> products = productRepository.findByRatingBetween(ratingMin, ratingMax);
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(productMapper.toResponseDto(p));
        }
        return result;
    }

    public List<ProductResponseDto> findTop10NouBestRating() {
        List<Product> products = productRepository.findTopNouBestRating(Condition.NOU, PageRequest.of(0, 10));
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product p : products) {
            result.add(productMapper.toResponseDto(p));
        }
        return result;
    }

    public Page<ProductResponseDto> findPaginated(int page) {
        Page<Product> productPage = productRepository.findByStatusTrue(PageRequest.of(page, 5));
        return productPage.map(productMapper::toResponseDto);
    }

    @Transactional
    public int loadFromCsv(MultipartFile file) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        int lineNumber = 0;
        int count = 0;

        while ((line = reader.readLine()) != null) {
            lineNumber++;
            if (lineNumber == 1 && line.toLowerCase().startsWith("name")) {
                continue;
            }
            String[] fields = line.split(",");
            if (fields.length < 4) {
                throw new Exception("Error a la línia " + lineNumber + ": format incorrecte. Mínim 4 camps requerits: name,description,stock,price");
            }
            try {
                Product product = new Product();
                product.setName(fields[0].trim());
                product.setDescription(fields[1].trim().isEmpty() ? null : fields[1].trim());
                product.setStock(Integer.parseInt(fields[2].trim()));
                product.setPrice(new BigDecimal(fields[3].trim()));
                if (fields.length > 4 && !fields[4].trim().isEmpty()) {
                    product.setRating(new BigDecimal(fields[4].trim()));
                }
                if (fields.length > 5 && !fields[5].trim().isEmpty()) {
                    product.setCondition(Condition.valueOf(fields[5].trim().toUpperCase()));
                }
                productRepository.save(product);
                count++;
            } catch (Exception e) {
                throw new Exception("Error a la línia " + lineNumber + ": " + e.getMessage());
            }
        }
        return count;
    }
}
