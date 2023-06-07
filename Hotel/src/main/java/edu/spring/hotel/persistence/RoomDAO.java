package edu.spring.hotel.persistence;

import java.util.List;

import edu.spring.hotel.domain.RoomVO;

public interface RoomDAO {
	
	// �� ȣ���� �� ��� ��ü�˻�
	List<RoomVO> selectByHotelId(int hotelId);
	
	// �� �󼼰˻�
	RoomVO select(int roomId);
	
	// ���� ���, ��Ұ� �Ͼ �� �ܿ� ���� �� ����
	int updateRoomCnt(int amount, int roomId);
	
} // end RoomDAO
