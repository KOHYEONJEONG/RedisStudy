package org.example.redisspring;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


//jpa를 사용할거기에 인터페이스로 생성
public interface BoardRepository extends JpaRepository<Board, Long> {

    //최신순으로 조회
    Page<Board> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
