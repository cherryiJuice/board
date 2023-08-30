package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm() {

        return "boardwrite";
    }
    @PostMapping("/board/writePro")
    public String boardWritePro(Board board, Model model) {
        boardService.write(board);

        model.addAttribute("message", "글 작성이 완료되었습니다");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {
        List<Board> list = boardService.boardList();
        model.addAttribute("list", list);

        return "boardlist";
    }

    @GetMapping("/board/view")
    public String boardview(Model model, Integer id){
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,
                              Model model) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id,
                              Board board) {
//        Board boardTmp = boardService.boardView(id);
//        boardTmp.setTitle(board.getTitle());
//        boardTmp.setContent(board.getContent());

        boardService.write(board);

        return "redirect:/board/list";
    }
}
