package tube;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewdController {

	@Autowired
	@Qualifier("viewdmapperservice")
	ViewdService viewdService;

    @PostMapping("/viewd")
    public String addViewd(@RequestBody ViewdDTO viewd) {
        viewdService.insertViewd(viewd);
        return "시청 기록 저장";
    }

    @GetMapping("/feed/history")
    public ModelAndView viewdList(@RequestParam("id") String id, HttpSession session) {
        ModelAndView mv = new ModelAndView("viewdListPage");
        session.setAttribute("userId", id);
        
        List<ViewdDTO> viewdList = viewdService.selectViewdById(id);
        mv.addObject("viewdList", viewdList);
        
        mv.addObject("viewdList", viewdList);
        mv.setViewName("feed/history");
        
        return mv;
    }
}