package react.ajax;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ReactAjaxController {
	
	@RequestMapping("/helloajax")
	@CrossOrigin(origins = "*")
	public LoginDTO test() {
		return new LoginDTO("boot_id", 1234);
	}
	
	@CrossOrigin(origins = "http://localhost:4000")
	@GetMapping("/helloajaxparam")
	public LoginDTO test(String id, int pw) {
		return new LoginDTO(id, pw);
	}
	
	@CrossOrigin(origins = "http://localhost:4000")
	@PostMapping("/helloajaxparam")
	public LoginDTO test(@RequestBody LoginDTO dto) {// json -- DTO 변환
		System.out.println("post 전달받음 = " + dto.getId() + " : " + dto.getPw());
		return dto;
	}
	
	@CrossOrigin(origins = "http://localhost:4000")
	@GetMapping("/helloajaxarray")
	public int[] testarray(int[] ids) {
		for(int i=0; i<ids.length; i++) {
			ids[i] = ids[i] * 10;
			System.out.println(ids[i]);
		}
		return ids;
	}
	
	@CrossOrigin(origins = "http://localhost:4000")
	@PostMapping("/helloajaxobjectarray")
	public PlayerDTO testplayer(@RequestBody Map<String, Object> parameters) throws JsonMappingException, JsonProcessingException {
		String json = parameters.get("playerArray").toString();
		ObjectMapper mapper = new ObjectMapper();
		List<PlayerDTO> playerList = mapper.readValue(json, new TypeReference<ArrayList<PlayerDTO>>() {});
		for (PlayerDTO dto : playerList) {
			if (dto.player.equals("son")) {
			return new PlayerDTO(dto.getPlayer(), dto.getGoal(), "한국", "손흥민");
			}
		}
		return new PlayerDTO("알수없음", 0, "국적모름", "이름모름");
	}
	
	
}
	 


class LoginDTO {
	String id;
	int pw;
	public LoginDTO() {}
	public LoginDTO(String id, int pw) {
		this.id = id;
		this.pw = pw;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPw() {
		return pw;
	}
	public void setPw(int pw) {
		this.pw = pw;
	}
}

class PlayerDTO {
	String player;
	int goal;
	String fullName;
	String nation;
	
	public PlayerDTO() {} // 원인
	public PlayerDTO(String player, int goal, String fullName, String nation) {
		super();
		this.player = player;
		this.goal = goal;
		this.fullName = fullName;
		this.nation = nation;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	
}