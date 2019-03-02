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

        // 묶음..
        account.addStudy(study);

        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);
        //entityManager.persist(account);
    }
}
