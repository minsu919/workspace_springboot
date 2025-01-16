package tube;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	@Qualifier("membermapperservice")
	MemberService memberService;
	
	
//	@GetMapping("/")
//	public ModelAndView mainForm(HttpServletRequest request) {
//	    ModelAndView mv = new ModelAndView();
//	    HttpSession session = request.getSession(false); // 세션이 없으면 새로 만들지 않음
//	    if (session != null) {
//	        String sessionid = (String) session.getAttribute("sessionid");
//	        mv.addObject("sessionid", sessionid); // 세션ID를 넘겨줌
//	    }
//	    mv.setViewName("start");
//	    return mv;
//	}
	
	//회원가입
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; 
    }

    @PostMapping("/register")
    public String register(MemberDTO dto) {
        String result = memberService.registerMember(dto);
        System.out.println(result);
        if (result.equals("success")) {
        	return "redirect:login?registerresult=" + result;
        } else {
        	return "redirect:/register?registerresult=" + result;
        }
    }
    
    //아이디 중복 확인
    @GetMapping("/checkId")
    @ResponseBody
    public String checkDuplicatedId(@RequestParam("id") String id) {
        boolean isAvailable = memberService.isIdAvailable(id);
        return isAvailable ? "사용 가능한 아이디입니다." : "중복된 아이디입니다."; 
    }
    
	//로그인
	@GetMapping("/login")
	public ModelAndView login(String id, String pw, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("sessionid") != null) {
			mv.setViewName("redirect:/?result=exist");
		} else {
			mv.setViewName("login");
		}
		
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView loginform(String id, String pw, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		MemberDTO dto = memberService.getMember(id);
		if (dto != null && dto.getPw().equals(pw)) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionid", id);
			mv.addObject("member",dto);
			mv.setViewName("redirect:/");
		} else {
			mv.setViewName("redirect:/login?result=wrong");
		}
		return mv;
	}
	
	//내정보
	@GetMapping("/mypage")
	public ModelAndView myPageForm(HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    
	    HttpSession session = request.getSession();
	    String sessionid = (String) session.getAttribute("sessionid");
	    
	    if (sessionid == null) {
	        mv.setViewName("redirect:/login");
	        return mv;
	    }
	    
	    MemberDTO member = memberService.getMember(sessionid);
	    
	    mv.addObject("sessionid", sessionid);
	    mv.addObject("member", member);  // member 객체도 전달
	    
	    mv.setViewName("/feed/you");
	    
	    return mv;
	}
	
	//회원정보수정
	@GetMapping("/modify")
	public ModelAndView modifyForm(HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    
	    HttpSession session = request.getSession();
	    String sessionid = (String) session.getAttribute("sessionid");
	    
	    if (sessionid == null) {
	        mv.setViewName("redirect:/login");
	        return mv;
	    }
	    
	    MemberDTO member = memberService.getMember(sessionid);
	    
	    mv.addObject("member", member);
	    mv.setViewName("modify");
	    
	    return mv;
	}
	
	//계정 삭제
	@PostMapping("/deleteMember")
	public String deleteMember(@RequestParam("id") String id, Model model) {
		memberService.deleteMember(id);
		model.addAttribute("message", id + "계정 삭제 완료");
		
		return "redirect:/";
	}
	
	@PostMapping("/modify")
	public ModelAndView modify(HttpServletRequest request, String id, String pw, MemberDTO updateMember) {
		ModelAndView mv = new ModelAndView();
		MemberDTO dto = memberService.getMember(id);
		
			//HttpSession session = request.getSession();
			//session.setAttribute("sessionid", id);

			dto.setPw(updateMember.getPw());
			dto.setPhone(updateMember.getPhone());
			
			memberService.updateMember(dto);
			
			mv.addObject("member", dto);
		
		return mv;
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("sessionid");
		return "redirect:/";
	}
	
	//관리자 - 회원관리
	@GetMapping("/admin")
	public String adminPage() {
		return "/admin/adminpage";
	}
	
	@PostMapping("/admin")
	public String adminUserManage(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession();
	    String sessionid = (String) session.getAttribute("sessionid");
	    
	    if (sessionid == null) {
	        return "redirect:/login"; 
	    }
	    
	    MemberDTO member = memberService.getMember(sessionid);
	    if (member != null && member.getIsadmin() == 1) {
	    	List<MemberDTO> allMembers = memberService.getAllMembers();
	    	model.addAttribute("allMembers", allMembers);
	        //model.addAttribute("allPosts", boardService.getAllPosts());
	        return "/admin";
	    }
	    
	    return "redirect:/"; 
	}
}