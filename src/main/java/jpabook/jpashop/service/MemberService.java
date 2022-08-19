package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor  // final 붙은 필드 변수만 골라서 생성자를 자동으로 만들어줌.
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입 기능
    @Transactional    // (readOnly = false) 가 default 설정임.
    public void join(Member member) {
        validateDuplicatedMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicatedMember(Member member) {
        List<Member> findMember = memberRepository.findByName(member.getName());
        if(!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    // 전체 회원 조회 기능
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // Id 로 특정 회원 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}

