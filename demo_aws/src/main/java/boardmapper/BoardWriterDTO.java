package boardmapper;

import org.springframework.web.multipart.MultipartFile;

public class BoardWriterDTO {
	
	int seq, viewcount, pw;
	String file1 ,title, contents, writingtime;
	MultipartFile multifile1;
	MemberDTO writerdto;
	
	
	
	public BoardWriterDTO() {
	
	}
	public BoardWriterDTO(int seq, int viewcount, int pw, String file1, String title, String contents,
			String writingtime, MultipartFile multifile1, MemberDTO writerdto) {
		super();
		this.seq = seq;
		this.viewcount = viewcount;
		this.pw = pw;
		this.file1 = file1;
		this.title = title;
		this.contents = contents;
		this.writingtime = writingtime;
		this.multifile1 = multifile1;
		this.writerdto = writerdto;
	}
	public MemberDTO getWriterdto() {
		return writerdto;
	}
	public void setWriterdto(MemberDTO writerdto) {
		this.writerdto = writerdto;
	}
	public MultipartFile getMultifile1() {
		return multifile1;
	}
	public void setMultifile1(MultipartFile multifile1) {
		this.multifile1 = multifile1;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public int getPw() {
		return pw;
	}
	public void setPw(int pw) {
		this.pw = pw;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWritingtime() {
		return writingtime;
	}
	public void setWritingtime(String writingtime) {
		this.writingtime = writingtime;
	}
	@Override
	public String toString() {
		return "BoardWriterDTO [seq=" + seq + ", viewcount=" + viewcount + ", pw=" + pw + ", file1=" + file1
				+ ", title=" + title + ", contents=" + contents + ", writingtime=" + writingtime + ", multifile1="
				+ multifile1 + ", writerdto=" + writerdto + "]";
	}
	
}
