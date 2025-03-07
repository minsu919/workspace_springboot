package annotation.springmvc.memberservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller 
public class MemberServiceController {
	//@Service("basicservice") , @Service("pointservice")생성
	
	@Autowired
	@Qualifier("basicservice") //자동 주입 
	MemberService basicservice;
	
	@Autowired
	@Qualifier("pointservice")
	MemberService pointservice;
	
	//form view- url매핑 메소드 리턴타입 String = view이름 . WEB-INF/VIEWS/*.jsp
	@GetMapping("/memberservice")
	public String memberservice() {
		System.out.println("memberservice 메소드 호출중");
		return "memberservice";
	}
	
	@PostMapping("/memberservice")
	//dto 내부 멤버변수들 이름 = 요청파라미터 이름 같으면 자동 주입
	public ModelAndView memberserviceresult(MemberDTO dto){
		ModelAndView mv = new ModelAndView();
		System.out.println(dto.getRegdate()); //2025-01-06T00:15
		//오늘날자-월 1, 6, 12 
		String month = dto.getRegdate().split("-")[1];
		String result = "";
		if(month.equals("01") || month.equals("06") || month.equals("12")) {
			result = pointservice.registerMember(dto);
		}
		else {
			result = basicservice.registerMember(dto);
		}
		
		mv.addObject("result", result);
		mv.setViewName("memberserviceresult");
		return mv;
		
	}
}
