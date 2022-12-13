package com.greenart.jpa_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.jpa_test.product.entity.ProductInfoVO;
import com.greenart.jpa_test.member.entity.MemberInfoVO;
import com.greenart.jpa_test.member.repository.MemberRepository;
import com.greenart.jpa_test.product.repository.ProductRepository;

@SpringBootTest
class JpaTestApplicationTests {
	@Autowired MemberRepository repo;
	@Test
	@Transactional // 아래 코드에서 수행한 insert를 수행이전으로 되돌린다.
	public void testMemberAdd() {
		MemberInfoVO data = new MemberInfoVO();
		data.setId("userid");
		data.setPwd("1234");
		data.setName("사용자");
		data.setNickname("닉네임");
		data.setRegDt(new Date());
		data.setStatus(1);
		repo.save(data);
	}
	@Test
	public void testSelectMember() {
		// MemberInfoVO data = repo.findById("userid1");
		// System.out.println(data);
		// List<MemberInfoVO> list = repo.findAll();
		// for(MemberInfoVO m : list) {
		// 	System.out.println(m);
		// }
	}

	@Test
	public void idDupChkTest() {
		Long cnt = repo.countById("myuser001");
		// cnt와 1이 같으면 통과
		assertEquals(cnt, 1);
	}
	@Autowired
	ProductRepository prod_repo;
	@Test
	public void addProduct() {
		ProductInfoVO p = new ProductInfoVO();
		p.setName("탄탄면");
		p.setPrice(12500);
		p.setDiscount(0.0);
		p.setRegDt(new Date());
		p.setStatus(1);
		p.setStock(30);
		prod_repo.save(p);
	}
}
