package tube;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	@Qualifier("membermapperservice")
	MemberService memberService;
	
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
	    mv.addObject("member", member);
	    
	    mv.setViewName("/feed/you");
	    
	    return mv;
	}
	
	//회원정보수정
	@GetMapping("/modify")
	public ModelAndView modifyForm(HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    
	    HttpSession session = request.getSession();
	    String sessionid = (String) session.getAttribute("sessionid");
	    Boolean pwChecked = (Boolean) session.getAttribute("pwChecked");
	    
	    if (sessionid == null) {
	        mv.setViewName("redirect:/login");
	        return mv;
	    }
	    
	    if (pwChecked == null || !pwChecked) {
	        mv.setViewName("redirect:/mypage");
	        return mv;
	    }
	    
	    MemberDTO member = memberService.getMember(sessionid);
	    mv.addObject("member", member);
	    mv.setViewName("modify");
	    
	    return mv;
	}
	
    // 비밀번호 재확인 처리
	@GetMapping("/modify/pwcheck")
	public String showPwCheck() {
	    return "pwcheck";  // 비밀번호 확인 화면 jsp 또는 템플릿 반환
	}
	
	@PostMapping("/modify/pwcheck")
	public ModelAndView checkPw(String pw, HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    
	    HttpSession session = request.getSession();
	    String sessionid = (String) session.getAttribute("sessionid");

	    if (sessionid == null) {
	        mv.setViewName("redirect:/login");
	        return mv;
	    }
	    
	    System.out.println(sessionid);
	    MemberDTO member = memberService.getMember(sessionid);
	    
	    if (member != null && member.getPw().equals(pw)) {
	    	mv.addObject("member",member);
	        session.setAttribute("pwchecked", true);
	        mv.setViewName("modify");
	    } else {
	    	System.out.println("뭐지?");
	        mv.setViewName("redirect:/modify/pwcheck");
	    }

	    return mv;
	}
	
	//계정 삭제
	@RequestMapping(value = "/deleteMember", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deleteMember(@RequestParam("id") String id, HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    String referer = request.getHeader("Referer");
	    
	    memberService.deleteMember(id);
	    
	    HttpSession session = request.getSession();
	    session.removeAttribute("id");
	    
	    if (referer != null && referer.contains("localhost:9090/admin")) {
	        mv.setViewName("redirect:" + referer);
	    } else {
	    	session.removeAttribute("sessionid");
	        mv.setViewName("redirect:/");
	    }
	    
	    return mv;
	}
	
	//관리자 - 계정 삭제
	@GetMapping("/admin/deleteMember")
	public ModelAndView deleteMember(@RequestParam("id") String id) {
	    ModelAndView mv = new ModelAndView();
	    memberService.deleteMember(id);
	    return mv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modify(HttpServletRequest request, String id, String pw, MemberDTO updateMember) {
		ModelAndView mv = new ModelAndView();
		MemberDTO dto = memberService.getMember(id);

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
	public ModelAndView adminpage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false); 
		    
		if (session != null) {
		    String sessionid = (String) session.getAttribute("sessionid");
		    mv.addObject("sessionid", sessionid);
		}

		List<MemberDTO> allMembers = memberService.getAllMembers();   
		mv.addObject("allMembers", allMembers);
		    
		mv.setViewName("/admin/adminpage"); 
		    
		return mv;
	}
}