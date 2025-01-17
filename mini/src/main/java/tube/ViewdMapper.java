package tube;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ViewdMapper {

    void insertViewd(ViewdDTO viewedDTO);
    
    List<ViewdDTO> selectViewddById(String id);
}
