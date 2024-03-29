package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    } // 각 Test가 끝날 때마다 지워줌
    // Test는 순서에 상관 없이, 의존관계 없이 설계가 되어야 한다. 그래서 하나의 Test가 끝날 때마다 저장소나 공용 데이터를 지워주어야 함.


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals(result, member); // 출력이 되는 것은 없지만 빌드가 됨, member을 null로 수정시 컴파일 되지 않음
        assertThat(member).isEqualTo((result)); // member가 result와 같을 때, 이것을 자주 씀
        // Alt + enter에서 static import로 하면 Assertions.assertThat을 assertThat으로 할 수 있음
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // 위의 내용을 복사한 뒤 Shift + F6을 하면 변수를 변경할 수 있음
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
