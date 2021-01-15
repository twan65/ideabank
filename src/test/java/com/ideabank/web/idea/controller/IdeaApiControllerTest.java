package com.ideabank.web.idea.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideabank.web.domain.idea.Idea;
import com.ideabank.web.domain.idea.IdeaRepository;
import com.ideabank.web.idea.dto.IdeaSaveRequestDto;
import com.ideabank.web.idea.dto.IdeaUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IdeaApiControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Autowired private IdeaRepository ideaRepository;

  @Autowired private WebApplicationContext context;

  private MockMvc mvc;


  @After
  public void tearDown() throws Exception {
    ideaRepository.deleteAll();
  }

  @Test
  @DisplayName("アイデア登録処理を検証する。")
  public void saveIdea() throws Exception {
    // given
    String title = "title";
    String content = "content";

    // リクエストパラメータを作成
    IdeaSaveRequestDto requestDto = IdeaSaveRequestDto.builder()
            .title(title)
            .content(content)
            .build();

    // URLを作成
    String url = "http://localhost:" + port + "/api/v1/idea";

    // when
    // RestTemplateを利用してAPIを呼び出す。（URL、パラメータ、戻り値の型）
    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

    // then
    // 検証
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    // データを取得して検証を行う。
    List<Idea> postsList = ideaRepository.findAll();

    assertThat(postsList.get(0).getTitle()).isEqualTo(title);
    assertThat(postsList.get(0).getContent()).isEqualTo(content);
  }

  @Test
  @DisplayName("アイデア編集処理を検証する。")
  public void updateIdea() throws Exception {
    // アイデア登録
    Idea saveIdea = ideaRepository.save(Idea.builder()
            .title("title")
            .content("content")
            .author("testUser")
            .build());

    Long updateId = saveIdea.getId();
    String updateTitle = "title2";
    String updateContent = "content2";

    // リクエストパラメータを作成
    IdeaUpdateRequestDto requestDto = IdeaUpdateRequestDto.builder()
            .title(updateTitle)
            .content(updateContent)
            .build();

    // URLを作成
    String url = "http://localhost:" + port + "/api/v1/idea/" + updateId;

    HttpEntity<IdeaUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

    // RestTemplateを利用してAPIを呼び出す。（URL、HTTPメソッド、パラメータ、戻り値の型）
    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

    // 検証
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    // データを取得して検証を行う。
    List<Idea> postsList = ideaRepository.findAll();

    assertThat(postsList.get(0).getTitle()).isEqualTo(updateTitle);
    assertThat(postsList.get(0).getContent()).isEqualTo(updateContent);
  }

  @Test
  @DisplayName("アイデアの論理削除処理を検証する。")
  public void logicalDeleteIdea() throws Exception {
    // アイデア登録
    Idea saveIdea = ideaRepository.save(Idea.builder()
            .title("title")
            .content("content")
            .author("testUser")
            .build());

    Long logicalDeleteId = saveIdea.getId();

    // URLを作成
    String url = "http://localhost:" + port + "/api/v1/idea/" + logicalDeleteId;

    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, Long.class);

    // 検証
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    // データを取得して検証を行う。
    List<Idea> postsList = ideaRepository.findAll();

    assertThat(postsList.size()).isEqualTo(0);
    assertThat(postsList.size()).isEqualTo(0);
  }
}
