package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipCode;

    protected Address() {   //  JPA 스펙 상 값 타입에는 반드시 생성자가 필요하다. 가급적 public 이 아닌 protected 로 설정해주는게 좋다.

    }

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }


}
