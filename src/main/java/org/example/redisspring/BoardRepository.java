package org.example.redisspring;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


//jpa를 사용할거기에 인터페이스로 생성
public interface BoardRepository extends JpaRepository<Board, Long> {

    //최신순으로 조회
    Page<Board> findAllByOrderByCreatedAtDesc(Pageable pageable); //모든 엔티티를 createdAt 기준으로 내림차순 정렬해서 가져오기
/*
    select
            b1_0.id,
            b1_0.content,
            b1_0.created_at,
            b1_0.title
        from  boards b1_0
    order by b1_0.created_at desc
    limit ?, ?
*/
}
