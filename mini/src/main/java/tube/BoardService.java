package tube;

import java.util.List;

public interface BoardService {

	int totalCount();

	List<MemberDTO> pagingList(int pagenum);

	void registerBoard(BoardDTO dto);


}