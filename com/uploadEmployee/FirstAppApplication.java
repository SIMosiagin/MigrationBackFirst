package uploadEmployee;


import uploadEmployee.entity.FileExcel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class })
public class FirstAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstAppApplication.class, args);



		//AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		//InterfaceService contactService = (InterfaceService) context.getBean("InterfaceService");






	}



	@Bean
	public FileExcel getFileExcel(){
		return new FileExcel();
	}

}
