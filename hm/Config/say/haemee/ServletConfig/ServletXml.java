package say.haemee.ServletConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration								//Spring MVC 프로젝트에 관련된 설정을 하는 클래스
@EnableWebMvc								//Controller 어노테이션이 세팅된 클래스를 Controller로 등록
@ComponentScan("say.haemee.Controller")		//스캔할 Bean들이 모여 있는 패키지를 지정
public class ServletXml implements WebMvcConfigurer {
	
	
	
	//Controller의 메서드에서 반환하는 문자열 앞 뒤에 붙일 경로를 세팅
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/" , ".jsp");		
	}//ViewResolvers
	
	
	
	//정적파일 경로 세팅
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/");
	}//ResourceHandlers
}//class
