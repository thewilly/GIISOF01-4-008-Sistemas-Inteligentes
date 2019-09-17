package aima.core.logic.fol.kb;

import aima.core.logic.fol.domain.DomainFactory;
import aima.core.logic.fol.domain.FOLDomain;
import aima.core.logic.fol.inference.InferenceProcedure;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class FOLKnowledgeBaseFactory {
	
	public static FOLKnowledgeBase createPersonaKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(DomainFactory.personaDomain(),
				infp);
		kb.tell("FORALL x (Persona(x) => Mortal(x))");
		kb.tell("Persona(Socrates)");
		
		return kb;
	}
	
	public static FOLKnowledgeBase createFilosofosKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(DomainFactory.filosofos(),
				infp);
		//Todas las personas excepto algunos filosofos son mortales
		//Luego todas las personas que no son filosofos son mortales
		kb.tell("FORALL x (Persona(x) AND NOT Filosofo(x) => Mortal(x))");
		//Y entre los filosofos los hay mortales
		kb.tell("EXISTS x (Filosofo(x) AND Mortal(x))");
		//y no mortales
		kb.tell("EXISTS x (Filosofo(x) AND NOT Mortal(x))");
		kb.tell("(Persona(Socrates) AND NOT Mortal(Socrates))");	
		return kb;
	}
	
	// Ejercicio 1: Alumno
		public static FOLKnowledgeBase createAlumnosKnowledgeBase(
				InferenceProcedure infp) {
			FOLKnowledgeBase kb = new FOLKnowledgeBase(DomainFactory.alumnos(),
					infp);
			
			kb.tell("NOT FORALL x FORALL y (Alumno(x) AND Asignatura(x) AND Suspende(x,y) => NOT Estudia(x,y))");
			kb.tell("FORALL x FORALL y (Alumno(x) AND Asignatura(x) AND Suspende(x,y) AND Estudia(x,y) => Deprimido(x) )");
			
			
			//kb.tell("(Alumno(Pedro) AND Asignatura(Matematicas) AND NOT Estudia(Pedro,Matematicas) )");
			
		    //kb.tell("(Alumno(Pedro) AND Asignatura(Matematicas) AND NOT Estudia(Pedro,Matematicas) AND Deprimido(Pedro))");
			
				
			return kb;
		}
		
		
	
	// Ejercicio 2: Madrileños
	public static FOLKnowledgeBase createMadrilenosKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(DomainFactory.madrilenos(),
				infp);
		kb.tell("FORALL x (Madrileno(x) AND NOT BebeLitrona(x) =>  Carroza(x))");
		//kb.tell("FORALL x (Universitario(x) AND Carroza(x) => (EXISTS y (Feminista(y) AND Madre(y,x)) OR (Marxista(y) AND Padre(y,x))))");
		kb.tell("FORALL x (Universitario(x) AND Carroza(x) => (EXISTS y (Madre(y,x) AND Feminista(y))) OR (EXISTS z (Padre(z,x) AND Marxista(z))))");
		kb.tell("FORALL x (BebeLitrona(x) => Degenerado(x))");
		kb.tell("FORALL x (Marxista(x) => Iluso(x))");
		kb.tell("FORALL x (Feminista(x) => Fanatico(x))");
		kb.tell("FORALL x (Degenerado(x) OR Iluso(x) OR Fanatico(x) => Asocial(x))");
		kb.tell("(Universitario(PedroPerplejo) AND Madrileno(PedroPerplejo))");
		
		
		//kb.tell("NOT BebeLitrona(PedroPerplejo)");
		//kb.tell("NOT Degenerado(PedroPerplejo)"); 
		//kb.tell("FORALL x (Iluso(x) OR Fanatico(x) => Asocial(x))");
		//kb.tell("((EXISTS y Feminista(y)) OR (EXISTS z Marxista(z)))");
		//kb.tell("(Iluso(PedroPerplejo) OR Fanatico(JuanDeLaCosa))");
		//kb.tell("(Iluso(PedroPerplejo) OR Fanatico(JuanDeLaCosa))");
		
		return kb;
	}	
	

	public static FOLKnowledgeBase createPortenasAlegresKnowledgeBase(
			InferenceProcedure infp) {
		// 
		FOLKnowledgeBase kb = new FOLKnowledgeBase(DomainFactory.portenasAlegresDomain(),
				infp);
		kb.tell("FORALL x (Portena(x) AND Alegre(x) => EXISTS y (Marinero(y) AND Amigo(x,y)))");
		kb.tell("FORALL x (FORALL y (Amigo(x,y) <=> Amigo(y,x)))");
		kb.tell("NOT (EXISTS x (Porteno(x) AND Feliz(x) AND (EXISTS y (Portena(y) AND Casado(x,y) AND Triste(y)))))");
		//kb.tell("FORALL x (FORALL y ((Porteno(x) AND Feliz(x) AND Portena(y) AND Casado(x,y)) => Alegre(y)))");
		kb.tell("FORALL x (Alegre(x) <=> NOT Triste(x))");
		kb.tell("FORALL x (FORALL y (Casado(x,y) <=> Casado(y,x)))");
		kb.tell("FORALL x (FORALL y (Porteno(x) AND Portena(y) AND Casado(x,y) AND EXISTS z (Amigo(y,z) AND Marinero(z))) => (Cornudo(x) OR Marinero(x)))");
		kb.tell("EXISTS x (EXISTS y (Porteno(x) AND Feliz(x) AND NOT Marinero(x) AND Portena(y) AND Casado(x,y) ))");

		return kb;
	}
	
	public static FOLKnowledgeBase createProtozoosKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(DomainFactory.protozoos(),
				infp);
		kb.tell("FORALL x (Protozoo(x) => (Pequeno(x) OR Peludo(x) OR Suave(x)))");
		kb.tell("FORALL x FORALL y ((Naturalista(x) AND NOT TieneMicroscopio(x) AND Protozoo(y) AND Observa(x,y)) => NOT Pequeno(y))");
		kb.tell("EXISTS x EXISTS y (Naturalista(x) AND Pobre(x) AND Protozoo(y) AND (NOT Suave(y)) AND Observa(x,y))");
		kb.tell("NOT EXISTS x (Pobre(x) AND TieneMicroscopio(x))");
		
		return kb;
	}
	

	public static FOLKnowledgeBase createPsicologosKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(DomainFactory.psicologos(),
				infp);
		//Ninguna persona insegura es psicologo
		kb.tell("NOT EXISTS x (PersonaInsegura(x) AND Psicologo(x))");
		//Todos los estudiosos de la conducta son psic—logos
		kb.tell("FORALL x (EstudiosoConducta(x) => Psicologo(x))");
		return kb;
	}
	

	 
	public static FOLKnowledgeBase createAnimalesDeLaSelvaKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(DomainFactory.animalesDeLaSelva(),
				infp);
		kb.tell("FORALL x (Animal(x) AND Selva(x) AND FORALL y Teme(y,x) => EXISTS w Teme(x,w))");
		kb.tell("FORALL x (EXISTS y Teme(x,y) => Teme(x,x))");
		kb.tell("FORALL x (Animal(x) => NOT Teme(x,x))");
		
		return kb;
	}

	public static FOLKnowledgeBase createPajaroEsquiadorKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(DomainFactory.pajaroEsquiador(),
				infp);
		kb.tell("FORALL x (Pajaro(x) => TienePico(x))");
		kb.tell("FORALL x ((Pajaro(x) AND NOT Avestruz(x)) => Vuela(x) )");
		kb.tell("FORALL x (Avestruz(x) => (Pajaro(x) AND NOT Vuela(x)))");
		kb.tell("FORALL x (PajaroEsquiador(x) <=> (Pajaro(x) AND SabeEsquiar(x)))");
		kb.tell("Avestruz(Pepe)");
		kb.tell("Avestruz(Juan)");
		kb.tell("SabeEsquiar(Juan)");
		kb.tell("PajaroEsquiador(Pedro)");
		//kb.tell("NOT Avestruz(Pedro)");

		return kb;
	}
	
		
	
	// ------------------------------------------------------------------- //

	public static FOLKnowledgeBase createKingsKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(DomainFactory.kingsDomain(),
				infp);
		kb.tell("((King(x) AND Greedy(x)) => Evil(x))");
		kb.tell("King(John)");
		kb.tell("King(Richard)");
		kb.tell("Greedy(John)");

		return kb;
	}

	public static FOLKnowledgeBase createWeaponsKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(
				DomainFactory.weaponsDomain(), infp);
		kb.tell("( (((American(x) AND Weapon(y)) AND Sells(x,y,z)) AND Hostile(z)) => Criminal(x))");
		kb.tell(" Owns(Nono, M1)");
		kb.tell(" Missile(M1)");
		kb.tell("((Missile(x) AND Owns(Nono,x)) => Sells(West,x,Nono))");
		kb.tell("(Missile(x) => Weapon(x))");
		kb.tell("(Enemy(x,America) => Hostile(x))");
		kb.tell("American(West)");
		kb.tell("Enemy(Nono,America)");

		return kb;
	}

	public static FOLKnowledgeBase createLovesAnimalKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(
				DomainFactory.lovesAnimalDomain(), infp);

		// Todo el que ama a los animales es amado por alguien
		kb.tell("FORALL x (FORALL y (Animal(y) => Loves(x, y)) => EXISTS y Loves(y, x))");
		// Todo el que mata a un animal no es amado por nadie
		kb.tell("FORALL x (EXISTS y (Animal(y) AND Kills(x, y)) => FORALL z NOT(Loves(z, x)))");
		// Jack ama a todos los animales
		kb.tell("FORALL x (Animal(x) => Loves(Jack, x))");
		// O Jack o la curiosidad mataron a Tuna
		kb.tell("(Kills(Jack, Tuna) OR Kills(Curiosity, Tuna))");
		// Tuna es un gato
		kb.tell("Cat(Tuna)");
		// Todos los gatos son animales
		kb.tell("FORALL x (Cat(x) => Animal(x))");

		return kb;
	}

	public static FOLKnowledgeBase createRingOfThievesKnowledgeBase(
			InferenceProcedure infp) {
		FOLKnowledgeBase kb = new FOLKnowledgeBase(
				DomainFactory.ringOfThievesDomain(), infp);

		// s(x) => ~c(x) One who skis never gets caught
		kb.tell("(Skis(x) => NOT(Caught(x)))");
		// c(x) => ~s(x) Those who are caught don't ever ski
		kb.tell("(Caught(x) => NOT(Skis(x)))");
		// p(x,y) & c(y) => s(x) Jailbird parents have skiing kids
		kb.tell("((Parent(x,y) AND Caught(y)) => Skis(x))");
		// s(x) & f(x,y) => s(y) All friends ski together
		kb.tell("(Skis(x) AND Friend(x,y) => Skis(y))");
		// f(x,y) => f(y,x) Friendship is symmetric
		kb.tell("(Friend(x,y) => Friend(y,x))");
		// FACTS
		// 1. { p(Mike,Joe) } Premise
		kb.tell("Parent(Mike, Joe)");
		// 2. { p(Janet,Joe) } Premise
		kb.tell("Parent(Janet,Joe)");
		// 3. { p(Nancy,Mike) } Premise
		kb.tell("Parent(Nancy,Mike)");
		// 4. { p(Ernie,Janet) } Premise
		kb.tell("Parent(Ernie,Janet)");
		// 5. { p(Bert,Nancy) } Premise
		kb.tell("Parent(Bert,Nancy)");
		// 6. { p(Red,Ernie) } Premise
		kb.tell("Parent(Red,Ernie)");
		// 7. { f(Red,Bert) } Premise
		kb.tell("Friend(Red,Bert)");
		// 8. { f(Drew,Nancy) } Premise
		kb.tell("Friend(Drew,Nancy)");
		// 9. { c(Mike) } Premise
		kb.tell("Caught(Mike)");
		// 10. { c(Ernie) } Premise
		kb.tell("Caught(Ernie)");

		return kb;
	}

	// Note: see -
	// http://logic.stanford.edu/classes/cs157/2008/lectures/lecture15.pdf
	// slide 12 for where this test example was taken from.
	public static FOLKnowledgeBase createABCEqualityKnowledgeBase(
			InferenceProcedure infp, boolean includeEqualityAxioms) {
		FOLDomain domain = new FOLDomain();
		domain.addConstant("A");
		domain.addConstant("B");
		domain.addConstant("C");

		FOLKnowledgeBase kb = new FOLKnowledgeBase(domain, infp);

		kb.tell("B = A");
		kb.tell("B = C");

		if (includeEqualityAxioms) {
			// Reflexivity Axiom
			kb.tell("x = x");
			// Symmetry Axiom
			kb.tell("(x = y => y = x)");
			// Transitivity Axiom
			kb.tell("((x = y AND y = z) => x = z)");
		}

		return kb;
	}

	// Note: see -
	// http://logic.stanford.edu/classes/cs157/2008/lectures/lecture15.pdf
	// slide 16,17, and 18 for where this test example was taken from.
	public static FOLKnowledgeBase createABCDEqualityAndSubstitutionKnowledgeBase(
			InferenceProcedure infp, boolean includeEqualityAxioms) {
		FOLDomain domain = new FOLDomain();
		domain.addConstant("A");
		domain.addConstant("B");
		domain.addConstant("C");
		domain.addConstant("D");
		domain.addPredicate("P");
		domain.addFunction("F");

		FOLKnowledgeBase kb = new FOLKnowledgeBase(domain, infp);

		kb.tell("F(A) = B");
		kb.tell("F(B) = A");
		kb.tell("C = D");
		kb.tell("P(A)");
		kb.tell("P(C)");

		if (includeEqualityAxioms) {
			// Reflexivity Axiom
			kb.tell("x = x");
			// Symmetry Axiom
			kb.tell("(x = y => y = x)");
			// Transitivity Axiom
			kb.tell("((x = y AND y = z) => x = z)");
			// Function F Substitution Axiom
			kb.tell("((x = y AND F(y) = z) => F(x) = z)");
			// Predicate P Substitution Axiom
			kb.tell("((x = y AND P(y)) => P(x))");
		}

		return kb;
	}
}
