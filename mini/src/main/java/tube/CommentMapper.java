package tube;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper {

    int insertComment(CommentDTO comment);


    int updateComment(CommentDTO comment);


    int deleteComment(int seq, String writer);

    
    List<CommentDTO> getCommentsByBoardSeq(int boardseq);

}
