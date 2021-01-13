package com.ideabank.web;

import com.ideabank.web.idea.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
     * @return
     */
    @GetMapping("/idea/save")
    public String ideaSave() {
        return "idea/save";
    }
}
