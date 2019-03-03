package io.jmlim.springdatajpastudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
       /* Comment comment = new Comment();
        comment.setComment("Hello Comment 임정묵");
        commentRepository.save(comment);

        List<Comment> all = commentRepository.findAll();
        assertThat(all.size()).isEqualTo(1);

        long count = commentRepository.count();
        assertThat(count).isEqualTo(1);*/

       /* Optional<Comment> byId = commentRepository.findById(100l);
        assertThat(byId).isEmpty();*/

        /**옵셔널이 제공하는 여러가지 메소드 써보기 */
        /** 단일 값을 받아올땐 옵셔널을 사용하는 것을 추천한다. 아름답게 처리할 수 있다.*/
        // boolean present = byId.isPresent();
        // Comment comment = byId.orElse(new Comment());
        // Comment comment = byId.orElseThrow(() -> new IllegalArgumentException());

        //그냥 엔티티 타입을 리턴하게 했을 때 그 값이 없으면?
        // Comment comment = commentRepository.findById(100l);
        // 최근엔 이런 null 비교를 쓰지 않는 추세..
        /*if(comment == null) {
            throw new IllegalArgumentException();
        }*/

        List<Comment> comments = commentRepository.findAll();
        assertThat(comments).isEmpty();
    }
}