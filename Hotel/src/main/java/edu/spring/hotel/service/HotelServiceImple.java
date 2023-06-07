package edu.spring.hotel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.hotel.domain.HotelVO;
import edu.spring.hotel.pageutil.PageCriteria;
import edu.spring.hotel.persistence.HotelDAO;
import edu.spring.hotel.persistence.RoomDAO;

@Service 
public class HotelServiceImple implements HotelService {
	private static final Logger logger =
			LoggerFactory.getLogger(HotelServiceImple.class);
	
	@Autowired
	private HotelDAO hotelDAO;

	@Override
	public HotelVO read(int hotelId) {
		logger.info("read()ȣ�� : hotelId = " + hotelId);
		return hotelDAO.select(hotelId);
	} // end read �󼼰˻�

	@Override
	public List<HotelVO> read(PageCriteria criteria) {
		logger.info("read()ȣ��");
		return hotelDAO.select(criteria);
	} // end readAll ��ü �˻�(����¡ ó��)

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts()ȣ��");
		return hotelDAO.getTotalCounts();
	} // end getTotalCounts ��ü ȣ�� �� �ҷ����� 
	
	@Override
	public int getTotalCountsByHotelName(String keyword) {
		logger.info("getTotalCountsByHotelName() ȣ�� : keyword = " + keyword);
		return hotelDAO.getTotalCountsByHotelName(keyword);
	}
	
	@Override
	public List<HotelVO> readOrderByReviewDesc(PageCriteria criteria) {
		logger.info("readOrderByReviewDesc() ȣ��");
		return hotelDAO.selectAllByReviewDesc(criteria);
	} // end readOrderByReviewDesc ���� �������� ��ü �˻�

	@Override
	public List<HotelVO> readOrderByReviewAvgDesc(PageCriteria criteria) {
		logger.info("readOrderByReviewAvgDesc() ȣ��");
		return hotelDAO.selectAllByReviewAvgDesc(criteria);
	}

	@Override
	public List<HotelVO> readOrderByHotelNameAsc(PageCriteria criteria) {
		logger.info("readOrderByHotelNameDesc() ȣ��");
		return hotelDAO.selectAllByHotelNameAsc(criteria);
	}

	@Override
	public List<HotelVO> readByHotelName(String keyword, int start, int end) {
		logger.info("readByHotelName() ȣ�� : keyword = " + keyword);
		return hotelDAO.selectByHotelName(keyword, start, end);
	}

	@Override
	public List<HotelVO> readByHeart(String memberId) {
		logger.info("readByHeart() ȣ�� : memberId = " + memberId);
		return hotelDAO.selectByHeart(memberId);
	}

}
