package com.ideabank.web.idea.controller;

import com.ideabank.web.idea.dto.IdeaSaveRequestDto;
import com.ideabank.web.idea.dto.IdeaUpdateRequestDto;
import com.ideabank.web.idea.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class IdeaApiController {

  private final IdeaService ideaService;

  /**
   * アイデアを登録する。
   *
   * @param requestDto アイデア情報
   * @return アイデアID
   */
  @PostMapping("/api/v1/idea")
  public Long save(@RequestBody IdeaSaveRequestDto requestDto) {
    return ideaService.save(requestDto);
  }

  /**
   * アイデアを更新する。
   * @param id アイデアID
   * @param requestDto アイデア詳細情報
   * @return アイデアID
   */
  @PutMapping("/api/v1/idea/{id}")
  public Long update(@PathVariable Long id, @RequestBody IdeaUpdateRequestDto requestDto) {
    return ideaService.update(id, requestDto);
  }
}
