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

import edu.spring.hotel.domain.ReviewVO;
import edu.spring.hotel.service.ReviewService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class ReviewServiceTest {
	private static final Logger logger =
			LoggerFactory.getLogger(ReviewServiceTest.class);
	
	@Autowired
	private ReviewService reviewService;
	
	@Test
	public void test() throws Exception {
		testCreate();
//		testRead();
//		testUpdate();
//		testDelete();
	} // end test()

	private void testCreate() {
		logger.info("testCreate() 호출");
		ReviewVO vo = new ReviewVO(0, "test", 1, "테스트리뷰", 1, null);
		reviewService.create(vo);
		
	}

	private void testRead() {
		logger.info("testRead() 호출");
		List<ReviewVO> list = reviewService.read(2);
		for (ReviewVO vo : list) {
			logger.info(vo.toString());
		}
	}

	private void testUpdate() {
		logger.info("testUpdate() 호출");
		ReviewVO vo = new ReviewVO(9, "test", 2, "리뷰 수정", 1, null);
		int result = reviewService.update(vo);
		logger.info(result + "행 수정 완료");
		
	}

	private void testDelete() throws Exception {
		logger.info("testDelete() 호출");
		int result = reviewService.delete(9, 2);
		logger.info(result + "행 삭제완료");
		
	}
	
	
} // end ReviewServiceTest
