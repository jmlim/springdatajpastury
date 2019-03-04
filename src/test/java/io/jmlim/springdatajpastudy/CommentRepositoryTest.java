package io.jmlim.springdatajpastudy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

       /* List<Comment> comments = commentRepository.findAll();
        assertThat(comments).isEmpty();*/

        /**
         * 파라미터에 널이면 안된다고 명시를 해놨으므로 에러가 발생.
         * */
        //commentRepository.save(null);

        this.createComment(100, "spring data jpa");
        this.createComment(55, "HIBERNATE SPRING");


        // List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("Spring", 10);
        // assertThat(comments.size()).isEqualTo(2);

        List<Comment> comments2 = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountDesc("Spring");
        assertThat(comments2.size()).isEqualTo(2);
        assertThat(comments2).first().hasFieldOrPropertyWithValue("likeCount", 100);


        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "LikeCount"));
        /*Page<Comment> pComments = commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest);
        //현재페이지의 카운트
        assertThat(pComments.getNumberOfElements()).isEqualTo(2);
        assertThat(pComments).first().hasFieldOrPropertyWithValue("likeCount", 100);*/

        try(Stream<Comment> sComments = commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest)) {
            Comment firstComment = sComments.findFirst().get();
            assertThat(firstComment.getLikeCount()).isEqualTo(100);
        }
    }

    public void createComment(int likeCount, String comment) {
        Comment newComment = new Comment();
        newComment.setLikeCount(likeCount);
        newComment.setComment(comment);
        commentRepository.save(newComment);
    }
}