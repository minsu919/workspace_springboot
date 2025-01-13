package boardmapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BoardMybatisController {
	
	@Autowired
	@Qualifier("boardMapperService")
	BoardService boardService;
	
	@RequestMapping("/")
	String start() {
		return "board/start";
	}
	
	@GetMapping("/boardwrite")
	String writeform() {
		return "board/writeform";
	}
	
	@PostMapping("/boardwrite")
	String writeprocess(BoardDTO dto) throws IOException{
		
		//1 multifile1 c:ezwel/upload 파일 저장
		//2 파일 이름만 db에 저장
		String savePath = "";
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
        	savePath = "c:/ezwel/upload/";        	
        } else { 
        	savePath = "/Users/minsu/Documents/ezwel/upload/";}
		String newfilename1 = null;
		MultipartFile file1 = dto.getMultifile1();
		if(!file1.isEmpty()) {//f1해당파일선택했다면
			//이름랜덤문자열포함
			String originalfilename1 = file1.getOriginalFilename();
			String before1 = originalfilename1.substring(0, originalfilename1.indexOf("."));
			String ext1 = originalfilename1.substring(originalfilename1.indexOf("."));
			newfilename1 = before1 + "(" + UUID.randomUUID() + ")" + ext1;
			//서버내부 지정경로에 파일내용 저장
			file1.transferTo( new java.io.File(savePath +  newfilename1));
		}
		dto.setFile1(newfilename1); // 업로드한 파일을 서버 저장에 이름 -- db insert
		
		boardService.registerBoard(dto);
		return "board/start";
	}
	
	@RequestMapping("/boardlist")
	ModelAndView boardlist
	(@RequestParam(defaultValue = "1" ,value = "pagenum", required = false) int pagenum) {
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> list = boardService.pagingList(pagenum);
		int total = boardService.totalCount();
		mv.addObject("boardlist",list);
		mv.addObject("total",total);
		mv.setViewName("board/list");
		return mv;
	}
	//@RequestParam(value = "seq", required = true)
	@RequestMapping("/boarddetail")
	ModelAndView boarddetail(int seq) {
		ModelAndView mv = new ModelAndView();
		BoardDTO dto = boardService.updateViewcountAndGetDetail(seq);
		mv.addObject("detailboard",dto);
		mv.setViewName("board/boarddetail");
		return mv;
	}
	
	@GetMapping("/boarddelete")
	@ResponseBody
	String boarddelete(int seq) {
		
		int rows = boardService.deleteBoard(seq);
		if (rows == 1) {return "{\"result\" : \"삭제성공\"}";}
		else {return "{\"result\" : \"삭제실패\"}";}
	}
	
	@GetMapping("/boardupdate")
	@ResponseBody
	String boardupdate(BoardDTO dto) {
		int rows = boardService.updateBoard(dto);
		if (rows == 1) {return "{\"result\" : \"수정성공\"}";}
		else {return "{\"result\" : \"수정실패\"}";}
	}
	
	@GetMapping("/boarddownload")
	public void boarddownload(String filename, HttpServletResponse response) throws IOException {
        String osName = System.getProperty("os.name").toLowerCase();
        String path = "";
		if (osName.contains("win")) {
        	path = "c:/ezwel/upload/";        	
        } else { 
        	path = "/Users/minsu/Documents/ezwel/upload/";}
		FileInputStream fin = new FileInputStream(new File( path + filename));
		filename = new String(filename.getBytes("utf-8") , "iso-8859-1" );

		response.setHeader("Content-Disposition", "attachment;filename=\"" +filename + "\"");

		OutputStream out = response.getOutputStream();
		FileCopyUtils.copy(fin,  out);
		fin.close();
		out.close();
	}

}