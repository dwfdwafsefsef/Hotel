package edu.spring.hotel.service;

import edu.spring.hotel.domain.HeartVO;

public interface HeartService {
	
	// ���ƿ� ���� �б�
	int read(int hotelId, String memberId);
	
	// ���ƿ� ���
	int create(HeartVO vo);
	
	// ���ƿ� ���
	int delete(int hotelId, String memberId);
	
	
} // end HeartService
