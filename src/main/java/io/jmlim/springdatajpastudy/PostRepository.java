package io.jmlim.springdatajpastudy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 타이틀을 가지고 있는 포스트 목록을 페이징을 해서 찾겠다..
     * @param title
     * @param pageable
     * @return
     */
    Page<Post> findByTitleContains(String title, Pageable pageable);

    /**
     *
     * @param title
     * @return
     */
    long countByTitleContains(String title);
}
