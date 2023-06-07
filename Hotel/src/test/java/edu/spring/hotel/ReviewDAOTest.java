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
import edu.spring.hotel.persistence.ReviewDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class ReviewDAOTest {
	private static final Logger logger =
			LoggerFactory.getLogger(ReviewDAOTest.class);
	
	@Autowired
	private ReviewDAO dao;
	
	@Test
	public void test() {
		testInsert();
//		testSelect();
//		testUpdate();
//		testDelete();
	} // end test()

	private void testInsert() {
		logger.info("testInsert() 호출");
		ReviewVO vo = new ReviewVO(0, "test", 98, "테스트리뷰", 1, null);
		int result = dao.insert(vo);
		if (result == 1) {
			logger.info(result + "행 등록 완료");
		}
		
	}

	private void testSelect() {
		logger.info("testSelect() 호출");
		List<ReviewVO> list = dao.select(98);
		for (ReviewVO vo : list) {
			logger.info(vo.toString());
		}
		
	}

	private void testUpdate() {
		logger.info("testUpdate() 호출");
		ReviewVO vo = new ReviewVO(6, null, 2, "리뷰 수정", 0, null);
		int result = dao.update(vo);
		if (result == 1) {
			logger.info(result + "행 수정");
		}
	}

	private void testDelete() {
		logger.info("testDelete() 호출");
		int result = dao.delete(6);
		if (result == 1) {
			logger.info(result + "행 삭제");
		}
		
	}
	
} // end ReviewDAOTest
