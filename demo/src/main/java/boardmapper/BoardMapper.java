package boardmapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {
	int insertBoard(BoardDTO dto);
}
