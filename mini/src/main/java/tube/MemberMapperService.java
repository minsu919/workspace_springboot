package tube;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("membermapperservice")
public class MemberMapperService implements MemberService {

	@Autowired
	MemberMapper mapper;

	@Override
	public MemberDTO getMember(String id) {
		return mapper.getMember(id);
	}
	
	@Override
	@Transactional
    public String registerMember(MemberDTO dto) {
        int result = mapper.insertMember(dto);
        return result == 1 ? "success" : "fail";
    }
	
	@Override
	@Transactional
	public String updateMember(MemberDTO dto) {
		mapper.updateMember(dto);
		return "success";
	}

	//아이디 중복 확인
	@Override
	public boolean isIdAvailable(String id) {
		return mapper.checkId(id) == 0;
	}
	
	//계정 삭제
	@Override
	public void deleteMember(String id) {
		mapper.deleteMember(id);
	}
	
	//관리자 - 회원 관리
	@Override
	public List<MemberDTO> getAllMembers() {
		return mapper.getAllMembers();
	}
	
}
