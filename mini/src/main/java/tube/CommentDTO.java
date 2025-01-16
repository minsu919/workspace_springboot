package tube;

public class CommentDTO {
    int seq;          // 댓글 번호
    int boardseq;     // 게시글 번호
    String writer;    // 작성자
    String contents;  // 댓글 내용
    String regdate;     // 작성 날짜
    
    
    public CommentDTO() {}

	public CommentDTO(int seq, int boardseq, String writer, String contents, String regdate) {
		this.seq = seq;
		this.boardseq = boardseq;
		this.writer = writer;
		this.contents = contents;
		this.regdate = regdate;
	}

	public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getBoardseq() {
        return boardseq;
    }

    public void setBoardseq(int boardseq) {
        this.boardseq = boardseq;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
}
