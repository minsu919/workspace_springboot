package tube;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

	List<MemberDTO> pagingList(int[] array);

	int totalCount();

	int insertBoard(BoardDTO dto);

}
