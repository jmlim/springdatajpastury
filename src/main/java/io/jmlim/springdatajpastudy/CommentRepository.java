package io.jmlim.springdatajpastudy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Stream;


/**
 * 사용할 기능을 직접 내가 다 정의하고 싶을 때 2가지 방법이 있는데 그중에 하나의 방법.
 * @RepositoryDefinition 을 사용하는 방법임.
 *
 * 이렇게 만들었을 땐 우리가 만든 기능이므로 테스트를 꼼꼼하게 다 해야함.
 */
/*
@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository {
    Comment save(Comment comment);

    List<Comment> findAll();
}
*/

/**
 * MyRepository 에 정의한 기능만 가지고 옴.
 * (레포지트리에서 필요한 메소드만 복사해서 MyRepository  에 정의해서도 사용 가능함.)
 */
public interface CommentRepository extends MyRepository<Comment, Long> {

    /**
     * @param keyword
     * @return
     */
    //@Query("SELECT c FROM Comment AS c")
    //List<Comment> findByCommentContains(String keyword);

    //Page<Comment> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Pageable pageable);

    List<Comment> findByCommentContainsIgnoreCase(String keyword);

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    List<Comment> findByCommentContainsIgnoreCaseOrderByLikeCountDesc(String keyword);

    // Page<Comment> findByCommentContainsIgnoreCase(String spring, Pageable pageable);
    Stream<Comment> findByCommentContainsIgnoreCase(String spring, Pageable pageable);
}