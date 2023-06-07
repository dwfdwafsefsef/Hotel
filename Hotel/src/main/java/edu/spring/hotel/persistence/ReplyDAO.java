package edu.spring.hotel.persistence;

import java.util.List;

import edu.spring.hotel.domain.ReplyVO;

public interface ReplyDAO {
	// ��� ���
	int insert(ReplyVO vo);
	// ��� ��ü�˻�
	List<ReplyVO> selectAll(int boardId);
	// ��� ����
	int update(ReplyVO vo);
	// ��� ����
	int delete(int replyId);
	// ���� �ۼ��� ��� �� �˻�
	int selectReplyCnt(String memberId);

} // end ReplyDAO
