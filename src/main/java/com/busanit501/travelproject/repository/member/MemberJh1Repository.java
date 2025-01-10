package com.busanit501.travelproject.repository.member;

import com.busanit501.travelproject.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberJh1Repository extends JpaRepository<Member, Long> {

  @Query("select m as member1 from Member m where m.memberID like concat('%', :searchQuery, '%') ")
  Page<Member> idOf(Pageable pageable, String searchQuery);

  @Query("select m as member1 from Member m where m.memberName like concat('%', :searchQuery, '%') ")
  Page<Member> nameOf(Pageable pageable, String searchQuery);

  @Query("select m as member1 from Member m where m.memberEmail like concat('%', :searchQuery, '%') ")
  Page<Member> emailOf(Pageable pageable, String searchQuery);

  @Query("select m as member1 from Member m where m.memberPhone like concat('%', :searchQuery, '%') ")
  Page<Member> phoneOf(Pageable pageable, String searchQuery);

}

