package edu.eci.arsw.blueprints.ui;

import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

public class Main {
	public static void main(String a[]) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
		String Autor = "Johan";
		try {
			//imprime los planos actuales
			System.out.println(bs.getAllBlueprints());
			Point[] pts = new Point[] { new Point(150, 150), new Point(200, 200) };
			Blueprint bp = new Blueprint(Autor, "Casa", pts);
			//agrega un nuevo plano
			bs.addNewBlueprint(bp);
			//consulta los planos con el nombre de autor
			Set<Blueprint> blueprintfound = bs.getBlueprintsByAuthor(Autor);
			System.out.println("La cantidad de planos encontrados por el nombre de autor "+ Autor+ " es:");
			System.out.println(blueprintfound.size());
			System.out.println(bs.getAllBlueprints());
		} catch (BlueprintNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BlueprintPersistenceException e) {
			System.out.println(e.getMessage());
		}
	}
}
