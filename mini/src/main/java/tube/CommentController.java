package tube;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {

    @Autowired
    @Qualifier("commentmapperservice")
    private CommentService commentService;

    // 댓글 추가
    @PostMapping("/comments/add")
    @ResponseBody
    public String addComment(@RequestBody CommentDTO comment) {
        String result = commentService.addComment(comment);
        if ("success".equals(result)) {
            return "{\"message\": \"댓글이 업로드되었습니다.\", \"status\": \"success\"}";
        } else {
            return "{\"message\": \"댓글 업로드가 실패하였습니다.\", \"status\": \"fail\"}";
        }
    }

    // 댓글 수정
    @PostMapping("/comments/update")
    @ResponseBody
    public String updateComment(@RequestBody CommentDTO comment) {
        String result = commentService.modifyComment(comment);
        if ("success".equals(result)) {
            return "{\"message\": \"댓글이 수정되었습니다.\", \"status\": \"success\"}";
        } else {
            return "{\"message\": \"댓글 수정이 실패하였습니다.\", \"status\": \"fail\"}";
        }
    }

    // 댓글 삭제
    @PostMapping("/comments/delete")
    @ResponseBody
    public String deleteComment(@RequestParam("seq") int seq, @RequestParam("writer") String writer) {
        String result = commentService.removeComment(seq, writer);
        if ("success".equals(result)) {
            return "{\"message\": \"댓글이 삭제되었습니다.\", \"status\": \"success\"}";
        } else {
            return "{\"message\": \"댓글 삭제가 실패하였습니다.\", \"status\": \"fail\"}";
        }
    }

    // 게시물 상세 보기와 댓글 목록
    @GetMapping("/watch/{boardseq}")
    public String watchBoardWithComments(@PathVariable("boardseq") int boardseq, Model model) {
    	System.out.println("===>"+((CommentMapperService)commentService));
    	((CommentMapperService)commentService).test();
        List<CommentDTO> comments = commentService.getCommentsByBoardSeq(boardseq);
        model.addAttribute("comments", comments);
        model.addAttribute("boardseq", boardseq);
        System.out.println("===>"+comments.size());
        return "board/boarddetail"; // boarddetail.jsp와 매핑
    }
}




