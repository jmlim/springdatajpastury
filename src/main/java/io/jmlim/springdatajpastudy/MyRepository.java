package io.jmlim.springdatajpastudy;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 공통 인터페이스 정의 시 @NoRepositoryBean 사용함.
 */
@NoRepositoryBean
public interface MyRepository<T, Id extends Serializable> extends Repository<T, Id> {
    <E extends T> E save(E entity);

    List<T> findAll();

    long count();
}
