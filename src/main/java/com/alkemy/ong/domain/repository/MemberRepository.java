package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findAll(Pageable page);
}
