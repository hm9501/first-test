package say.haemee.WebConfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import say.haemee.RootConfig.RootConfig;
import say.haemee.ServletConfig.ServletXml;


public class WebXml implements WebApplicationInitializer {
	
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//ServletXml 클래스를 객체로 등록
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletXml.class);
		
		
		//dispatcher 정의
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		

		//파라미터 인코딩 설정
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding","utf-8");
		filter.addMappingForServletNames(null,false,"dispatcher");
		
	}//OnStartUp
	
}//class
