package annotation.springmvc.ajax;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import annotation.springmvc.memberservice.MemberDTO;

@Controller
public class AjaxAdminController {
	ArrayList<MemberDTO> list = new ArrayList<MemberDTO>(); 
			 
			@RequestMapping("/ajaxadminstart") 
			public String start(){ 
			return "ajax/admin"; //view
			} 
			@RequestMapping(value="/ajaxadminlist", produces = {"application/json;charset=utf-8"}) 
			public @ResponseBody ArrayList<MemberDTO> ajaxlist(String id, int pw) { 
			if(id.equals("admin") && pw == 1111) { 
				if(list.size() == 0) {
				for(int i = 1; i <= 10; i++ ) { 
					MemberDTO dto = new MemberDTO(); 
					dto.setId("ajax" + i);  
					dto.setPw(i * 1000);  
					dto.setName("홍길동" + i); 
					dto.setPhone("010-1234-456" + i); 
					dto.setEmail("hong" + i + "@gil.dong"); 
					dto.setRegdate("2025-01-07"); 
					list.add(dto); 
				}
				}
			} 
			return list; 
			} 
			@ResponseBody 
			@RequestMapping("/getpw/{id}") 
			public String getpw(@PathVariable("id") String id ){ 
				for(MemberDTO dto : list) {
					if(dto.getId().equals(id)) {
						String pw = String.valueOf(dto.getPw()); //int 를 String 
						pw = pw.substring(0,2) + "*".repeat(pw.length() - 2);
						return "{\"pw\":\"" + pw +  "\"}";
					}
				}//for end
				return "{\"pw\":\"암호 모름\"}";
			}
			
}

			
			
			
			
			
			