package tube;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

    @Autowired
    @Qualifier("boardmapperservice")
    BoardService boardService;
    
    
    // 메인시작페이지
    @RequestMapping("/")
    public ModelAndView start
    (@RequestParam(defaultValue = "1" ,value = "pagenum", required = false) int pagenum,
    		HttpServletRequest request) {
    	
    	ModelAndView mv = new ModelAndView();
    	HttpSession session = request.getSession(false); // 세션이 없으면 새로 만들지 않음
	    if (session != null) {
	        String sessionid = (String) session.getAttribute("sessionid");
	        mv.addObject("sessionid", sessionid); // 세션ID를 넘겨줌
	    }
    	List<MemberDTO> boardlist = boardService.pagingList(pagenum);
    	int total = boardService.totalCount();
		mv.addObject("boardlist",boardlist);
		mv.addObject("total",total);
		System.out.println("total=" + total);
    	mv.setViewName("start");
    	return mv;
    }
    
    @GetMapping("/studio")
    public String studio() {
    	return "board/studio";
    }
    
    @PostMapping("/boardwrite")
	String writeprocess(BoardDTO dto) throws IOException{
		
		String savePath = "";
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
        	savePath = "c:/ezwel/miniupload/";        	
        } else { 
        	savePath = "/Users/minsu/Documents/ezwel/miniupload/";}
		String newfilename1 = null;
		MultipartFile file1 = dto.getPhoto();
		if(!file1.isEmpty()) {//f1해당파일선택했다면
			//이름랜덤문자열포함
			String originalfilename1 = file1.getOriginalFilename();
			String before1 = originalfilename1.substring(0, originalfilename1.indexOf("."));
			String ext1 = originalfilename1.substring(originalfilename1.indexOf("."));
			newfilename1 = before1 + "(" + UUID.randomUUID() + ")" + ext1;
			//서버내부 지정경로에 파일내용 저장
			file1.transferTo( new java.io.File(savePath +  newfilename1));
		}
		dto.setPhotofile(newfilename1); // 업로드한 파일을 서버 저장에 이름 -- db insert
		
		boardService.registerBoard(dto);
		return "board/studio";
	}
    
    @RequestMapping("/watch")
    public ModelAndView watchpage(){
    	ModelAndView mv = new ModelAndView();
    	return mv;
    }
    
    
}
