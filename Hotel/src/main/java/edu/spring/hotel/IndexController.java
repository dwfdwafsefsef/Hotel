package edu.spring.hotel;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.hotel.domain.HotelVO;
import edu.spring.hotel.domain.RoomVO;
import edu.spring.hotel.pageutil.PageCriteria;
import edu.spring.hotel.pageutil.PageMaker;
import edu.spring.hotel.service.HotelService;
import edu.spring.hotel.service.RoomService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
	private static final Logger logger = 
			LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private HotelService hotelService;
	// ?
	@Autowired
	private RoomService roomService;

	@GetMapping
	public String indexGET(Model model, Integer page, Integer numsPerPage, String sortBy, String keyword,
			RedirectAttributes reAttr) {
		logger.info("indexGET() �샇異�");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		logger.info("�젙�젹 諛⑹떇 : " + sortBy);
		logger.info(keyword);
		// �럹�씠吏� 泥섎━
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
//s
		List<HotelVO> list = null;
		PageMaker pageMaker = new PageMaker();
		
		if (sortBy == null || sortBy.equals("")) {
			list = hotelService.read(criteria);
			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(hotelService.getTotalCounts());
			pageMaker.setPageData();
			model.addAttribute("list", list);
		} else if (sortBy.equals("hotelName")) {
			list = hotelService.readOrderByHotelNameAsc(criteria);
			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(hotelService.getTotalCounts());
			pageMaker.setPageData();
			model.addAttribute("list", list);
		} else if (sortBy.equals("hotelReviewCnt")) {
			list = hotelService.readOrderByReviewDesc(criteria);
			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(hotelService.getTotalCounts());
			pageMaker.setPageData();
			model.addAttribute("list", list);
		} else if (sortBy.equals("hotelReviewAvg")) {
			list = hotelService.readOrderByReviewAvgDesc(criteria);
			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(hotelService.getTotalCounts());
			pageMaker.setPageData();
			model.addAttribute("list", list);
		}
		
		// keyword 寃��깋 �궎�썙�뱶媛� �엳�쑝硫�
		if (keyword != null) {
			list = hotelService.readByHotelName(keyword, criteria);
			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(hotelService.getTotalCountsByHotelName(keyword));
			pageMaker.setPageData();
			model.addAttribute("list", list);

			// keyword 媛믪씠 �뾾�쑝硫�(寃��깋寃곌낵媛� �뾾�쑝硫�)
			if (list.isEmpty()) {
				reAttr.addFlashAttribute("result", "searchFail");
				return "redirect:/";
			} 
		}
		
		model.addAttribute("sortBy", sortBy); // �젙�젹蹂� �닚�꽌�븣 �럹�씠吏� 泥섎━媛� �젣 湲곕뒫�쓣 �븯�젮硫� 荑쇰━�뒪�듃留곸쓣 �쑀吏��빐二쇱뼱�빞 �븿
		model.addAttribute("pageMaker", pageMaker);
		return "index";
	} // end indexGET()

	@GetMapping("/detail")
	public void detailGET(Model model, Integer hotelId, Integer page) {
		logger.info("detailGET() �샇異� : hotelId = " + hotelId);
		HotelVO hvo = hotelService.read(hotelId);
		List<RoomVO> list = roomService.read(hotelId);
		model.addAttribute("list", list);
		model.addAttribute("hvo", hvo);
		model.addAttribute("page", page);
	} // end detailGET()

	@GetMapping("/login")
	public String loginGET() {
		logger.info("loginGET() �샇異�");
		return "redirect:/member/login";
	} // end loginGET()

} // end IndexController
