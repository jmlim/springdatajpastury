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

        // ============== 여기까지가 Transient 상태(JPA 가 모르는 상태)

        // 묶음..
        account.addStudy(study);

        Session session = entityManager.unwrap(Session.class);
        // Save 를 하게되면 Persistent 상태 (JPA 가 관리중인 상태)
        session.save(account);
        session.save(study);
        //entityManager.persist(account);

        // 데이터베이스 가지 않고 캐시에 있는것을 가지고 온다.
        Account jmlim = session.load(Account.class, account.getId());

        // 객체의 변경사항이 있을땐? 반영을 해준다.
        // 실행해보면.. 불필요한 셀렉트를 하지 않는다.
        jmlim.setUsername("Lim Jeong Muk");
        jmlim.setUsername("Lim Jeong Muk 2");
        //원래 이름이랑 같아서 update 쿼리가 일어나지 않음..
        jmlim.setUsername("jmlim");
        
        System.out.println("===============================");
        System.out.println(jmlim.getUsername());
    }
}
