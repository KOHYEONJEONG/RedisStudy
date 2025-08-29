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

    /*
    * 여러 게시글 조회
    * ㄴ 페이지네이션을 위해 page, size 파라미타로 받음
    * */
    @GetMapping()
    public List<Board> getBoards(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size",defaultValue = "10") int size) {
        return boardService.getBoards(page, size); // 1, 10

        //캐시 생성 로그  : Creating cache entry for key 'boards:page:1:size:10' in cache(s) [getBoards]
        //새로고침 시 (캐시로 조회) :
        //              Computed cache key 'boards:page:2:size:10' for operation Builder[public java.util.List org.example.redisspring.BoardService.getBoards(int,int)] caches=[getBoards] | key=''boards:page:' + #page + ':size:' + #size' | keyGenerator='' | cacheManager='boardCacheManager' | cacheResolver='' | condition='' | unless='' | sync='false'
        //              Cache entry for key 'boards:page:1:size:10' found in cache(s) [getBoards]
    }
}
