package com.ideabank.web;

import com.ideabank.web.idea.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

  private final IdeaService ideaService;

  /**
   * TOP画面を返却する。（前アイデアを取得）
   *
   * @param model
   * @return TOP画面
   */
  @GetMapping("/")
  public String index(Model model) {

    model.addAttribute("ideas", ideaService.findAll());

    return "index";
  }

  /**
   * アイデア登録画面を返却する。
   *
   * @return アイデア登録画面
   */
  @GetMapping("/idea/save")
  public String ideaSave() {
    return "idea/save";
  }

  /**
   * アイデア詳細を１件取得して詳細画面を返却する。
   * @param id アイデアID
   * @param model
   * @return アイデア画面
   */
  @GetMapping("/idea/{id}")
  public String ideaDetail(@PathVariable Long id, Model model) {

    model.addAttribute("idea", ideaService.findById(id));

    return "idea/detail";
  }
}
