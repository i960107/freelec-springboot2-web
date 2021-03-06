package com.springboot.book.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title="테스트 게시글";
        String content="테스트 본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("i960107@naver.com").build()  );

        List<Posts> postsList=postsRepository.findAll();

        Posts posts= postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록(){
    LocalDateTime now = LocalDateTime.of(2021,5,11,13,49,0);
    postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

    List<Posts> postsList= postsRepository.findAll();
        System.out.println(">>>>>>>createdDate:"+postsList.get(0).getCreatedDate()+",modifiedDate:"+postsList.get(0).getModifiedDate());
    assertThat(postsList.get(0).getCreatedDate()).isAfter(now);
    assertThat(postsList.get(0).getModifiedDate()).isAfter(now);
    }
}
