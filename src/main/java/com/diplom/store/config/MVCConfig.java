package com.diplom.store.config;

import java.io.File;
import javax.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MVCConfig implements WebMvcConfigurer {

	private final PropertiesConfig config;

	private final ServletContext context;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String realPath = context.getRealPath("/img/");
		File upload = new File(realPath);
		if (!upload.exists()) {
			upload.mkdir();
		}
		registry.addResourceHandler("/img/**")
			.addResourceLocations("file:\\" + realPath + "\\");
	}
}
