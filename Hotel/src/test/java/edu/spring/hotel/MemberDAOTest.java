package edu.spring.hotel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.hotel.domain.MemberVO;
import edu.spring.hotel.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration
public class MemberDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);

	@Autowired
	private MemberDAO dao;

	@Test
	public void testDAO() throws Exception {
//		testInsert();
//		testSelectByMemberId();
//		testupdate();
//		testdelete();
//		testLogin();
//		testFindMemberId();
		testFindMemberPw();
	}

	private void testFindMemberId() {
		logger.info("testFindMemberId() 호출");
		MemberVO vo = new MemberVO(null, null, "1", "1", "1", null);		
		String memberId = dao.findMemberId(vo);
		logger.info("아이디 찾기 성공! : memberId = " + memberId);
		
	}

	private void testFindMemberPw() {
		logger.info("testFindMemberPw() 호출");
		MemberVO vo = new MemberVO("test", null, "1", "1", "1", null);
		String memberPw = dao.findMemberPw(vo);
		logger.info("비밀번호 찾기 성공! : memberPw = " + memberPw);
		
	}

	private void testdelete() {
		MemberVO vo = new MemberVO("test4", "1", null, null, null, null);
		int result = dao.delete(vo);
		if (result == 1) {
			logger.info("delete 성공");
		} else {
			logger.info("delete 실패");
		}

	} // end delete 삭제

	private void testupdate() {
		MemberVO vo = new MemberVO("test4", "1", "1", "1", "1", null);
		int result = dao.update(vo);
		if (result == 1) {
			logger.info("update 성공");
		} else {
			logger.info("update 실패");
		}
	} // end update 수정

	private void testSelectByMemberId() {
		MemberVO vo = dao.select("test2");
		logger.info(vo.toString());

	} // end selectByMemberId 상세 검색

	private void testInsert() {
		MemberVO vo = new MemberVO("test4", "1234", "둘리", "010-1234-1234", "test@test.com", null);
		int result = dao.insert(vo);
		logger.info(result + "행 삽입");
	} // end test 등록
	
	private void testLogin() throws Exception {
		logger.info("testLogin() 호출");
		MemberVO vo = new MemberVO("test", "test", null, null, null, null);
		vo = dao.login(vo);
		logger.info("아이디 : " + vo.getMemberId());
		logger.info("비밀번호 : " + vo.getMemberPw());
	}

} // end MemberDAOTest