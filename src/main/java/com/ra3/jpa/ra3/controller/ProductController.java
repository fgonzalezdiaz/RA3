package com.ra3.jpa.ra3.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ra3.jpa.ra3.dto.ErrorDto;
import com.ra3.jpa.ra3.dto.ProductRequestDto;
import com.ra3.jpa.ra3.dto.ProductResponseDto;
import com.ra3.jpa.ra3.model.Condition;
import com.ra3.jpa.ra3.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    //Helpers de resposta d'error 
    private ResponseEntity<ErrorDto> notFound(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorDto(404, "Producte amb id " + id + " no trobat"));
    }

    private ResponseEntity<ErrorDto> serverError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorDto(500, e.getMessage()));
    }


    @Autowired
    private ProductService productService;

    //Endpoints 
    @PostMapping("/csv")
    public ResponseEntity<?> loadFromCsv(@RequestParam("file") MultipartFile file) {
        try {
            int count = productService.loadFromCsv(file);
            return ResponseEntity.ok().body("Cargados " + count);
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<ProductResponseDto> product = productService.findById(id);
        return product.isPresent() ? ResponseEntity.ok(product.get()) : notFound(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto dto) throws Exception{
        return ResponseEntity.ok().body(productService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto dto) {
        try {
            Optional<ProductResponseDto> updated = productService.update(id, dto);
            return updated.isPresent() ? ResponseEntity.ok(updated.get()) : notFound(id);
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @PatchMapping("/update/{id}/stock")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestParam Integer stock) {
        try {
            Optional<ProductResponseDto> updated = productService.updateStock(id, stock);
            return updated.isPresent() ? ResponseEntity.ok(updated.get()) : notFound(id);
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @PatchMapping("/update/{id}/price")
    public ResponseEntity<?> updatePrice(@PathVariable Long id, @RequestParam BigDecimal price) {
        try {
            Optional<ProductResponseDto> updated = productService.updatePrice(id, price);
            return updated.isPresent() ? ResponseEntity.ok(updated.get()) : notFound(id);
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            return productService.deleteById(id)
                ? ResponseEntity.ok("Producte eliminat correctament")
                : notFound(id);
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @PatchMapping("/delete/{id}/soft")
    public ResponseEntity<?> softDeleteProduct(@PathVariable Long id) {
        try {
            Optional<ProductResponseDto> updated = productService.softDelete(id);
            return updated.isPresent() ? ResponseEntity.ok(updated.get()) : notFound(id);
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping("/search/nom")
    public ResponseEntity<?> searchByName(@RequestParam String prefix) {
        try {
            return ResponseEntity.ok(productService.findByNamePrefix(prefix));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping("/search/order")
    public ResponseEntity<?> searchByOrder(@RequestParam String camp, @RequestParam String order) {
        try {
            return ResponseEntity.ok(productService.findByOrder(camp, order));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping("/search/condition")
    public ResponseEntity<?> searchByCondition(@RequestParam Condition condition) {
        try {
            return ResponseEntity.ok(productService.findByCondition(condition));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping("/search/orderRating")
    public ResponseEntity<?> searchByOrderRating(@RequestParam String order) {
        try {
            return ResponseEntity.ok(productService.findByOrder("rating", order));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping("/search/price-range")
    public ResponseEntity<?> findByPriceRange(@RequestParam BigDecimal priceMin, @RequestParam BigDecimal priceMax) {
        try {
            return ResponseEntity.ok(productService.findByPriceBetween(priceMin, priceMax));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping("/query/best-value")
    public ResponseEntity<?> findTop5BestValue() {
        try {
            return ResponseEntity.ok(productService.findTop5BestValue());
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping("/query/rating-range")
    public ResponseEntity<?> findByRatingRange(@RequestParam BigDecimal ratingMin, @RequestParam BigDecimal ratingMax) {
        try {
            return ResponseEntity.ok(productService.findByRatingBetween(ratingMin, ratingMax));
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping("/query/top-nous")
    public ResponseEntity<?> findTop10NouBestRating() {
        try {
            return ResponseEntity.ok(productService.findTop10NouBestRating());
        } catch (Exception e) {
            return serverError(e);
        }
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getPaginated(@PathVariable int page) {
        try {
            Page<ProductResponseDto> result = productService.findPaginated(page);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return serverError(e);
        }
    }
}
