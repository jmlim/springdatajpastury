package io.jmlim.springdatajpastudy;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       /*TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post As p", Post.class);
       List<Post> posts = query.getResultList();
       // toString 조심.. comment 를 toString 으로 찍으면 커멘트도 같이 가져온다. (커멘트 가져오는 쿼리도 같이 실행됨.)
       posts.forEach(System.out::println);*/

        //이렇게 크리테리아를 쓰면 타입 세이프한 쿼리를 사용 가능하다.
        /*CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> query = builder.createQuery(Post.class);
        Root<Post> root = query.from(Post.class);
        query.select(root);

        List<Post> posts = entityManager.createQuery(query).getResultList();
        posts.forEach(System.out::println);*/

        //NativeQuery 사용하기.
        List<Post> posts = entityManager.createNativeQuery("Select * from Post", Post.class).getResultList();
        posts.forEach(System.out::println);
    }
}
