package edu.spring.hotel.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.hotel.domain.ReviewVO;
import edu.spring.hotel.service.HotelService;
import edu.spring.hotel.service.ReviewService;

// /replies/���� (PUT) : �ش� ���� ��ȣ(reviewId)�� ������ ����(update)
// /replies/���� (DELETE) : �ش� ���� ��ȣ(reviewId)�� ���並 ����(delete)

@RestController
@RequestMapping(value="reviews")
public class ReviewRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(ReviewRESTController.class);
	
	@Autowired
	private ReviewService reviewService;
	

	@PostMapping // POST : ���� �Է�
	public ResponseEntity<Integer> createReview(@RequestBody ReviewVO vo) {
		// @RequestBody
		// - Ŭ���̾�Ʈ���� ���۹��� json �����͸�
		//	 �ڹ� ��ü�� ��ȯ���ִ� annotation
		logger.info("createReview() ȣ�� : vo = " + vo.toString());
		
		// ResponseEntity<T> : Rest ��Ŀ��� �����͸� ������ �� ���̴� ��ü
		// - ������ HttpStatus�� ����
		// - <T> : �������� �ϴ� ������ Ÿ��
		int result = 0;
		try {
			result = reviewService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end createReview()
	
	@GetMapping("/all/{hotelId}") // GET : ���� ����(all)
	public ResponseEntity<List<ReviewVO>> readReviews(
			@PathVariable("hotelId") int hotelId) {
			// @PathVariable("hotelId") : /all/{hotelId} ���� ������ ������ ����
		logger.info("readReviews() ȣ�� : hotelId = " + hotelId);
		List<ReviewVO> list = reviewService.read(hotelId);
		return new ResponseEntity<List<ReviewVO>>(list, HttpStatus.OK);
	} // end readReviews()
	
	@PutMapping("/{reviewId}") // PUT : ���� ����
	public ResponseEntity<Integer> updateReview(
			@PathVariable("reviewId") int reviewId,
			@RequestBody ReviewVO vo
			) {
		int result = reviewService.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end updateReview()
	
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<Integer> deleteReview(
			@PathVariable("reviewId") int reviewId,
			@RequestBody int hotelId) {
		logger.info("reviewId = " + hotelId);
		
		int result = 0;
		try {
			result = reviewService.delete(reviewId, hotelId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end deleteReview()
	
} // end ReviewRESTController