package tube;

public class MemberDTO {
	String id, pw, name, phone, regdate;
	int isadmin; // 0 : user 1 : admin
	
	public MemberDTO() {}
	
	public MemberDTO(String id, String pw, String name, String phone, String regdate, int isadmin) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.regdate = regdate;
		this.isadmin = isadmin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}

	
}
