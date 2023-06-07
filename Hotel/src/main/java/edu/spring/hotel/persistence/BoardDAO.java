package edu.spring.hotel.persistence;

import java.util.List;

import edu.spring.hotel.domain.BoardVO;
import edu.spring.hotel.pageutil.PageCriteria;

public interface BoardDAO {
	// 게시글 등록
	int insert(BoardVO vo);
	// 게시글 전체검색(기본/최신순)
	List<BoardVO> selectAllDefault();
	// 게시글 전체검색(댓글 많은 순)
	List<BoardVO> selectAllReplyCnt();
	// 게시글 상세검색
	BoardVO select(int boardId);
	// 게시글 수정
	int update(BoardVO vo);
	// 게시글 삭제
	int delete(int boardId);
	// 게시글 제목 또는 내용을 키워드로 검색
	List<BoardVO> selectByTitleOrContent(String keyword, int start, int end);
	// 게시글 작성자를 키워드로 검색
	List<BoardVO> selectByMemberId(String keyword, int start, int end);
	// 페이징 처리를 위한 전체검색
	List<BoardVO> select(PageCriteria criteria);
	// 전체 게시글 수 불러오기
	int getTotalCounts();
	// 제목 또는 내용으로 검색했을 때 게시글 수 불러오기
	int getTotalCountsByTitleOrContent(String keyword);
	// 작성자 아이디로 검색했을 때 게시글 수 불러오기
	int getTotalCountsByMemberId(String keyword);
	// 댓글 수정, 삭제 시 댓글 개수 수정
	int updateReplyCnt(int amount, int boardId);
	// 내가 작성한 게시글 수 조회
	int selectBoardCnt(String memberId);
	
} // end BoardDAO
