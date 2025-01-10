package boardmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardMapperService implements BoardService{
	
	@Autowired
	BoardMapper mapper;
	
	@Override
	public String registerBoard(BoardDTO dto) {
		int result = mapper.insertBoard(dto);
		if (result == 1) return "글쓰기성공";
		else return "글쓰기실패";
	}

}
