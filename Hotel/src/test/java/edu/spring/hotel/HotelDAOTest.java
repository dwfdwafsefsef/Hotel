package edu.spring.hotel;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.hotel.domain.HotelVO;
import edu.spring.hotel.domain.MemberVO;
import edu.spring.hotel.pageutil.PageCriteria;
import edu.spring.hotel.persistence.HotelDAO;
import edu.spring.hotel.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class HotelDAOTest {
	private static final Logger logger = 
			LoggerFactory.getLogger(HotelDAOTest.class);
	
	@Autowired
	private HotelDAO dao;
	
	@Test
	public void testDAO() {
//		testSelect();
//	  testSelectAllReviewDesc();
		testSelectPaging();
//		testGetTotalCounts();
	}



	private void testGetTotalCounts() {
		int totalCounts = dao.getTotalCounts();
		logger.info("전체 호텔 수 : " + totalCounts);
	} // end testGetTotalCOunts() 전체 호텔 수



	private void testSelectPaging() {
		PageCriteria criteria = new PageCriteria(1, 3);
		List<HotelVO> list = dao.select(criteria);
		for(HotelVO vo : list) {
			logger.info(vo.toString());
		}
	} // end testSelectPaging() 호텔 페이징 처리 



	private void testSelectAllReviewDesc() {
//		List<HotelVO> list = dao.selectAllByReviewDesc();
//		for(HotelVO vo : list) {
//			logger.info(vo.toString());
//		}
		
	} // end testSelectAllReviewDesc() 리뷰순 내림차순 전체 검색


	private void testSelect() {
		HotelVO vo = dao.select(4);
		logger.info(vo.toString());
		
	} // testSelect 상세 검색

	
	
} // end HotelDAOTest
