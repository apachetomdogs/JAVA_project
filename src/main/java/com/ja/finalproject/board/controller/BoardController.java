package com.ja.finalproject.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.ja.finalproject.board.service.BoardService;
import com.ja.finalproject.dto.ArticleDto;
import com.ja.finalproject.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("mainPage")
    public String mainPage(Model model) {

        List<Map<String, Object>> list = boardService.getArticleList();

        model.addAttribute("list", list);

        return "board/mainPage";
    }

    @RequestMapping("writeArticlePage")
    public String writeArticlePage() {

        return "board/writeArticlePage";
    }

    @RequestMapping("writeArticleProcess")
    public String writeArticleProcess(HttpSession session, ArticleDto params) {

        // 로그인 사용자 정보가 필요할 때는 session 활용(특히 id)
        UserDto sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        int userPk = sessionUserInfo.getId();

        params.setUser_id(userPk);

        boardService.registerArticle(params);

        return "redirect:./mainPage";
    }

    @RequestMapping("articleDetailPage")
    public String articleDetailPage(Model model, @RequestParam("id") int id) {

        boardService.increaseReadCount(id);

        Map<String, Object> map = boardService.getArticle(id);

        // html escape - 특수문자, 엔터 -> <br>
        // 나중에 javascript로 넘어가면 수행 안해도 됨. 
        ArticleDto articleDto = (ArticleDto)map.get("articleDto");
        String content = articleDto.getContent();
        content = StringUtils.escapeXml(content); // 특수 문자 처리... th: text
        content = content.replaceAll("\n", "<br>");
        articleDto.setContent(content);

        model.addAttribute("qwer", map);

        return "board/articleDetailPage";
    }

    @RequestMapping("deleteArticleProcess")
    public String deleteArticleProcess(@RequestParam("id") int id) {

        boardService.deleteArticle(id);

        return "redirect:./mainPage";
    }

    @RequestMapping("updateArticlePage")
    public String updateArticlePage(Model model, @RequestParam("id") int id) {

        model.addAttribute("yyyy", boardService.getArticle(id));

        return "board/updateArticlePage";
    }

    @RequestMapping("updateArticleProcess")
    public String updateArticleProcess(ArticleDto params) {

        boardService.updateArticle(params);

        return "redirect:./mainPage";
    }

}
