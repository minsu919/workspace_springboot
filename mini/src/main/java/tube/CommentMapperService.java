package tube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentmapperservice")
public class CommentMapperService implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    void test() {
    	System.out.println("===>"+commentMapper);
    }
    @Override
    public String addComment(CommentDTO comment) {
        int result = commentMapper.insertComment(comment);
        return result > 0 ? "success" : "failure";
    }

    @Override
    public String modifyComment(CommentDTO comment) {
        int result = commentMapper.updateComment(comment);
        return result > 0 ? "success" : "failure";
    }

    @Override
    public String removeComment(int seq, String writer) {
        int result = commentMapper.deleteComment(seq, writer);
        return result > 0 ? "success" : "failure";
    }

    @Override
    public List<CommentDTO> getCommentsByBoardSeq(int boardseq) {
        return commentMapper.getCommentsByBoardSeq(boardseq);
    }
}