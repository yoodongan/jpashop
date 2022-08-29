package jpabook.jpashop.member;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    // findOne, findAll, findByName 3개 개발.

    private final MemberRepository memberRepository;

    public void join(Member member) {
        validateDuplicatedMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicatedMember(Member member) {
        Optional<Member> findMember = memberRepository.findById(member.getId());
        if (!(findMember.isEmpty())) {  // isPresent 써도 되고, isEmpty 로 검증해도 상관 없다.
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    public Optional<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }



}
