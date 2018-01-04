package com.reviews.app;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.annotation.HandlesTypes;

import org.apache.catalina.Context;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.NonEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.reviews.app.jsf.FacesViewScope;
import com.sun.faces.config.FacesInitializer;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SpringBootFacesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootFacesApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootFacesApplication.class);
	}

	@Bean
	public static CustomScopeConfigurer customScopeConfigurer() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(Collections.<String, Object>singletonMap(FacesViewScope.NAME, new FacesViewScope()));
		return configurer;
	}

/*	@Bean
	public ServletContextInitializer servletContextCustomizer() {
		return new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext sc) throws ServletException {
				sc.setInitParameter(Constants.ContextParams.THEME, "sentinel");
				// sc.setInitParameter(Constants.ContextParams.FONT_AWESOME,
				// "true");
				sc.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, ProjectStage.Development.name());
				sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
			}
		};
	}*/
	
	
	 @Bean
	    public ServletContextInitializer servletContextInitializer() {
	        return servletContext -> {
	            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
	            servletContext.setInitParameter("primefaces.THEME", "sentinel");
	            servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
	            servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
	            servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
	            servletContext.setInitParameter("primefaces.UPLOADER", "commons");
	        };
	    }
	
	 @Bean
	    public ServletRegistrationBean facesServletRegistraiton() {
	        ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(), new String[]{"*.jsf"});
	        registration.setName("Faces Servlet");
	        registration.setLoadOnStartup(1);
	        return registration;
	    }
	 
	@Bean
    public FilterRegistrationBean facesUploadFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new FileUploadFilter(), facesServletRegistraiton());
        registrationBean.setName("PrimeFaces FileUpload Filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST);
        return registrationBean;
    }

	/**
	 * This bean is only needed when running with embedded Tomcat.
	 */
	@Bean
	@ConditionalOnMissingBean(NonEmbeddedServletContainerFactory.class)
	public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

		tomcat.addContextCustomizers(new TomcatContextCustomizer() {
			@Override
			public void customize(Context context) {
				// register FacesInitializer
				context.addServletContainerInitializer(new FacesInitializer(),
						getServletContainerInitializerHandlesTypes(FacesInitializer.class));

				// add configuration from web.xml
				context.addWelcomeFile("login.jsf");

				// register additional mime-types that Spring Boot doesn't
				// register
				context.addMimeMapping("eot", "application/vnd.ms-fontobject");
				context.addMimeMapping("ttf", "application/x-font-ttf");
				context.addMimeMapping("woff", "application/x-font-woff");
			}
		});

		return tomcat;
	}

	@SuppressWarnings("rawtypes")
	private Set<Class<?>> getServletContainerInitializerHandlesTypes(
			Class<? extends ServletContainerInitializer> sciClass) {
		HandlesTypes annotation = sciClass.getAnnotation(HandlesTypes.class);
		if (annotation == null) {
			return Collections.emptySet();
		}

		Class[] classesArray = annotation.value();
		Set<Class<?>> classesSet = new HashSet<Class<?>>(classesArray.length);
		for (Class clazz : classesArray) {
			classesSet.add(clazz);
		}

		return classesSet;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
