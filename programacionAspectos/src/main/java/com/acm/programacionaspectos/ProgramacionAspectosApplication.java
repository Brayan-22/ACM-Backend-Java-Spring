package com.acm.programacionaspectos;

import com.acm.programacionaspectos.persistence.dao.impl.ClienteDAO;
import com.acm.programacionaspectos.persistence.dao.impl.ProductoDAO;
import com.acm.programacionaspectos.persistence.entity.Cliente;
import com.acm.programacionaspectos.persistence.entity.Producto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@Slf4j
@EnableAspectJAutoProxy
public class ProgramacionAspectosApplication {
	//AOP -> Paradigma basada en el aspecto
	//Aspecto -> Conjunto de asuntos transversales de modularización difícil
	//Los aspectos encapsulan lógica de una funcionalidad transversal
	//Se pueden aplicar/reutilizar en múltiples lugares
	public static void main(String[] args) {
		SpringApplication.run(ProgramacionAspectosApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ClienteDAO clienteDAO, ProductoDAO productoDAO) {
		return args -> {
			clienteDAO.save(new Cliente(1L,"Alejandro","Riveros"));
			productoDAO.save(new Producto(1L,"Televisor",1_299d));
			var l = clienteDAO.findAll();
			l.forEach(x -> System.out.println(x.id()));
			try{
				clienteDAO.save(null);
				clienteDAO.save(null);
				clienteDAO.save(null);
				clienteDAO.save(null);
				clienteDAO.save(null);
			}catch (Exception e){
				e.getMessage();
			}
			var r = clienteDAO.findById(1L);
			log.info(r.toString());
		};
	}
}
