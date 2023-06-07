package edu.spring.hotel.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.hotel.domain.HeartVO;

@Repository
public class HeartDAOImple implements HeartDAO {
	private static Logger logger = 
			LoggerFactory.getLogger(HeartDAOImple.class);
	private static final String NAMESPACE = 
			"edu.spring.hotel.HeartMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int select(int hotelId, String memberId) {
		logger.info("select() ȣ��");
		logger.info("hotelId = " + hotelId);
		logger.info("memberId = " + memberId);
		Map<String, Object> args = new HashMap<>();
		args.put("hotelId", hotelId);
		args.put("memberId", memberId);
		return sqlSession.selectOne(NAMESPACE + ".select", args);
	}

	@Override
	public int insert(HeartVO vo) {
		logger.info("insert() ȣ�� : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int delete(int hotelId, String memberId) {
		logger.info("delete() ȣ��");
		logger.info("hotelId = " + hotelId);
		logger.info("memberId = " + memberId);
		Map<String, Object> args = new HashMap<>();
		args.put("hotelId", hotelId);
		args.put("memberId", memberId);
		return sqlSession.delete(NAMESPACE + ".delete", args);
	}

	@Override
	public int selectHeartCnt(String memberId) {
		logger.info("selectHeartCnt() ȣ�� : memberId = " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_heart_cnt", memberId);
	}
	
} // end HeartDAOImple