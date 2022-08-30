package jpabook.jpashop.member;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository

public class MemberRepository{
    // save, findOne, findAll, findByName
    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {  // 리포지터리에서는 딱 저장하는 기능만 수행.
        em.persist(member);
    }

    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);

    }
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }




}
