package edu.spring.hotel.persistence;

import java.util.List;

import edu.spring.hotel.domain.BoardVO;
import edu.spring.hotel.pageutil.PageCriteria;

public interface BoardDAO {
	// �Խñ� ���
	int insert(BoardVO vo);
	// �Խñ� ��ü�˻�(�⺻/�ֽż�)
	List<BoardVO> selectAllDefault();
	// �Խñ� ��ü�˻�(��� ���� ��)
	List<BoardVO> selectAllReplyCnt();
	// �Խñ� �󼼰˻�
	BoardVO select(int boardId);
	// �Խñ� ����
	int update(BoardVO vo);
	// �Խñ� ����
	int delete(int boardId);
	// �Խñ� ���� �Ǵ� ������ Ű����� �˻�
	List<BoardVO> selectByTitleOrContent(String keyword, int start, int end);
	// �Խñ� �ۼ��ڸ� Ű����� �˻�
	List<BoardVO> selectByMemberId(String keyword, int start, int end);
	// ����¡ ó���� ���� ��ü�˻�
	List<BoardVO> select(PageCriteria criteria);
	// ��ü �Խñ� �� �ҷ�����
	int getTotalCounts();
	// ���� �Ǵ� �������� �˻����� �� �Խñ� �� �ҷ�����
	int getTotalCountsByTitleOrContent(String keyword);
	// �ۼ��� ���̵�� �˻����� �� �Խñ� �� �ҷ�����
	int getTotalCountsByMemberId(String keyword);
	// ��� ����, ���� �� ��� ���� ����
	int updateReplyCnt(int amount, int boardId);
	// ���� �ۼ��� �Խñ� �� ��ȸ
	int selectBoardCnt(String memberId);
	
} // end BoardDAO
