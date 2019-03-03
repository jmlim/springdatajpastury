package io.jmlim.springdatajpastudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/*
 스프링 러너가 필요.
 데이터 Access Layer 만 테스트 하기 위한 @DataJpaTest를 가져옴. 다른빈들은 등록이 안되고 Repository 빈들만 등록됨.
    - 테스트를 실행할 때 좀 더 빠를 수 있다.

 이 테스트에선 h2 메모리 디비 사용한다. 마리아 디비에 영향 주지 않음.
*/
@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    /**
     * 기본적으로 스프링에서 테스트는 끝나면 롤백을 함.
     * 하이버네이트는 롤백을 어차피 할 것이라 판단되어 insert 쿼리를 실행하지 않음.
     * 그래서 아이디만 반환시켜서 테스트가 성공됨..
     * <p>
     * 그렇기에 Rollback 옵션을 false 줘서 insert 쿼리까지 확인.
     */
    @Test
    @Rollback(false)
    public void crudRepository() {
        // Given
        Post post = new Post();
        post.setTitle("Hello spring boot common");
        //아직 Transient 상태이므로 null 이 나와야 함.
        assertThat(post.getId()).isNull();

        // When
        Post newPost = postRepository.save(post);

        // Then
        //assertj에 있는 assertThat 임.
        assertThat(newPost.getId()).isNotNull();

        // When
        List<Post> posts = postRepository.findAll();

        // Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        //When
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
        //전체 카운트 갯수
        assertThat(page.getTotalElements()).isEqualTo(1);
        //현재 페이지 넘버
        assertThat(page.getNumber()).isEqualTo(0);
        //요청했던 페이징 사이즈
        assertThat(page.getSize()).isEqualTo(10);
        //현재 페이지에 들어오는 갯수
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        // When
        page = postRepository.findByTitleContains("spring", PageRequest.of(0, 10));
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        //When
        long spring = postRepository.countByTitleContains("spring");
        //Then
        assertThat(spring).isEqualTo(1);

    }
}