package boardmapper;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
		String savePath = "c:/ezwel/upload/";
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
}