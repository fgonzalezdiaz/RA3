package com.ra3.jpa.ra3.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ra3.jpa.ra3.model.Condition;
import com.ra3.jpa.ra3.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStatusTrueAndNameStartingWith(String prefix);

    List<Product> findByStatusTrueOrderByPriceAsc();
    List<Product> findByStatusTrueOrderByPriceDesc();

    List<Product> findByStatusTrueAndCondition(Condition condition);

    List<Product> findByStatusTrueOrderByRatingAsc();
    List<Product> findByStatusTrueOrderByRatingDesc();

    Page<Product> findByStatusTrue(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = true AND p.price BETWEEN :priceMin AND :priceMax ORDER BY p.price ASC")
    List<Product> findByPriceBetween(@Param("priceMin") BigDecimal priceMin, @Param("priceMax") BigDecimal priceMax);

    @Query("SELECT p FROM Product p WHERE p.status = true AND p.rating IS NOT NULL AND p.rating > 0 ORDER BY p.price / p.rating ASC")
    List<Product> findTop5BestValue(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = true AND p.rating BETWEEN :ratingMin AND :ratingMax ORDER BY p.rating DESC")
    List<Product> findByRatingBetween(@Param("ratingMin") BigDecimal ratingMin, @Param("ratingMax") BigDecimal ratingMax);

    @Query("SELECT p FROM Product p WHERE p.status = true AND p.condition = :condition ORDER BY p.rating DESC")
    List<Product> findTopNouBestRating(@Param("condition") Condition condition, Pageable pageable);
}
