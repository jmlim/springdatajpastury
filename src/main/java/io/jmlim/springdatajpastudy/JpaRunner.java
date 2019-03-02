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
        /***
         * 어떤 게시글이 있고 게시글을 저장하거나 삭제할 때
         * 포함되어있는 코멘트도 같이 저장해야 하고 같이 삭제해야 한다고 가정함.
         */
        /*Post post = new Post();
        post.setTitle("Spring Data JPA 언제 보나...");

        Comment comment = new Comment();
        comment.setComment("빨리 보고 싶어요");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("곧 보여드릴게요.");
        post.addComment(comment1);*/

        /**
         연간 관례의 엔티티를 어떻게 가져올 것이냐... 지금(Eager)? 나중에(Lazy)?
         - @OneToMany의 기본값은 Lazy
         - @ManyToOne의 기본값은 Eager
         * */

        Session session = entityManager.unwrap(Session.class);
        Post post = session.get(Post.class, 4l);
        System.out.println(post.getTitle());

        // 이전엔 n+1 문제가 생겼는데 지금은 업그레이드 되었다..?ㅎㅎ..
        post.getComments().forEach(c -> {
            System.out.println("==============");
            System.out.println(c.getComment());
        });

       /* Comment comment = session.get(Comment.class, 5l);
        System.out.println("==================================");
        System.out.println(comment.getComment());
        System.out.println(comment.getPost().getTitle());*/

        //session.save(post);

        //session.delete(post);
    /*    // Cascade 설정이 없으면 포스트만 저장이 되버림.(코멘트는 저장안됨)
        // 저장할 PERSIST 를 전파하는 옵션 추가. @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
        //  - 코멘트 저장됨.
        session.save(post);*/

    }
}
