package edu.spring.hotel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.hotel.domain.MemberVO;
import edu.spring.hotel.service.MemberService;

@Controller
@ControllerAdvice
@RequestMapping(value = "member") // url : /hotel/member���� �س����� ���� member ������ �������
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	

	// ȸ������ �޼ҵ�
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() ȣ��");
	} // end registerGET()

	// ȸ������ �޼ҵ�
	@PostMapping("/register")
	public String registerPOST(MemberVO vo, RedirectAttributes reAttr) {
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());
		int result = 0; 
		try {
			result = memberService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(result + "�� ����");
		if (result == 1) {
			reAttr.addFlashAttribute("result", "registerSuccess");
			return "redirect:/";
		} else {
			return "redirect:/member/register";
		}
	} // end registerPOST()

	@GetMapping("/idCheck")
	public void idCheckGET() {

	}// end idcheckGET()

	@ResponseBody
	@PostMapping("/idCheck")
	public int idcheckPOST(MemberVO vo) {
		logger.info("idcheckPOST() ȣ��");
		logger.info(vo.toString());

		int result = memberService.idCheck(vo);

		return result;

	} // end idcheckPOST() ���̵� �ߺ�Ȯ��
	
	@GetMapping("/phoneCheck")
	public void phoneCheckGET() {
		
	} // end phonecheckGET()
	
	@ResponseBody
	@PostMapping("/phoneCheck")
	public int phonecheckPOST(MemberVO vo) throws Exception {
		logger.info("phoneCheckPOST()ȣ��");
		logger.info(vo.toString());
		
		int result = memberService.phoneCheck(vo);
		return result;
	} // end phonecheckPOST()�޴��ȣ �ߺ�Ȯ�� 

	// �α��� �޼ҵ�
	@GetMapping("/login")
	public String loginGET() {
		logger.info("loginGET() ȣ��");
		return "member/login";
	} // end loginGET()

	// �α��� �޼ҵ�
	@PostMapping("/login")
	public String loginPOST(MemberVO vo, RedirectAttributes reAttr, HttpSession session) throws Exception {
		logger.info("loginPOST() ȣ��");

		vo = memberService.login(vo);

		String targetURI = (String) session.getAttribute("targetURI");
		logger.info(targetURI);

		if (vo != null) {
			session.setAttribute("memberId", vo.getMemberId());
			reAttr.addFlashAttribute("result", "loginSuccess");
			if (targetURI != null) {
				session.removeAttribute("targetURI");
				return "redirect:" + targetURI;
			} else {
				return "redirect:/";
			}
		} else {
			reAttr.addFlashAttribute("result", "loginFail");
			return "redirect:/member/login";
		}
	} // end loginPOST()

	// �α׾ƿ� �޼ҵ�
	@GetMapping("/logout")
	public String logoutGET(HttpSession session, RedirectAttributes reAttr) {
		logger.info("logoutGET() ȣ��");
		session.removeAttribute("memberId");
		reAttr.addFlashAttribute("result", "logoutSuccess");
		return "redirect:/";
	} // end logoutGET()

	@GetMapping("/update")
	public void updateGET(HttpSession session, Model model) {
		logger.info("updateGET() ȣ��");
		String memberId = (String) session.getAttribute("memberId");
		logger.info(memberId);
		MemberVO vo = memberService.read(memberId);
		model.addAttribute("vo", vo);
	} // end updateGET()

	@PostMapping("/update")
	public String updatePOST(MemberVO vo, RedirectAttributes reAttr) {
		logger.info("updatePOST() ȣ��");
		int result = memberService.update(vo);
		if (result == 1) {
			logger.info(result + "�� ����");
			reAttr.addFlashAttribute("result", "updateSuccess");
			return "redirect:/member/mypage";
		} else {
			return "redirect:/member/update";
		}
	} // end updatePOST()

	@GetMapping("/delete")
	public void deleteGET() {
		logger.info("deleteGET() ȣ��");
	} // end deleteGET()

	@PostMapping("/delete")
	public String deletePOST(MemberVO vo, RedirectAttributes reAttr, HttpSession session) {
		logger.info("deletePOST() ȣ��");
		int result = memberService.delete(vo);
		if (result == 1) {
			logger.info(result + "�� ����");
			reAttr.addFlashAttribute("result", "deleteSuccess");
			session.removeAttribute("memberId");
			return "redirect:/";
		} else {
			reAttr.addFlashAttribute("result", "deleteFail");
			return "redirect:/member/delete";
		}
	} // end deletePOST()

	@GetMapping("/find-memberid")
	public void findMemberId() {
		logger.info("findMemberId() ȣ��");
	} // end findMemberId()

	@GetMapping("/find-memberpw")
	public void findMemberPw() {
		logger.info("findMemberPw() ȣ��");
	} // end findMemberPw()

	// ���������� �޼ҵ�
	@GetMapping("/mypage")
	public void myPageGET() {
		logger.info("myPageGET() ȣ��");
	} // end myPage()

} // end MemberController
