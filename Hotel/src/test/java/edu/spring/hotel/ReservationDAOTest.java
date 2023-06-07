package edu.spring.hotel;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.hotel.domain.ReservationVO;
import edu.spring.hotel.persistence.ReservationDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class ReservationDAOTest {
	private static final Logger logger =
			LoggerFactory.getLogger(ReservationDAOTest.class);
	
	@Autowired
	private ReservationDAO dao;
	
	@Test
	public void test() {
		testInsert();
//		testSelect();
//		testDelete();
	}

	private void testInsert() {
		logger.info("testInsert() 호출");
		ReservationVO vo = new ReservationVO(0, 228, 13, "test", "테스트 호텔", "1인실", 30000, new Date(), new Date());
		int result = dao.insert(vo);
		logger.info(result + "행 삽입");
		
	}

	private void testSelect() {
		logger.info("testSelect() 호출");
		List<ReservationVO> list = dao.select("test");
		for (ReservationVO vo : list) {
			logger.info(vo.toString());
		}
	}

	private void testDelete() {
		logger.info("testDelete() 호출 ");
//		int result = dao.delete(1);
//		logger.info(result + "행 삭제");
		
	}
	
	
	
} // end ReservationDAOTest
