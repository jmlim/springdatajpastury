package io.jmlim.springdatajpastudy;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 공통 인터페이스 정의 시 @NoRepositoryBean 사용함.
 */
@NoRepositoryBean
public interface MyRepository<T, Id extends Serializable> extends Repository<T, Id> {
    <E extends T> E save(@NonNull E entity);

    List<T> findAll();

    long count();

    /**
     * 리턴되는 값이 Null 이어도 이전처럼 널로 처리하는것이 아닌 이 옵셔널이라는 인터페이스가 제공하는 메소드로 검사를 할 수가 있다.
     */
    // <E extends T> Optional<E> findById(Id id);

    /**
     * 그냥 엔티티 타입을 리턴하게 했을 때 그 값이 없으면?
     */
    @Nullable
    <E extends T> E findById(Id id);
}
