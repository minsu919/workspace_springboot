package annotation.springmvc.memberservice;

public interface MemberService {
	public abstract void registerMember();
	//메소드 overloading:2개이상 메소드. 이름 같지만 매개변수 다르게 다르게 정의
	public abstract String registerMember(MemberDTO dto);
}
