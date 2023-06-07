package edu.spring.hotel.service;

import edu.spring.hotel.domain.MemberVO;
import edu.spring.hotel.pageutil.PageCriteria;

// CRUD(create, Read, Update, Delete)
public interface MemberService {
	int create(MemberVO vo);
	MemberVO read(String memberId);
	int update(MemberVO vo);
	int delete(MemberVO vo);
	MemberVO login(MemberVO vo) throws Exception;
	int idCheck(MemberVO vo); // ���̵� �ߺ�üũ
	String findMemberId(MemberVO vo); // ���̵� ã��
	String findMemberPw(MemberVO vo); // ��й�ȣ ã��
	int phoneCheck(MemberVO vo); // �޴��ȣ �ߺ�üũ
	
} // end MemberService 
