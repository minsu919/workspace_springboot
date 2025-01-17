package tube;

public class ViewdDTO {
    String id;        
    int boardid;      
    java.util.Date viewtime; 

    public ViewdDTO() {}

    public ViewdDTO(String id, int boardid, java.util.Date viewtime) {
        this.id = id;
        this.boardid = boardid;
        this.viewtime = viewtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

    public java.util.Date getViewtime() {
        return viewtime;
    }

    public void setViewtime(java.util.Date viewtime) {
        this.viewtime = viewtime;
    }
}