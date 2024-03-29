package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/*
동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; // sequence는 0, 1, 2 등 key 값을 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    } // member의 Id를 셋팅을 하고 store에 저장

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // ofNullable은 Null이 반환될 가능성이 있을 때 사용
    } // store에서 가져옴

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() 
                .filter(member -> member.getName().equals(name)) // getName으로 받아온 name이 파라미터로 넘어온 name과 같은지 비교
                .findAny(); // name이 같고 찾으면 반환, 만약 없으면 Null 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 있는 value들을 반환
    }

    public void clearStore() {
        store.clear(); // store을 비워줌
    }
}
