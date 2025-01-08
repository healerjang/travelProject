package com.busanit501.travelproject.repository.member;

import com.busanit501.travelproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMemberNo(long memberNo);
    boolean existsByMemberID(String id);
    boolean existsByMemberName(String name);
    boolean existsByMemberPhone(String phone);
    boolean existsByMemberEmail(String email);
    Member findByMemberIDAndMemberPassword(String memberID, String memberPassword);
    Member findByMemberNoAndMemberUUID(Long memberNo, String memberUUID);
    Member findByMemberNo(Long memberNo);
    Member findByMemberName(String memberName);
}
