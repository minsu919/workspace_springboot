package tube;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

    @Autowired
    @Qualifier("boardmapperservice")
    BoardService boardService;
    
    @Autowired
    @Qualifier("membermapperservice")
    MemberService memberService;
    
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
		return "redirect:/mypage";
	}
    
    @RequestMapping("/watch")
	ModelAndView boarddetail(int seq, HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView();
		BoardDTO dto = boardService.updateViewcountAndGetDetail(seq);
		mv.addObject("detailboard",dto);
		//관리자 삭제 기능 추가를 위해 member 불러왔습니다
		HttpSession session = request.getSession();
	    String sessionid = (String) session.getAttribute("sessionid");
	    
	    if (sessionid == null) {
	        sessionid = "";  
	    }
	    
	    mv.addObject("sessionid", sessionid);
	    
		MemberDTO member = memberService.getMember(sessionid);
	    mv.addObject("member", member);
		mv.setViewName("board/boarddetail");
		return mv;

    }
    
    @GetMapping("/imgdownload")
    public void showImg(String filename, HttpServletResponse response) throws IOException {
        String osName = System.getProperty("os.name").toLowerCase();
        String path = "";
        
        // 운영체제에 맞는 파일 경로 설정
        if (osName.contains("win")) {
            path = "c:/ezwel/miniupload/";
        } else { 
            path = "/Users/minsu/Documents/ezwel/miniupload/";
        }
        
        // 파일을 열기
        File file = new File(path + filename);
        FileInputStream fin = new FileInputStream(file);
        
        // 파일 이름 인코딩 처리
        filename = new String(filename.getBytes("utf-8"), "iso-8859-1");

        // 파일의 MIME 타입을 설정 (이미지일 경우)
        String guessedType = URLConnection.guessContentTypeFromName(filename);
        if (guessedType == null) {
        	guessedType = "application/octet-stream"; // MIME 타입이 없다면 기본 값 설정
        }
        // HTTP 응답 헤더 설정
        response.setContentType(guessedType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        
        // 파일 데이터를 클라이언트에게 전송
        OutputStream out = response.getOutputStream();
        FileCopyUtils.copy(fin, out);
        
        // 스트림 닫기
        fin.close();
        out.close();
    }
    
	@GetMapping("/boarddelete")
	@ResponseBody
	String boarddelete(int seq) {
		
		int rows = boardService.deleteBoard(seq);
		if (rows == 1) {return "{\"result\" : \"삭제성공\"}";}
		else {return "{\"result\" : \"삭제실패\"}";}
	}
	
    @GetMapping("/results")
	ModelAndView searchResult(String searchquery) {
    	ModelAndView mv = new ModelAndView();
		ArrayList<BoardDTO> list = boardService.searchResult(searchquery);
		mv.addObject("boardlist",list);
		mv.setViewName("board/searchresult");
		return mv;
    }
	
	@GetMapping("/boardupdate")
	@ResponseBody
	String boardupdate(BoardDTO dto) {
		int rows = boardService.updateBoard(dto);
		if (rows == 1) {return "{\"result\" : \"수정성공\"}";}
		else {return "{\"result\" : \"수정실패\"}";}
	}
    
	
	@RequestMapping(value="/myboardlist", produces = {"application/json;charset=utf-8"})
	@ResponseBody
	ArrayList<BoardDTO> myBoardlist(String writer) {
		ArrayList<BoardDTO> list = boardService.myBoardlist(writer);
		
		return list;
	}

}
