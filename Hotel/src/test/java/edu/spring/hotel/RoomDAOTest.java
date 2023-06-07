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

import edu.spring.hotel.domain.RoomVO;
import edu.spring.hotel.persistence.RoomDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class RoomDAOTest {
	private static final Logger logger = 
			LoggerFactory.getLogger(RoomDAOTest.class);
	
	@Autowired
	private RoomDAO dao;
	
	@Test
	public void testDAO() {
//		testSelectByHotelId();
		testSelect();
	}

	private void testSelectByHotelId() {
		logger.info("testSelectByHotelId() 호출");
		List<RoomVO> list = dao.selectByHotelId(228);
		for (RoomVO vo : list) {
			logger.info(vo.toString());
		}
		
	}

	private void testSelect() {
		logger.info("testSelect() 호출");
		RoomVO vo = dao.select(3);
		logger.info(vo.toString());
		
	}
	
} // end RoomDAOTest
