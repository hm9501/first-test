package say.haemee.RootConfig;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.BooleanTypeHandler;
import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/spring/appconfig.properties")
@EnableTransactionManagement
@MapperScan(value = "say.haemee.Mapper")
public class RootConfig {
	
	
	@Value("${db.driver}")
	private String driver;
	
	@Value("${db.url}")
	private String url;
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public BasicDataSource dataSource(){
		BasicDataSource bsds = new BasicDataSource();
		bsds.setDriverClassName(driver);
		bsds.setUrl(url);
		bsds.setUsername(username);
		bsds.setPassword(password);
		return bsds;
	}//dataSource
	
	
	@Bean
	public SqlSessionFactoryBean sqlFactoryBean() throws Exception{
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		
		Resource [] res = new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/*.xml");
		
		bean.setMapperLocations(res);
		bean.setTypeAliasesPackage("say.haemee.Impl");
		bean.setTypeHandlers(new TypeHandler [] {
				new DateTypeHandler(), new BooleanTypeHandler()
				
		});
		
		return (SqlSessionFactoryBean) bean.getObject();
	}//SqlSessionFactoryBean
	
	
	
	@Bean
	public PlatformTransactionManager platformTransactionManager(){
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(dataSource());
		return dstm;
	}//platformTransactionManager
	
	
	@Bean
	public SqlSessionTemplate sessionTemplate(SqlSessionFactory factory){
		return new SqlSessionTemplate(factory);
	}
}//class
