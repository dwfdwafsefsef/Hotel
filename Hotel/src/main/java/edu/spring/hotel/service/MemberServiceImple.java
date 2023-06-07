package edu.spring.hotel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.hotel.domain.MemberVO;
import edu.spring.hotel.pageutil.PageCriteria;
import edu.spring.hotel.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService{
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberServiceImple.class);
	
	@Autowired
	private MemberDAO dao;

	@Override
	public int create(MemberVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	} // end create

	@Override
	public MemberVO read(String memberId) {
		logger.info("read() ȣ�� : memberId = " + memberId);
		return dao.select(memberId);
	} // end read

	@Override
	public int update(MemberVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString() );
		return dao.update(vo);
	} // end update

	@Override
	public int delete(MemberVO vo) {
		logger.info("delete()ȣ�� : vo = " + vo.toString());
		return dao.delete(vo); 
	} // end delete

	@Override
	public int idCheck(MemberVO vo){
		logger.info("idcheck()ȣ�� : vo = " + vo.toString());
		return dao.idCheck(vo);
	} // end idcheck

	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		logger.info("login() ȣ��");
		logger.info("memberId = " + vo.getMemberId());
		logger.info("memberPw = " + vo.getMemberPw());
		return dao.login(vo);
	}

	@Override
	public String findMemberId(MemberVO vo) {
		logger.info("findMemberId() ȣ�� : memberId = " + vo.getMemberId());
		return dao.findMemberId(vo);
	}

	@Override
	public String findMemberPw(MemberVO vo) {
		logger.info("findMemberPw() ȣ�� : memberPw = " + vo.getMemberPw());
		return dao.findMemberPw(vo);
	}
	
	@Override
	public int phoneCheck(MemberVO vo){
		logger.info("phoneCheck()ȣ�� : vo = " + vo.toString());
		return dao.phoneCheck(vo);
	}
	
} // end MemberServiceImple
