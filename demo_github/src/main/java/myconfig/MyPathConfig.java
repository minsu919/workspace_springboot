package myconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  //@SpringBootApplication 클래스 시작시에 같이 실행
public class MyPathConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("패스컨피그실행");
		if (System.getProperty("os.name").contains("Win")) {
			registry.addResourceHandler("/miniupload/**")
			.addResourceLocations("file:///c:/ezwel/miniupload/");		
		} else {
			registry.addResourceHandler("/miniupload/**")
			.addResourceLocations("file:////Users/minsu/Documents/ezwel/miniupload/");
		}
	}
	
}