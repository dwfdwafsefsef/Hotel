package edu.spring.hotel.persistence;

import edu.spring.hotel.domain.HeartVO;

public interface HeartDAO {

	int select(int hotelId, String memberId); // ���ƿ� ���� �Ǻ� �˻�
	
	int insert(HeartVO vo); // ���ƿ� ���
	
	int delete(int hotelId, String memberId); // ���ƿ� ����
	
	int selectHeartCnt(String memberId); // ���� ���ƿ� �� ���� �˻�
	
}
