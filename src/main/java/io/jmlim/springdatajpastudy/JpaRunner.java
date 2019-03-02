package io.jmlim.springdatajpastudy;


import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    /**
     *
     */
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("jmlim");
        //account.setPassword("jpa");
        account.setPassword("hibernate");

        //주인한테 관계를 설정해야 디비에 반영이된다.
        Study study = new Study();
        study.setName("Spring Data JPA");

        // 이렇게 할 경우 account_studies 테이블에 관계 정보가 들어감.
        account.getStudies().add(study);
        // 양방향 관계일땐 이쪽에도 저장을 해야한다.
        // 여기선 study 쪽이 관계의 주인이됨..
        study.setOwner(account);

        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);
        //entityManager.persist(account);
    }
}
