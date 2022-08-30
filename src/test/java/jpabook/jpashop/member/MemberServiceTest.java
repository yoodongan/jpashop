package jpabook.jpashop.member;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Fail.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void join() {
        //given
        Member member = new Member();
        member.setName("spring");
        Member member2 = new Member();
        member2.setName("summer");

        //when
        memberService.join(member);
        memberService.join(member2);
        //then
        Member findMember1 = memberRepository.findOne(member.getId());
        Member findMember2 = memberRepository.findOne(member2.getId());
        Assertions.assertThat(member).isEqualTo(findMember1);
        Assertions.assertThat(member2).isEqualTo(findMember2);

    }
    /* 중복회원예외 테스트 */
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다. 여기까지 오면 안됨.");

    }
}