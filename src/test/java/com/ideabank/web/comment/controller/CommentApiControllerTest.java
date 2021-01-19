package com.ideabank.web.comment.controller;

import com.ideabank.web.comment.dto.CommentSaveRequestDto;
import com.ideabank.web.comment.dto.CommentUpdateRequestDto;
import com.ideabank.web.domain.comment.Comment;
import com.ideabank.web.domain.comment.CommentRepository;
import com.ideabank.web.domain.idea.Idea;
import com.ideabank.web.domain.idea.IdeaRepository;
import com.ideabank.web.idea.dto.IdeaSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentApiControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Autowired private IdeaRepository ideaRepository;

  @Autowired private CommentRepository commentRepository;

  @Autowired private WebApplicationContext context;

  private MockMvc mvc;

  @After
  public void tearDown() throws Exception {
    commentRepository.deleteAll();
    ideaRepository.deleteAll();
  }

  @Test
  @DisplayName("コメント登録処理を検証する。")
  public void saveComment() throws Exception {
    // idea given
    String title = "title";
    String content = "content";

    // リクエストパラメータを作成
    IdeaSaveRequestDto ideaRequestDto = IdeaSaveRequestDto.builder()
            .title(title)
            .content(content)
            .build();

    // URLを作成
    String ideaUrl = "http://localhost:" + port + "/api/v1/idea";
    ResponseEntity<Long> ideaResponseEntity = restTemplate.postForEntity(ideaUrl, ideaRequestDto, Long.class);

    // then
    // 検証
    assertThat(ideaResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(ideaResponseEntity.getBody()).isGreaterThan(0L);

    // comment given
    long ideaId = ideaResponseEntity.getBody();
    String comment = "comment";
    String author = "testUser";

    // リクエストパラメータを作成
    CommentSaveRequestDto commentRequestDto = CommentSaveRequestDto.builder()
            .ideaId(ideaId)
            .comment(comment)
            .author(author)
            .build();

    String commentUrl = "http://localhost:" + port + "/api/v1/comment";
    ResponseEntity<Long> commentResponseEntity = restTemplate.postForEntity(commentUrl, commentRequestDto, Long.class);

    // then
    // 検証
    assertThat(commentResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(commentResponseEntity.getBody()).isGreaterThan(0L);

    // データを取得して検証を行う。
    List<Comment> commentList = commentRepository.findAll();

    assertThat(commentList.get(0).getIdeaId()).isEqualTo(ideaId);
    assertThat(commentList.get(0).getComment()).isEqualTo(comment);
    assertThat(commentList.get(0).getAuthor()).isEqualTo(author);
  }

  @Test
  @DisplayName("コメント更新処理を検証する。")
  public void updateComment() throws Exception {
    // アイデア登録
    Idea saveIdea = ideaRepository.save(Idea.builder()
            .title("title")
            .content("content")
            .author("testUser")
            .build());


    // コメント登録
    Comment saveComment = commentRepository.save(Comment.builder()
            .ideaId(saveIdea.getIdeaId())
    .comment("comment")
    .author("testUser")
    .build());


    // given
    Long updateId = saveComment.getCommentId();
    String updateComment = "comment2";

    CommentUpdateRequestDto requestDto = CommentUpdateRequestDto.builder()
            .comment(updateComment)
            .build();

    String url = "http://localhost:" + port + "/api/v1/comment/" + updateId;

    HttpEntity<CommentUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

    // RestTemplateを利用してAPIを呼び出す。（URL、HTTPメソッド、パラメータ、戻り値の型）
    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

    // 検証
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    // データを取得して検証を行う。
    List<Comment> commentList = commentRepository.findAll();

    assertThat(commentList.get(0).getComment()).isEqualTo(updateComment);
  }

  @Test
  @DisplayName("コメントの論理削除処理を検証する。")
  public void logicalDeleteIdea() throws Exception {
    // アイデア登録
    Idea saveIdea = ideaRepository.save(Idea.builder()
            .title("title")
            .content("content")
            .author("testUser")
            .build());


    // コメント登録
    Comment saveComment = commentRepository.save(Comment.builder()
            .ideaId(saveIdea.getIdeaId())
            .comment("comment")
            .author("testUser")
            .build());


    // given
    Long deleteId = saveComment.getCommentId();

    String url = "http://localhost:" + port + "/api/v1/comment/" + deleteId;

    // RestTemplateを利用してAPIを呼び出す。（URL、HTTPメソッド、パラメータ、戻り値の型）
    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, Long.class);

    // 検証
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    // データを取得して検証を行う。
    List<Comment> commentList = commentRepository.findAll();

    assertThat(commentList.size()).isEqualTo(0);
  }
}
