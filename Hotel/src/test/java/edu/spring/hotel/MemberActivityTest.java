package edu.spring.hotel;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.hotel.domain.MemberActivity;
import edu.spring.hotel.persistence.BoardDAO;
import edu.spring.hotel.persistence.HeartDAO;
import edu.spring.hotel.persistence.MemberDAO;
import edu.spring.hotel.persistence.ReplyDAO;
import edu.spring.hotel.persistence.ReviewDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class MemberActivityTest {
	private static final Logger logger =
			LoggerFactory.getLogger(MemberActivityTest.class);
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private ReplyDAO replyDAO;
	
	@Autowired
	private ReviewDAO reviewDAO;
	
	@Autowired
	private HeartDAO heartDAO;
	
	@Test
	public void test() {
		testSelect();
	}

	private void testSelect() {
		logger.info("testSelect() 호출");
		String memberId = "test";
		Date memberDateCreated = memberDAO.selectMemberDateCreated(memberId);
		int boardCnt = boardDAO.selectBoardCnt(memberId);
		int replyCnt = replyDAO.selectReplyCnt(memberId);
		int reviewCnt = reviewDAO.selectReviewCnt(memberId);
		int heartCnt = heartDAO.selectHeartCnt(memberId);
		MemberActivity activity = 
				new MemberActivity(memberDateCreated, boardCnt, replyCnt, reviewCnt, heartCnt);
		logger.info(activity.toString());
		
	}

}
