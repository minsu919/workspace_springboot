package tube;

import java.util.List;

public interface MemberService {

	MemberDTO getMember(String id);
	
    String registerMember(MemberDTO dto) ;
    
    String updateMember(MemberDTO dto);

	List<MemberDTO> getAllMembers();

	boolean isIdAvailable(String id);

	void deleteMember(String id);
}
