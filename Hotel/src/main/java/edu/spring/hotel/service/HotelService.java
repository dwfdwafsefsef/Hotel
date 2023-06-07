package edu.spring.hotel.service;

import java.util.List;


import edu.spring.hotel.domain.HotelVO;
import edu.spring.hotel.pageutil.PageCriteria;

// CRUD(Create, Read, Update, Delete)
public interface HotelService {
	HotelVO read(int hotelId); // �� �˻�
	
	List<HotelVO> read(PageCriteria criteria); // ��ü �˻� (����¡ ó��)
	
	List<HotelVO> readOrderByReviewDesc(PageCriteria criteria); // ����� �������� ��ü �˻�
	
	List<HotelVO> readOrderByReviewAvgDesc(PageCriteria criteria); // ��� ���� �������� ��ü�˻�
	
	List<HotelVO> readOrderByHotelNameAsc(PageCriteria criteria); // ������ �������� ��ü�˻�
	
	List<HotelVO> readByHotelName(String keyword, PageCriteria criteria); // ȣ�� �̸� Ű����� �˻�
	
	int getTotalCounts(); //  ��ü ȣ�� �� �޾ƿ��� 
	
	int getTotalCountsByHotelName(String keyword); // ȣ�ڸ� �˻��� ���� ȣ�� �� �޾ƿ���
	
	List<HotelVO> readByHeart(String memberId); // ���� ���� ȣ�� ��� ��ȸ


} // end HotelService
