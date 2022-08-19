package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    private List<Item> itemList = new ArrayList<>();

    @ManyToOne
    private Category parent;

    @OneToMany
    private List<Category> child = new ArrayList<>();
}
