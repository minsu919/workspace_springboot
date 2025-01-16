package tube;
//
import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
    int seq;
    String title; // 제목
    String boardtext; // 내용
    String writer; // 작성자
    String writingtime;
    String photofile;
    int viewcount;
    MultipartFile photo;

    public BoardDTO () {}

	public BoardDTO (int seq, String title, String boardtext, String writer, String writingtime, String photofile, int viewcount) {
		this.seq = seq;
		this.title = title;
		this.boardtext = boardtext;
		this.writer = writer;
		this.writingtime = writingtime;
		this.photofile = photofile;
		this.viewcount = viewcount;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBoardtext() {
		return boardtext;
	}

	public void setBoardtext(String boardtext) {
		this.boardtext = boardtext;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWritingtime() {
		return writingtime;
	}

	public void setWritingtime(String writingtime) {
		this.writingtime = writingtime;
	}

	public String getPhotofile() {
		return photofile;
	}

	public void setPhotofile(String photofile) {
		this.photofile = photofile;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	
    
}


