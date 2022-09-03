package jpabook.jpashop.domain;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Table(name = "item")
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /* 비즈니스 로직 시작 */
    // addStock, removeStock(예외 처리까지)
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    public void removeStock(int quantity) {
        int restStock = stockQuantity-quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다!");
        }
        stockQuantity -= quantity;
    }

}
