package com.VargasTest.Estudiantes;

import com.VargasTest.Estudiantes.modelo.Estudiante;
import com.VargasTest.Estudiantes.servicio.EstudianteServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {


	@Autowired
	private EstudianteServicio estudianteServicio;


	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

	String n1 = System.lineSeparator();


	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");
		// Levantar la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada");
	}


	@Override
	public void run(String... args) throws Exception {
		logger.info(n1 + "ejecutando metodo run de Spring...." + n1);
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(n1);

		}


	}
	private void mostrarMenu(){
		logger.info(n1);
		logger.info("""
                ***Sistema de estudiantes***
                1. Listar Estudiante
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elige una opcion:""");
	}
	private  boolean ejecutarOpciones(Scanner consola){
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion){
			case 1 ->{ // Listar Estudiantes
				logger.info(n1 + "Listado de Estudiantes" + n1);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiante();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString() + n1)));
			}//fin case1
			case 2 ->{ // Buscar por id
				logger.info(n1 + "Introduce el id_estudiante a buscar");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);

				if(estudiante != null)
					logger.info(n1 + "Estudiante encontrado: " + estudiante + n1);
				else
					logger.info(n1 + "No se encontro al estudiante con id: " +  estudiante + n1);
			}//fin case 2
			case 3 ->{// Agregar
				logger.info(n1 + "Agregar estudiante: " + n1);
				logger.info(n1 + "Nombre: ");
				var nombre = consola.nextLine();
				logger.info(n1+ "Apellido: ");
				var apellido = consola.nextLine();
				logger.info(n1 + "Telefono: ");
				var telefono =  consola.nextLine();
				logger.info(n1 + "Email: ");
				var email = consola.nextLine();

				//Crear el objeto estudiante sin el id
				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				if(estudiante != null)
					logger.info(n1 + "Se agrego el estudiante: " + estudiante);
				else
					logger.info(n1 + "No se pudo agregar al estudiante: " + estudiante);

			}//fin case 3
			case 4 ->{//modificar
				logger.info("Modificar estudiante: " + n1);
				logger.info("Id Estudiante: " );
				var idEstudiante = Integer.parseInt(consola.nextLine());
				//Buscamos el estudiante a modificar
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante!=null){

					logger.info(n1 + "Nombre: ");
					var nombre = consola.nextLine();
					logger.info(n1+ "Apellido: ");
					var apellido = consola.nextLine();
					logger.info(n1 + "Telefono: ");
					var telefono =  consola.nextLine();
					logger.info(n1 + "Email: ");
					var email = consola.nextLine();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);

					logger.info("Se modifico el estudiante: " + estudiante + n1);
				}else{
					logger.info("Estudiante no encontrado con id " + estudiante + n1);
				}


			}//fin case 4
			case 5 ->{// Eliminar
				logger.info("Eliminar estudiante: " + n1);
				logger.info("Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante!=null){
					estudianteServicio.eliminarestudiante(estudiante);
					logger.info("Estudiante eliminado: " + estudiante + n1);
				}else{
					logger.info("Estudiante no encontrado con id " + estudiante + n1);
				}
			}//Fin case 5
			case 6 ->{//Salir
					logger.info("Hasta Pronto!" + n1 + n1);
				 }// Fin case 6
			default -> logger.info("Opcion no reconocida: " + opcion + n1);
		}//fin del switch
		return salir;
	}
}
