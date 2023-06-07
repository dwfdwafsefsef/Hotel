package edu.spring.hotel.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.hotel.domain.MemberVO;

@Repository
public class MemberDAOImple implements MemberDAO{
	private static final Logger logger =
			LoggerFactory.getLogger(MemberDAOImple.class);
	private static final String NAMESPACE =
			"edu.spring.hotel.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(MemberVO vo) {
		logger.info("insert() ȣ�� : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	} // end insert ���

	@Override
	public MemberVO select(String memberId) {
		logger.info("select()ȣ�� : memberId = " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", memberId);
	} // end select �󼼰˻�

	@Override
	public int update(MemberVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	} // end update ����

	@Override
	public int delete(MemberVO vo) {
		logger.info("delete() ȣ��");
		logger.info("memberId = " + vo.getMemberId());
		logger.info("memberPw = " + vo.getMemberPw());
		return sqlSession.delete(NAMESPACE + ".delete", vo);
	} // end delete ����

	@Override
	public int idCheck(MemberVO vo) {
		logger.info("idcheck()ȣ�� : vo = " + vo.toString());
		return sqlSession.selectOne(NAMESPACE + ".idCheck", vo);
	} // end idcheck ���̵� �ߺ�üũ

	@Override
	public MemberVO login(MemberVO vo) {
		logger.info("login() ȣ��");
		logger.info("vo = " + vo.toString());
		return sqlSession.selectOne(NAMESPACE + ".login", vo);
	} // �α���

	@Override
	public String findMemberId(MemberVO vo) {
		logger.info("findMemberId() ȣ�� vo = " + vo.toString());
		return sqlSession.selectOne(NAMESPACE + ".find_member_id", vo);
	} // ���̵� ã��

	@Override
	public String findMemberPw(MemberVO vo) {
		logger.info("findMemberPw() ȣ�� : vo = " + vo.toString());
		return sqlSession.selectOne(NAMESPACE + ".find_member_pw", vo);
	} // ��й�ȣ ã��
	
	@Override
	public int phoneCheck(MemberVO vo) {
		logger.info("phonecheck()ȣ�� : vo = " +  vo.toString());
		return sqlSession.selectOne(NAMESPACE + ".phoneCheck", vo);
	} // end phone �޴��� ��ȣ �ߺ�üũ

	@Override
	public Date selectMemberDateCreated(String memberId) {
		logger.info("selectMemberDateCreated() ȣ�� : memberId = " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_member_date_created", memberId);
	}

} // end MemberDAOImple
