package errors;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyWebErrorController implements ErrorController {// 오버라이딩할 메소드 없다
	
	@RequestMapping("/error")
	String handleError(HttpServletRequest request) throws UnsupportedEncodingException {
		//다른 컨트롤러에서 오류 발생 시 현재 컨트롤러로 자동 forward
		// 서버콘솔 출력 - 개발자용
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		System.out.println("오류코드 = " + status);
		System.out.println("오류메시지 = " + request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
		System.out.println("오류파일 = " + request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
		System.out.println("파라미터정보 = " + request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING));
		if (request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING) != null) {			
			URLDecoder.decode((String)request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING), "utf-8");
		}
		System.err.println("요청방식 = " + request.getMethod());
		//http 프로토콜 파라미터 한글 - %16진수 2자리...인코딩 규칙
		
		// 400.jsp , 404, 405, 500
		return "errors/" + status.toString(); // 브라우저 출력 - 사용자
	}
}
