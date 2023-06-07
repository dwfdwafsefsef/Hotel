package edu.spring.hotel.persistence;

import java.util.Date;

import edu.spring.hotel.domain.MemberVO;

public interface MemberDAO {
	
	int insert(MemberVO vo); // ȸ�� ���
	
	MemberVO select(String memberId); // ȸ�� �󼼰˻�
	
	int update(MemberVO vo); // ȸ�� ����
	
	int delete(MemberVO vo); // ȸ�� ����
	
	int idCheck(MemberVO vo); // ���̵� �ߺ�üũ
	
	MemberVO login(MemberVO vo) throws Exception; // �α���
	
	String findMemberId(MemberVO vo); // ���̵� ã��
	 
	String findMemberPw(MemberVO vo); // ��й�ȣ ã��
	
	int phoneCheck(MemberVO vo); //  �޴��� ��ȣ �ߺ�üũ 
	
	Date selectMemberDateCreated(String memberId);
	
}
