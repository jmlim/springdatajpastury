package io.jmlim.springdatajpastudy;

import javax.persistence.Embeddable;

/**
 * 이 예제에선 Account 에 속한 데이터중에 하나로 취급한다.
 * 그러므로 Entity 라고 보기 어렵다.
 * */
@Embeddable
public class Address {
    private String street;

    private String city;

    private String state;

    private String zipCode;
}
