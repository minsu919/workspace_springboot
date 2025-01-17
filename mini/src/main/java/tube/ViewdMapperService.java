package tube;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("viewdmapperservice")
public class ViewdMapperService implements ViewdService {

	@Autowired
	ViewdMapper mapper;
	
	@Override
    public void insertViewd(ViewdDTO viewd) {
        mapper.insertViewd(viewd);
    }

    @Override
    public List<ViewdDTO> selectViewdById(String id) {
        return mapper.selectViewddById(id);
    }
}
