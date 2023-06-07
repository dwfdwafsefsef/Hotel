package edu.spring.hotel.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.hotel.domain.ReviewVO;

@Repository
public class ReviewDAOImple implements ReviewDAO {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReviewDAOImple.class);
	private static final String NAMESPACE =
			"edu.spring.hotel.ReviewMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ReviewVO vo) {
		logger.info("insert() ȣ�� : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ReviewVO> select(int hotelId) {
		logger.info("select() ȣ�� : hotelId = " + hotelId);
		return sqlSession.selectList(NAMESPACE + ".select_all", hotelId);
	}

	@Override
	public int update(ReviewVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int reviewId) {
		logger.info("delete() ȣ�� : reviewId = " + reviewId);
		return sqlSession.delete(NAMESPACE + ".delete", reviewId);
	}

	@Override
	public int selectReviewCnt(String memberId) {
		logger.info("selectReviewCnt() ȣ�� : memberId = " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_review_cnt", memberId);
	}

} // end ReviewDAOImple
