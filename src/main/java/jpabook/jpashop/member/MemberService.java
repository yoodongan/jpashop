package jpabook.jpashop.member;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    // join, findOne, findAll, findByname(List 로 리턴)

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDupliatedMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    public List<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }

    private void validateDupliatedMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("회원명 중복!");
        }
    }


}
