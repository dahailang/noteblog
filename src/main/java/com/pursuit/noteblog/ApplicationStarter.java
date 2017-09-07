package com.pursuit.noteblog;

import java.awt.Desktop;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/spring.xml")
public class ApplicationStarter {

	public static void main(String[] args) {
		Logger logger  = LoggerFactory.getLogger(ApplicationStarter.class);
		SpringApplication app = new SpringApplication(ApplicationStarter.class);
        app.setAdditionalProfiles();
        app.setBannerMode(Banner.Mode.LOG);
        //System.out.println(System.getProperty("java.awt.headless", Boolean.toString(true)));
        app.run(args);
        logger.info("系统启动完成："+Desktop.isDesktopSupported());
        
        if(Desktop.isDesktopSupported()){
        	try {
        		Desktop.getDesktop().browse(new URI("http" + "://" + "127.0.0.1" + ":" + "8080" + "/"));
        	} catch (final Throwable e) {
        		logger.error("打开浏览器失败");
        		e.printStackTrace();
        	}
        }
	}
}
