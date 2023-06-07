package edu.spring.hotel.service;

import java.util.List;

import edu.spring.hotel.domain.ReviewVO;

public interface ReviewService {
	// ���� �ۼ�
	int create(ReviewVO vo);
	// ���� �б�
	List<ReviewVO> read(int hotelId);
	// ���� ����
	int update(ReviewVO vo);
	// ���� ����
	int delete(int reviewId, int hotelId) throws Exception;
	
} // end ReviewService
