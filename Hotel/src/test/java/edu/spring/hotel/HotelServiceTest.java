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
import edu.spring.hotel.pageutil.PageCriteria;
import edu.spring.hotel.service.HotelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class HotelServiceTest {
	private static final Logger logger = 
			LoggerFactory.getLogger(HotelServiceTest.class);
	
	@Autowired
	private HotelService Service;
	
	@Test
	public void testService() {
//		testread();
//		testreadPaging();
//		testreadAllReviewDesc();
//		testGetTotalCounts();
//		testSelectByHotelName();
		testReadByHeart();
	}

	private void testReadByHeart() {
		logger.info("testReadByHeart() �샇異�");
		List<HotelVO> list = Service.readByHeart("test");
		for (HotelVO vo : list) {
			logger.info(vo.toString());
		}
		
	}

//	private void testSelectByHotelName() {
//		logger.info("testSelectByHotelName() �샇異�");
//		List<HotelVO> list = Service.readByHotelName("", null);
//		for (HotelVO vo : list) {
//			logger.info(vo.toString());
//		}		
//	}

	private void testGetTotalCounts() {
		logger.info("count()�샇異�");
		int totalCounts = Service.getTotalCounts();
		logger.info("�쟾泥� �샇�뀛 �닔 : " + totalCounts);
		
	}

	private void testreadPaging() {
		logger.info("read()�샇異�");
		PageCriteria criteria = new PageCriteria(1, 3);
		List<HotelVO> list = Service.read(criteria);
		for(HotelVO vo : list) {
			logger.info(vo.toString());
		}
	} // end testreadPaging �쟾泥� 寃��깋 �럹�씠吏뺤쿂由� 

	private void testreadAllReviewDesc() {
//		logger.info("read()�샇異�");
//		List<HotelVO> list = Service.readOrderByReviewDesc();
//		for(HotelVO vo : list) {
//			logger.info(vo.toString());
//		}
	} // end testreadAllReviewDesc 由щ럭 �궡由쇱감�닚 	

	private void testread() {
		HotelVO vo = Service.read(1);
		logger.info(vo.toString());
		
	} // end testread �긽�꽭 寃��깋



	
	
} // end HotelServiceTest
