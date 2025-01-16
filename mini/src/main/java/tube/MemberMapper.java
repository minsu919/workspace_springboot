package tube;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {

	MemberDTO getMember(String id);
	
	int insertMember(MemberDTO dto);
	
	int updateMember(MemberDTO dto);

	int checkId(String id);

	void deleteMember(String id);

	List<MemberDTO> getAllMembers();
}
