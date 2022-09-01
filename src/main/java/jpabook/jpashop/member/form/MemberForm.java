package jpabook.jpashop.member.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "거주 중인 도시를 입력해주세요.")
    private String city;
    @NotEmpty(message = "거주 중인 도로명을 입력해주세요.")
    private String street;
    @NotEmpty(message = "거주 중인 우편번호를 입력해주세요.")
    private String zipcode;
}
