package com.acm.mongodb;
import com.acm.mongodb.domain.CuentaDTO;
import com.acm.mongodb.domain.PersonaDTO;
import com.acm.mongodb.persistence.documents.Cuenta;
import com.acm.mongodb.persistence.documents.Persona;
import com.acm.mongodb.persistence.repository.PersonaRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.codecs.ObjectIdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@Slf4j
public class MongodbApplication {
	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}

	//Enfoque mongodb driver nativo
	//@Bean
	CommandLineRunner runner(@Value("${spring.data.mongodb.uri}") String uri){
		return args -> {
			MongoClient mongoClient = MongoClients.create(uri);
			MongoDatabase db = mongoClient.getDatabase("acm");
			var collection = db.getCollection("persona");
			//Eliminacion
			collection.deleteMany(new Document("id",1));
			collection.deleteMany(new Document("_id",1));
			//Inserciones
			CuentaDTO c1 = new CuentaDTO(new ObjectIdGenerator().generate().toString(),"Ahorros","Banco1",2_000d);
			CuentaDTO c2 = new CuentaDTO(new ObjectIdGenerator().generate().toString(),"Corriente","Banco2",3_000d);
			CuentaDTO c3 = new CuentaDTO(new ObjectIdGenerator().generate().toString(),"Nomina","Banco3",1_500d);
			PersonaDTO persona = new PersonaDTO(1,"Fulano","Perez","fulano@correo.com",20,List.of(c1,c2,c3));
			collection.insertOne(persona.getDocument());
			//Actualizacion
			collection.updateOne(persona.getDocument(),Updates.set("apellido","Rodriguez"));
			//Lectura
			List<Document> res = collection.find().into(new ArrayList<>());
			res.stream().map(PersonaDTO::fromDocument).forEach(p -> log.info(p.toString()));

			//Operaciones de agregacion y consultas avanzadas

			var tipoCuentaByIdPersona = collection.aggregate(Arrays.asList(
					new Document("$match",new Document("_id",1)),
					new Document("$unwind","$cuentas"),
					new Document("$match",new Document("cuentas.tipoCuenta","Ahorros")),
					new Document("$count","total")
			)).into(new ArrayList<>());

			var personasPorNombre = collection.countDocuments(new Document("nombre","Fulano"));
			log.info("Numero de cuentas de ahorros de {}: {}","Fulano",tipoCuentaByIdPersona.get(0).getInteger("total"));
			log.info("Numero de personas con nombre {}: {}","Fulano",personasPorNombre);
		};
	}

	//Enfoque IoC
	//@Bean
	CommandLineRunner configurationMongoClient(MongoClient mongoClient) {
		return args -> {
			var db = mongoClient.getDatabase("acm");
			var collection = db.getCollection("persona");

			//Eliminacion
			collection.deleteMany(new Document("id",1));
			collection.deleteMany(new Document("_id",1));
			//Inserciones
			CuentaDTO c1 = new CuentaDTO(new ObjectIdGenerator().generate().toString(),"Ahorros","Banco1",2_000d);
			CuentaDTO c2 = new CuentaDTO(new ObjectIdGenerator().generate().toString(),"Corriente","Banco2",3_000d);
			CuentaDTO c3 = new CuentaDTO(new ObjectIdGenerator().generate().toString(),"Nomina","Banco3",1_500d);
			PersonaDTO persona = new PersonaDTO(1,"Fulano","Perez","fulano@correo.com",20,List.of(c1,c2,c3));
			collection.insertOne(persona.getDocument());
			//Actualizacion
			collection.updateOne(persona.getDocument(),Updates.set("apellido","Rodriguez"));
			//Lectura
			List<Document> res = collection.find().into(new ArrayList<>());
			res.stream().map(PersonaDTO::fromDocument).forEach(p -> log.info(p.toString()));

			//Operaciones de agregacion y consultas avanzadas

			var tipoCuentaByIdPersona = collection.aggregate(Arrays.asList(
					new Document("$match",new Document("_id",1)),
					new Document("$unwind","$cuentas"),
					new Document("$match",new Document("cuentas.tipoCuenta","Ahorros")),
					new Document("$count","total")
			)).into(new ArrayList<>());

			var personasPorNombre = collection.countDocuments(new Document("nombre","Fulano"));
			log.info("Numero de cuentas de ahorros de {}: {}","Fulano",tipoCuentaByIdPersona.get(0).getInteger("total"));
			log.info("Numero de personas con nombre {}: {}","Fulano",personasPorNombre);
		};
	}

	//Enfoque programatico de mongoTemplate
	@Bean
	CommandLineRunner mongoTemplateUsage(MongoTemplate mongoTemplate) { //El objeto MongoTemplate puede reemplazarse con su interfaz MongoOperations
		return args -> {
			//Inserciones:
			mongoTemplate.save(new Persona(1,"Pepito","Perez","pepito@correo.com",20,List.of(
					new Cuenta(new ObjectIdGenerator().generate().toString(),"Ahorros","Banco1",2_000d),
					new Cuenta(new ObjectIdGenerator().generate().toString(),"Corriente","Banco2",3_000d),
					new Cuenta(new ObjectIdGenerator().generate().toString(),"Nomina","Banco3",1_500d)
			)),"persona");
			//Actualizacion:
			mongoTemplate.updateFirst(new Query(Criteria.where("id").is(1)), Update.update("apellido","Rodriguez"),"persona");
			//Lectura:
			mongoTemplate.findAll(Persona.class).forEach(p -> log.info(p.toString()));
			//Operaciones de agregacion y consultas avanzadas
			//Filtros:
			Aggregation aggregation = Aggregation.newAggregation(
					Aggregation.match(Criteria.where("nombre").is("Pepito")),
					Aggregation.unwind("cuentas"),
					Aggregation.match(Criteria.where("cuentas.tipoCuenta").is("Ahorros")),
					Aggregation.count().as("total")
			);

			// Ejecucion de la agregación
			AggregationResults<Document> result = mongoTemplate.aggregate(aggregation,"persona",Document.class);
			log.info(result.getRawResults().toJson());
			var tipoCuentaByIdPersona = result.getMappedResults().isEmpty()?0:result.getMappedResults().get(0).getInteger("total");
			log.info("Numero de cuentas de ahorros de {}: {}","Pepito",tipoCuentaByIdPersona);
			var personasPorNombre = mongoTemplate.count(new Query(Criteria.where("nombre").is("Pepito")),"persona");
			log.info("Numero de personas con nombre {}: {}","Pepito",personasPorNombre);

			//Eliminacion
			mongoTemplate.remove(new Query(Criteria.where("id").is(1)), "persona");
		};
	}



	//Enfoque usando abstracciones de repositorios
	//@Bean
	CommandLineRunner mongoRepositoryUsage(PersonaRepository personaRepository) {
		return args -> {
			//Eliminaciones
			personaRepository.deleteAll();
			Cuenta c1 = new Cuenta(new ObjectIdGenerator().generate().toString(),"Ahorros","Banco1",2_000d);
			Cuenta c2 = new Cuenta(new ObjectIdGenerator().generate().toString(),"Corriente","Banco2",3_000d);
			Cuenta c3 = new Cuenta(new ObjectIdGenerator().generate().toString(),"Nomina","Banco3",1_500d);
			List<Cuenta> cuentas = List.of(c1,c2,c3);
			//Inseciones
			personaRepository.save(new Persona(1,"Fulano","Perez","fulano@correo.com",20, cuentas));
			//Actualizaciones
			personaRepository.updateApellidoByNombre("Rodriguez","Fulano");
			personaRepository.updatePersonaById(1,Persona.builder()
					.nombre("Pepito")
					.apellido("Perez")
					.correo("pepito@correo.com")
					.edad(35)
					.build());
			//Lectura
			List<Persona> personas = personaRepository.findAll();
			personas.forEach(p -> {
				log.info(p.getNombre());
				p.getCuentas().forEach(c -> log.info(c.toString()));
			});
			var p = personaRepository.findPersonaById(1);
			log.info(p.toString());
			//Operaciones de agregacion y consultas avanzadas
			var tipoCuentaByIdPersona = personaRepository.countTipoCuentaByIdPersona(1,"Ahorros");
			var personasPorNombre = personaRepository.countPersonasByNombre("Pepito");
			log.info("Numero de cuentas de ahorros: {}",tipoCuentaByIdPersona);
			log.info("Numero de personas con nombre {}: {}","Pepito",personasPorNombre);
		};
	}

}