package edu.spring.hotel.persistence;

import java.util.List;

import edu.spring.hotel.domain.HotelVO;
import edu.spring.hotel.pageutil.PageCriteria;


public interface HotelDAO {
	
	
	HotelVO select(int hotelId); // ȣ�� �� �˻�
	
	List<HotelVO> select(PageCriteria criteria); // ȣ�� ����¡ ó���� ���� ��ü �˻�
	
	List<HotelVO> selectAllByReviewDesc(PageCriteria criteria); // ȣ�� ���� ���� �� ��ü�˻�
	
	List<HotelVO> selectAllByReviewAvgDesc(PageCriteria criteria); // ȣ�� ��� ���� �� ��ü�˻�
	
	List<HotelVO> selectAllByHotelNameAsc(PageCriteria criteria); // ȣ�� ������ �� ��ü�˻�
	
	List<HotelVO> selectByHotelName(String keyword, int start, int end); // ȣ�� �̸� Ű����� �˻�

	int getTotalCounts(); // ��ü ȣ�� �� �ҷ�����
	
	int getTotalCountsByHotelName(String keyword); // ȣ�ڸ� �˻��� ���� ȣ�� �� �ҷ�����

	int updateReviewCntAndAvg(int amount, int hotelId); // ȣ�� ���� ���, ������ ���� ��� ����
	
	int updateHeartCnt(int amount, int hotelId); // ��Ʈ ���, ���� �� ȣ�� ���̺��� ��Ʈ ���� ����
	
	List<HotelVO> selectByHeart(String memberId); // ����ڰ� ���� ȣ�ڸ�� ��ü�˻�
	
}
