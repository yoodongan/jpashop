package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem extends BaseEntity{
    @Id @GeneratedValue
    private Long id;

    @Column(name ="ITEM_ID")
    private Long item_id;
    @Column(name ="ORDER_ID")
    private Long order_id;

    private int orderPrice;
    private int count;

}
