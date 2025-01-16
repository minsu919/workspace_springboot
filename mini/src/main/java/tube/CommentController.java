package tube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    @Qualifier("commentmapperservice")
    private CommentService commentService;

    @GetMapping("/{boardSeq}")
    public ModelAndView getComments(@PathVariable int boardSeq, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("sessionid");

        ModelAndView mv = new ModelAndView();
        List<CommentDTO> comments = commentService.getComments(boardSeq);
        mv.addObject("comments", comments);
        mv.addObject("boardSeq", boardSeq);
        mv.addObject("userId", userId);
        mv.setViewName("comments");
        return mv;
    }

    // 댓글 추가
    @PostMapping("/add")
    public ModelAndView addComment(CommentDTO dto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("sessionid");

        ModelAndView mv = new ModelAndView();

        if (userId != null) {
        	System.out.println(userId);
            dto.setWriter(userId);
            String result = commentService.addComment(dto);

            if ("success".equals(result)) {
                mv.setViewName("redirect:/comments/" + dto.getBoardseq());
            } else {
                mv.setViewName("redirect:/comments?result=wrong");
            }
        } else {
            mv.setViewName("redirect:/login"); //로그인하지 않은 사용자는 로그인 페이지로 이동
        }

        return mv;
    }

    // 댓글 수정
    @PostMapping("/update")
    public ModelAndView updateComment(CommentDTO comment, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        ModelAndView mv = new ModelAndView();

        if (userId != null && userId.equals(comment.getWriter())) {
            String result = commentService.modifyComment(comment);

            if ("success".equals(result)) {
                mv.setViewName("redirect:/comments/" + comment.getBoardseq());
            } else {
                mv.setViewName("redirect:/comments?result=wrong");
            }
        } else {
            mv.setViewName("redirect:/comments?result=unauthorized");
        }

        return mv;
    }

    // 댓글 삭제
    @PostMapping("/delete")
    public ModelAndView deleteComment(@RequestParam int seq, @RequestParam String writer,
                                      @RequestParam int boardSeq, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        ModelAndView mv = new ModelAndView();

        if (userId != null && userId.equals(writer)) {
            String result = commentService.removeComment(seq, writer);

            if ("success".equals(result)) {
                mv.setViewName("redirect:/comments/" + boardSeq);
            } else {
                mv.setViewName("redirect:/comments?result=wrong");
            }
        } else {
            mv.setViewName("redirect:/comments?result=unauthorized");
        }

        return mv;
    }
}


