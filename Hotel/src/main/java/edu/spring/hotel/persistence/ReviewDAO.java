package edu.spring.hotel.persistence;

import java.util.List;

import edu.spring.hotel.domain.ReviewVO;

public interface ReviewDAO {
	// ���� ���
	int insert(ReviewVO vo);
	// ���� ��ü�˻�
	List<ReviewVO> select(int hotelId);
	// ���� ����
	int update(ReviewVO vo);
	// ���� ����
	int delete(int reviewId);
	// ���� �ۼ��� ���� �� �˻�
	int selectReviewCnt(String memberId);
	
} // end ReviewDAO
