package com.greenart.jpa_test;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.jpa_test.entity.MemberInfoVO;
import com.greenart.jpa_test.repository.MemberRepository;

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
		MemberInfoVO data = repo.findById("userid1");
		System.out.println(data);
		// List<MemberInfoVO> list = repo.findAll();
		// for(MemberInfoVO m : list) {
		// 	System.out.println(m);
		// }
	}
}
