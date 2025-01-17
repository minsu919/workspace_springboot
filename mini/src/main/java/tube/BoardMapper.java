package tube;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

	List<MemberDTO> pagingList(int[] array);

	int totalCount();

	int insertBoard(BoardDTO dto);

	void updateViewCount(int seq);

	BoardDTO getDetail(int seq);

	int deleteBoard(int seq);

	int updateBoard(BoardDTO dto);

	ArrayList<BoardDTO> myboardlist(String writer);

	ArrayList<BoardDTO> searchResult(String searchquery);

}
