
package edu.spring.hotel.service;

import java.util.List;

import edu.spring.hotel.domain.ReplyVO;

//CRUD(Create, Read, Update, Delete)

public interface ReplyService {
	// ��� �ۼ�
	int create(ReplyVO vo);
	// ��� �б�
	List<ReplyVO> read(int boardId);
	// ��� ����
	int update(ReplyVO vo);
	// ��� ����
	int delete(int replyId, int boardId) throws Exception;
}
