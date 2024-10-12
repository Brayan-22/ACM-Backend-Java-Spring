package com.acm.jdbcacm;

import com.acm.jdbcacm.persistence.DAO.IDAO;
import com.acm.jdbcacm.persistence.jpa.repository.PersonaRepository;
import com.acm.jdbcacm.persistence.models.Persona;
import com.acm.jdbcacm.persistence.models.PersonaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.support.ExecutorServiceAdapter;

import java.time.Duration;
import java.util.concurrent.ExecutorService;


@SpringBootApplication
public class JdbcAcmApplication  {
	private static final Logger logger = LoggerFactory.getLogger(JdbcAcmApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JdbcAcmApplication.class, args);
	}

	//@Bean
	CommandLineRunner runner (
			@Autowired
			@Qualifier("nativeJDBCpersonaDao")
			//@Qualifier("datasourcePersonaDao")
			IDAO<Persona,Integer> dao) {
		return args -> {
			ExecutorService executorService = new ExecutorServiceAdapter(Runnable::run);
			Runnable runnable = () -> {
				for (int i = 0; i < 100 ; i++) {
					try{
						var p = dao.findById(1);
						logger.info("Iteracion: {}, Cosulta: {}",i,p.toString());
						Thread.sleep(Duration.ofSeconds(1).toMillis());
					}catch (InterruptedException e){
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			};
			executorService.execute(runnable);
		};
	}
	@Bean
	ApplicationRunner runner(@Autowired PersonaRepository repository) {
		return args -> {
			ExecutorService executorService = new ExecutorServiceAdapter(task -> {
				task.run();
			});
			Runnable runnable = () -> {
				for (int i = 0; i < 100 ; i++) {
					try{
						var p = repository.findById(1);
						logger.info("Iteracion: {}, Cosulta: {}",i,p.toString());
						Thread.sleep(Duration.ofSeconds(1).toMillis());
					}catch (InterruptedException e){
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			};
			executorService.execute(runnable);
		};
	}

	//@Bean
	ApplicationRunner applicationRunner (@Autowired@Qualifier("entityManagerDAO") IDAO<PersonaEntity,Integer> dao) {
		return args -> {
			ExecutorService executorService = new ExecutorServiceAdapter(Runnable::run);
			Runnable runnable = () -> {
				for (int i = 0; i < 100 ; i++) {
					try{
						var p = dao.findById(1);
						logger.info("Iteracion: {}, Cosulta: {}",i,p.toString());
						Thread.sleep(Duration.ofSeconds(1).toMillis());
					}catch (InterruptedException e){
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			};
			executorService.execute(runnable);
        };
    }
}
