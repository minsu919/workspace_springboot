package tube;

import java.util.List;

public interface ViewdService {
	
    void insertViewd(ViewdDTO viewd);

    List<ViewdDTO> selectViewdById(String id);
}