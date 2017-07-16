package com.pursuit.noteblog;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/spring.xml")
public class ApplicationStarter {

	public static void main(String[] args) {
		//Logger logger  = LoggerFactory.getLogger(ApplicationStarter.class);
        
		SpringApplication app = new SpringApplication(ApplicationStarter.class);
        app.setAdditionalProfiles();
        app.setBannerMode(Banner.Mode.LOG);
        app.run(args);
//        try {
//            Desktop.getDesktop().browse(new URI("http" + "://" + "127.0.0.1" + ":" + "8080" + "/"));
//        } catch (final Throwable e) {
//        	logger.error("打开浏览器失败");
//        	e.printStackTrace();
//        }
	}
}
