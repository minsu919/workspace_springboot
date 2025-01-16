package tube;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    int insertComment(CommentDTO comment);


    int updateComment(CommentDTO comment);


    int deleteComment(int seq, String writer);

    
    List<CommentDTO> getCommentsByBoardSeq(int boardseq);

}
