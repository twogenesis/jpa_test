package com.greenart.jpa_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.jpa_test.entity.MemberInfoVO;

@Repository
public interface MemberRepository extends JpaRepository<MemberInfoVO, Long>{
    MemberInfoVO findById(String id);
}
