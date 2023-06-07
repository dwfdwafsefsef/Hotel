package edu.spring.hotel.persistence;

import java.util.List;

import edu.spring.hotel.domain.ReservationVO;

public interface ReservationDAO {
	// ���� ���
	int insert(ReservationVO vo);
	// �� ���� ��� �˻�
	List<ReservationVO> select(String memberId);
	// ���� ����
	int delete(int reservationId, int roomId);
	
} // end ReservationDAO
