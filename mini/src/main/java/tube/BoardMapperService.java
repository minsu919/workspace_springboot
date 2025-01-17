package tube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tube.BoardDTO;

import java.util.ArrayList;
import java.util.List;

@Service("boardmapperservice")
public class BoardMapperService implements BoardService {
	
	@Override
	public void registerBoard(BoardDTO dto) {
		int result = mapper.insertBoard(dto);
	}

	@Autowired
	BoardMapper mapper;

    @Override
	public int totalCount() {
		return mapper.totalCount();
	}

	@Override
	public List<MemberDTO> pagingList(int pagenum) {
		int cnt = 4;
		int start = (pagenum-1)*cnt + 1;
		int end = pagenum * cnt;
		int array[] = {start, end};
		return mapper.pagingList(array);
	}

	@Override
	public BoardDTO updateViewcountAndGetDetail(int seq) {
		mapper.updateViewCount(seq);
		return mapper.getDetail(seq);
	}

	@Override
	public int deleteBoard(int seq) {
		return mapper.deleteBoard(seq);
	}

	@Override
	public int updateBoard(BoardDTO dto) {
		return mapper.updateBoard(dto);
	}

	@Override
	public ArrayList<BoardDTO> myBoardlist(String writer) {
		// TODO Auto-generated method stub
		return mapper.myboardlist(writer);
	}

	@Override
	public ArrayList<BoardDTO> searchResult(String searchquery) {
		return mapper.searchResult(searchquery);
	}


}
