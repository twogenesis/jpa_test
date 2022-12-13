package com.greenart.jpa_test.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.jpa_test.product.entity.ProductInfoVO;

@Repository
public interface ProductRepository extends JpaRepository<ProductInfoVO, Long>{
    public Page<ProductInfoVO> findAll(Pageable pageable);
    public ProductInfoVO findBySeq(Integer seq);
    public void deleteBySeq(Integer seq);
}
