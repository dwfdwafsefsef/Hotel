package edu.spring.hotel.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.hotel.domain.BoardVO;
import edu.spring.hotel.pageutil.PageCriteria;
import edu.spring.hotel.pageutil.PageMaker;

@Repository
public class BoardDAOImple implements BoardDAO {
	private static final Logger logger =
			LoggerFactory.getLogger(BoardDAOImple.class);
	private static final String NAMESPACE =
			"edu.spring.hotel.BoardMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(BoardVO vo) {
		logger.info("insert() 호출 : vo = " + vo);
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<BoardVO> selectAllDefault() {
		logger.info("selectAllDefault() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all_default");
	}

	@Override
	public List<BoardVO> selectAllReplyCnt() {
		logger.info("selectAllReplyCnt() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all_reply_cnt");
	}

	@Override
	public BoardVO select(int boardId) {
		logger.info("select() 호출 : boardId = " + boardId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_board_id", boardId);
	}

	@Override
	public int update(BoardVO vo) {
		logger.info("update() 호출 : vo = " + vo);
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int boardId) {
		logger.info("delete() 호출 : boardId = " + boardId);
		return sqlSession.delete(NAMESPACE + ".delete", boardId);
	}

	@Override
	public List<BoardVO> selectByTitleOrContent(String keyword, int start, int end) {
		logger.info("selectByTitleOrContent() 호출 : keyword = " + keyword);
		Map<String, Object> args = new HashMap<>();
		args.put("keyword", "%" + keyword + "%");
		args.put("start", start);
		args.put("end", end);
		return sqlSession.selectList(NAMESPACE + ".select_by_title_content", args);
	}

	@Override
	public List<BoardVO> selectByMemberId(String keyword, int start, int end) {
		logger.info("selectByMemberId() 호출 : keyword = " + keyword);
		Map<String, Object> args = new HashMap<>();
		args.put("keyword", "%" + keyword + "%");
		args.put("start", start);
		args.put("end", end);
		return sqlSession.selectList(NAMESPACE + ".select_by_member_id", args);
	}

	@Override
	public List<BoardVO> select(PageCriteria criteria) {
		logger.info("select() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() 호출");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}
	
	@Override
	public int getTotalCountsByTitleOrContent(String keyword) {
		logger.info("getTotalCountsByTitleOrContent() 호출 : keyword = " + keyword);
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_title_or_content", "%" + keyword + "%");
	}

	@Override
	public int getTotalCountsByMemberId(String keyword) {
		logger.info("getTotalCountsByMemberId() 호출 : keyword = " + keyword);
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_member_id", "%" + keyword + "%");
	}

	@Override
	public int updateReplyCnt(int amount, int boardId) {
		logger.info("updateReplyCnt() 호출");
		logger.info("amount = " + amount);
		logger.info("boardId = " + boardId);
		Map<String, Integer> args = new HashMap<>();
		args.put("amount", amount);
		args.put("boardId", boardId);
		return sqlSession.update(NAMESPACE + ".update_reply_cnt", args);
	}

	@Override
	public int selectBoardCnt(String memberId) {
		logger.info("selectBoardCnt() 호출 : memberId = " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_board_cnt", memberId);
	}


} // end BoardDAOImple
