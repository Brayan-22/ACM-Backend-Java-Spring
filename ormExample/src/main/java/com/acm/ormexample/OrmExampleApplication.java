package com.acm.ormexample;

import com.acm.ormexample.persistence.entity.*;
import com.acm.ormexample.persistence.repository.ClienteRepository;
import com.acm.ormexample.persistence.repository.ProductoRepository;
import com.acm.ormexample.persistence.repository.VentaRepository;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class OrmExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrmExampleApplication.class, args);
	}

	/*
	* La anotacion @Order establece el orden en el que se invoca la declaracion de Beans :)
	*/

	@Bean
	@Order(1)
	ApplicationRunner applicationRunner(ClienteRepository clienteRepository) {
		return args -> {
			Cliente c = Cliente.builder()
					.id(10L)
					.nombre("Fulano")
					.apellido("Perez")
					.cuenta(new Cuenta(UUID.randomUUID(),"mypassword", Cuenta.Privilegio.VIP))
					.ciudad(new Ciudad(10L,"Pereira",20L,new Departamento(12L,"RisaraldA",10L)))
					.build();
			clienteRepository.save(c);
		};
	}
	@Bean
	@Order(2)
	CommandLineRunner commandLineRunner(ProductoRepository productoRepository,
										ClienteRepository clienteRepository,
										VentaRepository ventaRepository) {
		return args -> {
			productoRepository.findById(1L).ifPresent(producto -> log.info(producto.toString()));
			var ventas = ventaRepository.findByClienteId(1L);
			ventas.forEach(venta -> log.info(venta.toString()+" "+ venta.getCliente()+" "+venta.getVentaProductos()));
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
			var json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ventas);
			System.out.println(json);
			var c = clienteRepository.findByNombreAndApellido("Fulano", "Perez");
			System.out.println(c);
			log.info(clienteRepository.findFirstByNombre("Fulano").toString());
			log.info(clienteRepository.findFirstByApellido("Perez").toString());
			clienteRepository.deleteByNombre("Fulano");
        };
	}

}
