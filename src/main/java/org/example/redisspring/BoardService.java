package org.example.redisspring;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    // Redis 적용 후(속도는 수치로 증명해야함. 빠른것 같아요 느린것 같아요 x)
    // @Cacheable : Cache Aside 전력으로 캐싱이 적용됨. (Cache Aside 전략은 캐시에서 데이터를 확인하고, 없다면 db를 통해 조회해오는 방식)
        //    해당 메서드로 요청이 들어오면 레디스를 확인한 후에 데이터가 있다면 레디스의 데이터를 조회해서 바로 응답한다.
        //    만약 데이터가 없다면 메서드 내부의 로직을 실행시킨 뒤에 return 값으로 응답한다.
        //    그리고 그 return 값을 레디스에 저장
    // 해석 : board:page:1:size:10 = 게시글에 페이지 1번 페이지에 사이즈가 10개짜리인 데이터
    @Cacheable(cacheNames = "getBoards", key = "'boards:page:' + #page + ':size:' + #size", cacheManager = "boardCacheManager")
    public List<Board> getBoards(int page, int size){
        //cacheNames : 캐시 이름을 설정

        //일반적으로 흔히 많이 짜는 페이지네이션 로직
        Pageable pageable = PageRequest.of(page - 1, size); //select count(b1_0.id) from boards b1_0

        Page<Board> pageOfBoards = boardRepository.findAllByOrderByCreatedAtDesc(pageable);

        return pageOfBoards.getContent();
    }

    //캐시 생성 로그  : Creating cache entry for key 'boards:page:1:size:10' in cache(s) [getBoards]
    //재요청 시 (캐시로 조회) :
    //              Computed cache key 'boards:page:2:size:10' for operation Builder[public java.util.List org.example.redisspring.BoardService.getBoards(int,int)] caches=[getBoards] | key=''boards:page:' + #page + ':size:' + #size' | keyGenerator='' | cacheManager='boardCacheManager' | cacheResolver='' | condition='' | unless='' | sync='false'
    //              Cache entry for key 'boards:page:1:size:10' found in cache(s) [getBoards]

}
