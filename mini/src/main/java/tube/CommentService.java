package tube;

import java.util.List;

public interface CommentService {

    String addComment(CommentDTO comment);

    String modifyComment(CommentDTO comment);

    String removeComment(int seq, String writer);

    List<CommentDTO> getCommentsByBoardSeq(int boardseq);
}