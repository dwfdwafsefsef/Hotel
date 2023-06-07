package edu.spring.hotel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.hotel.domain.HotelVO;
import edu.spring.hotel.domain.ReviewVO;
import edu.spring.hotel.persistence.HotelDAO;
import edu.spring.hotel.persistence.ReviewDAO;

@Service
public class ReviewServiceImple implements ReviewService {
	private static final Logger logger =
			LoggerFactory.getLogger(ReviewServiceImple.class);
	
	@Autowired
	private ReviewDAO reviewDAO;
	
	@Autowired
	private HotelDAO hotelDAO;

	@Transactional(value="transactionManager")
	@Override
	public int create(ReviewVO vo) {
	    logger.info("���� create() ȣ�� : vo = " + vo.toString());
	    int result = reviewDAO.insert(vo);
	    logger.info("���� �Է� ����!");

	    // ȣ�� ���� ����
	    int updateResult = hotelDAO.updateReviewCntAndAvg(1, vo.getHotelId());
	    logger.info("ȣ�� ���� ���� ����!");

	    return result;
	}
	@Override
	public List<ReviewVO> read(int hotelId) {
		logger.info("���� read() ȣ�� : hotelId = " + hotelId);
		return reviewDAO.select(hotelId);
	}

	@Override
	public int update(ReviewVO vo) {
		logger.info("���� update() ȣ�� : vo = " + vo.toString());
		return reviewDAO.update(vo);
	}

	@Transactional(value="transactionManager")
	@Override
	public int delete(int reviewId, int hotelId) throws Exception {
		logger.info("���� delete() ȣ��");
		reviewDAO.delete(reviewId);
		logger.info("���� ���� ����!");
		int updateResult = hotelDAO.updateReviewCntAndAvg(-1, hotelId);
	    logger.info("ȣ�� ���� ���� ����!");
		return 1;
	}
	
	
	
} // end ReviewServiceImple
