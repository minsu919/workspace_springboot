package tube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		int cnt = 2;
		int start = (pagenum-1)*cnt + 1;
		int end = pagenum * cnt;
		int array[] = {start, end};
		return mapper.pagingList(array);
	}


}
