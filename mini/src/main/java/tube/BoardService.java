package tube;

import java.util.ArrayList;
import java.util.List;

public interface BoardService {

	int totalCount();

	List<MemberDTO> pagingList(int pagenum);

	void registerBoard(BoardDTO dto);

	BoardDTO updateViewcountAndGetDetail(int seq);

	int deleteBoard(int seq);

	int updateBoard(BoardDTO dto);

	ArrayList<BoardDTO> myBoardlist(String writer);

	ArrayList<BoardDTO> searchResult(String searchquery);
	


}