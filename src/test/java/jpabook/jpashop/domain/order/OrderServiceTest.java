package jpabook.jpashop.domain.order;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.exception.NotEnoughStockException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Fail.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @PersistenceContext EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember("회원1");
        Item item = createBook("어린왕자", 10, 10000);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        Order getOrder = orderRepository.findOne(orderId);

        //then
        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문 시 상태는 ORDER");
        Assertions.assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        Assertions.assertEquals(20000, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
        Assertions.assertEquals(8, item.getStockQuantity(), "주문한 수량만큼 재고 감소");

    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Book book = createBook("어린왕자", 10, 10000);
        Member member= createMember("spring");
        int orderCount = 11;  // 재고 수량 초과 시 오류 발생해야 함.
        //when
        orderService.order(member.getId(), book.getId(), orderCount);

        //then
        fail("이쪽으로 넘어오면 안됨. 넘어오기 전에 재고 수량 부족 예외가 발생해야 한다.");

    }
    @Test
    public void 주문취소() {
        //given
        Member member = createMember("spring");
        Item item = createBook("어린왕자", 10, 10000);
        int orderCount = 2;

        Long orderID = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderID);

        //then
        Order getOrder = orderRepository.findOne(orderID);
        assertThat(OrderStatus.CANCEL).isEqualTo(getOrder.getStatus());
        assertThat(10).isEqualTo(item.getStockQuantity());


    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울", "독립문로", "123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int stockQuantity, int price) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }


}