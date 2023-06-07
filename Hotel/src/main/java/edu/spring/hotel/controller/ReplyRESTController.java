package edu.spring.hotel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.hotel.domain.ReplyVO;
import edu.spring.hotel.service.ReplyService;



// * RESTful url과 의미
// /replies (POST) : 댓글 추가(insert)
// /replies/all/숫자 (GET) : 해당 글 번호(boardId)의 모든 댓글 검색(select)
// /replies/숫자 (PUT) : 해당 댓글 번호(replyId)의 내용을 수정(update)
// /replies/숫자 (DELETE) : 해당 댓글 번호(replyId)의 댓글을 삭제(delete)

@RestController
@RequestMapping(value="board/replies")
public class ReplyRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(ReplyRESTController.class);
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping // POST : 댓글 입력
	public ResponseEntity<Integer> createReply(@RequestBody ReplyVO vo) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를
		//	 자바 객체로 변환해주는 annotation
		logger.info("createReply() 호출 : vo = " + vo.toString());
		
		// ResponseEntity<T> : Rest 방식에서 데이터를 리턴할 때 쓰이는 객체
		// - 데이터 HttpStatus를 전송
		// - <T> : 보내고자 하는 데이터 타입
		int result = 0;
		try {
			result = replyService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end createReply()
	
	@GetMapping("/all/{boardId}") // GET : 댓글 선택(all)
	public ResponseEntity<List<ReplyVO>> readReplies(
			@PathVariable("boardId") int boardId) {
			// @PathVariable("boardId") : /all/{boardId} 값을 설정된 변수에 저장
		logger.info("readReplies() 호출 : boardId = " + boardId);
		List<ReplyVO> list = replyService.read(boardId);
		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
	} // end readReplies()
	
	@PutMapping("/{replyId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> updateReply(
			@PathVariable("replyId") int replyId,
			@RequestBody ReplyVO vo
			) {
		int result = replyService.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end updateReply()
	
	@DeleteMapping("/{replyId}")
	public ResponseEntity<Integer> deleteReply(
			@PathVariable("replyId") int replyId,
			@RequestBody int boardId) {
		logger.info("replyId = " + replyId);
		
		int result = 0;
		try {
			result = replyService.delete(replyId, boardId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end deleteReply()
	
} // end ReplyRESTController
