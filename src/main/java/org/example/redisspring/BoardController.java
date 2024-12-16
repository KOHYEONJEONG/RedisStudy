package org.example.redisspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping()
    public List<Board> getBoards(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return boardService.getBoards(page, size); // 1, 10

        //캐시 생성 로그  : Creating cache entry for key 'boards:page:1:size:10' in cache(s) [getBoards]
        //새로고침 시 (캐시로 조회) : Cache entry for key 'boards:page:1:size:10' found in cache(s) [getBoards]
    }
}
