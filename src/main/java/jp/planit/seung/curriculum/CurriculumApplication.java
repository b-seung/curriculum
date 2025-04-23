package jp.planit.seung.curriculum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jp.planit.seung.curriculum.dto.MenuStaticValue;
import jp.planit.seung.curriculum.entity.MenuEntity;
import jp.planit.seung.curriculum.repository.MenuRepository;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
public class CurriculumApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurriculumApplication.class, args);
	}

	@Bean
	public ApplicationRunner menuCreate() {
		return new MenuInitializer();
	}
}

class MenuInitializer implements ApplicationRunner {
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<MenuEntity> menuList = menuRepository.findAll();
		MenuStaticValue.menuList = menuList;
	}

}
