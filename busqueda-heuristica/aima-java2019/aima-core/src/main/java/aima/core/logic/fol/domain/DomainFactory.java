package aima.core.logic.fol.domain;

/**
 * @author Ravi Mohan
 * 
 */
public class DomainFactory {
	
	public static FOLDomain personaDomain() {
		FOLDomain domain = new FOLDomain();
		domain.addConstant("Socrates");
		domain.addPredicate("Persona");
		domain.addPredicate("Mortal");
		return domain;
	}
	
	public static FOLDomain filosofos() {
		FOLDomain domain = new FOLDomain();
		domain.addPredicate("Persona");
		domain.addPredicate("Mortal");
		domain.addPredicate("Filosofo");
		domain.addConstant("Socrates");
		return domain;
	}
	

	public static FOLDomain madrilenos() {
		FOLDomain domain = new FOLDomain();

		domain.addPredicate("Madrileno");
		domain.addPredicate("Universitario");
		domain.addPredicate("Carroza");
		domain.addPredicate("BebeLitrona");
		domain.addPredicate("Degenerado");
		domain.addPredicate("Padre");
		domain.addPredicate("Madre");
		domain.addPredicate("Marxista");
		domain.addPredicate("Feminista");
		domain.addPredicate("Fanatico");
		domain.addPredicate("Iluso");
		domain.addPredicate("Asocial");
		
		domain.addConstant("PedroPerplejo");
		domain.addConstant("JuanDeLaCosa");
		
		return domain;
	}
	

		public static FOLDomain alumnos() {
			FOLDomain domain = new FOLDomain();

			domain.addPredicate("Alumno");
			domain.addPredicate("Suspende");
			domain.addPredicate("Deprimido");
			domain.addPredicate("Estudia");
			domain.addPredicate("Asignatura");
			
			return domain;
		}

	
	public static FOLDomain portenasAlegresDomain(){
		FOLDomain domain = new FOLDomain();
		
		domain.addPredicate("Porteno");
		domain.addPredicate("Portena");
		domain.addPredicate("Marinero");
		domain.addPredicate("Amigo");
		domain.addPredicate("Alegre");
		domain.addPredicate("Feliz");
		domain.addPredicate("Triste");
		domain.addPredicate("Cornudo");
		domain.addPredicate("Casado");
		
		return domain;
	}
	
	public static FOLDomain protozoos() {
		FOLDomain domain = new FOLDomain();

		domain.addPredicate("Protozoo");
		domain.addPredicate("Pequeno");
		domain.addPredicate("Peludo");
		domain.addPredicate("Suave");
		domain.addPredicate("Naturalista");
		domain.addPredicate("Pobre");
		domain.addPredicate("TieneMicroscopio");
		domain.addPredicate("Observa");
		
		return domain;
	}
	
	

	
	public static FOLDomain psicologos() {
		FOLDomain domain = new FOLDomain();

		domain.addPredicate("EstudiosoConducta");
		domain.addPredicate("PersonaInsegura");
		domain.addPredicate("Psicologo");
		
		return domain;
	}
	

	
	public static FOLDomain animalesDeLaSelva() {
		FOLDomain domain = new FOLDomain();

		domain.addPredicate("Animal");
		domain.addPredicate("Selva");
		domain.addPredicate("Ama");
		domain.addPredicate("Teme");
		domain.addPredicate("Solucion");
		
		return domain;
	}
	
	public static FOLDomain pajaroEsquiador() {
		FOLDomain domain = new FOLDomain();
		domain.addConstant("Pepe");
		domain.addConstant("Juan");
		domain.addConstant("Pedro");

		domain.addPredicate("Pajaro");
		domain.addPredicate("Avestruz");
		domain.addPredicate("Vuela");
		domain.addPredicate("TienePico");
		domain.addPredicate("SabeEsquiar");
		domain.addPredicate("PajaroEsquiador");
		domain.addPredicate("Solucion");
		
		return domain;
	}
	
	
	// ----------------------------------------------- //
	
	public static FOLDomain crusadesDomain() {
		FOLDomain domain = new FOLDomain();
		domain.addConstant("John");
		domain.addConstant("Richard");
		domain.addConstant("England");
		domain.addConstant("Saladin");
		domain.addConstant("Crown");

		domain.addFunction("LeftLegOf");
		domain.addFunction("BrotherOf");
		domain.addFunction("EnemyOf");
		domain.addFunction("LegsOf");

		domain.addPredicate("King");
		return domain;
	}

	public static FOLDomain knowsDomain() {
		FOLDomain domain = new FOLDomain();
		domain.addConstant("John");
		domain.addConstant("Jane");
		domain.addConstant("Bill");
		domain.addConstant("Elizabeth");
		domain.addFunction("Mother");
		domain.addPredicate("Knows");
		return domain;
	}

	public static FOLDomain weaponsDomain() {

		FOLDomain domain = new FOLDomain();
		domain.addConstant("West");
		domain.addConstant("America");
		domain.addConstant("M1");
		domain.addConstant("Nono");
		domain.addPredicate("American");
		domain.addPredicate("Weapon");
		domain.addPredicate("Sells");
		domain.addPredicate("Hostile");
		domain.addPredicate("Criminal");
		domain.addPredicate("Missile");
		domain.addPredicate("Owns");
		domain.addPredicate("Enemy");

		return domain;
	}

	public static FOLDomain kingsDomain() {
		FOLDomain domain = new FOLDomain();
		domain.addConstant("John");
		domain.addConstant("Richard");
		domain.addPredicate("King");
		domain.addPredicate("Greedy");
		domain.addPredicate("Evil");
		return domain;
	}

	public static FOLDomain lovesAnimalDomain() {
		FOLDomain domain = new FOLDomain();
		domain.addPredicate("Animal");
		domain.addPredicate("Loves");
		domain.addPredicate("Kills");
		domain.addPredicate("Cat");
		
		domain.addConstant("Jack");
		domain.addConstant("Tuna");
		domain.addConstant("Curiosity");
		
		return domain;
	}

	public static FOLDomain ringOfThievesDomain() {
		FOLDomain domain = new FOLDomain();
		domain.addPredicate("Parent");
		domain.addPredicate("Caught");
		domain.addPredicate("Friend");
		domain.addPredicate("Skis");
		domain.addConstant("Mike");
		domain.addConstant("Joe");
		domain.addConstant("Janet");
		domain.addConstant("Nancy");
		domain.addConstant("Ernie");
		domain.addConstant("Bert");
		domain.addConstant("Red");
		domain.addConstant("Drew");
		return domain;
	}
}
