package com.greenart.jpa_test.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.jpa_test.member.entity.MemberInfoVO;

@Repository
public interface MemberRepository extends JpaRepository<MemberInfoVO, Long>{
    // select count(*) from member_info where mi_id = ""; - 자동생성
    public Long countById(String id);
    // select * from member_infow where mi_id = "" and mi_pwd = ""
    public MemberInfoVO findByIdAndPwd(String id, String pwd);
}
