package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장
    Optional<Member> findById(Long id); // Id나 Name이 없으면 Null 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
