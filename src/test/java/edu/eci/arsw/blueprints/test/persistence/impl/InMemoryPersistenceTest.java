/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filtros.FiltroRedundancia;
import edu.eci.arsw.blueprints.filtros.FiltroSubmuestreo;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
        
    }
    @Test
    public void getBlueprintTest(){
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Point[] pts = new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp = new Blueprint("Pedro", "Pablo",pts);
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException e) {
            fail("InMemoryBluePrintsPersistence save error.");
        }
        Blueprint resultBp=null;
        try {
            resultBp = ibpp.getBlueprint("Pedro","Pablo");
        } catch (BlueprintNotFoundException e) {
            fail("InMemoryBluePrintsPersistence get error.");
        }
        assertEquals(resultBp,bp);
    }
    
    @Test
    public void getBlueprintByAuthorTest(){
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts1 = new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp1 = new Blueprint("Raul", "Perez",pts1);
        Point[] pts2 = new Point[]{new Point(0, 45),new Point(45, 10)};
        Blueprint bp2 = new Blueprint("Raul", "Acosta",pts2);
        Set<Blueprint> authorBlueprints = new HashSet<>();
        authorBlueprints.add(bp1);
        authorBlueprints.add(bp2);

        try {
            ibpp.saveBlueprint(bp1);
            ibpp.saveBlueprint(bp2);
            //ibpp.saveBlueprint(bp3);
        } catch (BlueprintPersistenceException ex) {
            fail("InMemoryBluePrintsPersistence save error.");
        }
        try {
			assertEquals(ibpp.getSetBlueSprints("Raul"),authorBlueprints);
			//System.out.println("Entro");
		} catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testRedundancia() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence persistencia=new InMemoryBlueprintPersistence();
        FiltroRedundancia filtro = new FiltroRedundancia();

        Point[] lpuntos1=new Point[]{new Point(10, 10),new Point(12, 12), new Point(10, 10), new Point(10, 10), new Point(12, 12), new Point(50, 50)};
        Blueprint user1=new Blueprint("Pablo", "Perez",lpuntos1);

        Point[] lpuntos2=new Point[]{new Point(10, 10), new Point(50, 50)};//lista de prueba sin puntos repetidos
        Blueprint user2=new Blueprint("Felipe", "Roman",lpuntos2);
        persistencia.saveBlueprint(user1);
        persistencia.saveBlueprint(user2);
        Blueprint bpToTest = filtro.filtrar(persistencia.getBlueprint("Pablo", "Perez"));
        assertEquals(user2.getPoints().get(0).getX(),bpToTest.getPoints().get(0).getX()); 
    }

    @Test
    public void testSubmuestreo() throws BlueprintPersistenceException, BlueprintNotFoundException{
    	InMemoryBlueprintPersistence persistencia=new InMemoryBlueprintPersistence();
    	FiltroSubmuestreo filtro = new FiltroSubmuestreo();
    	Point[] lpuntos1=new Point[]{new Point(10, 10),new Point(20, 20), new Point(30, 30), new Point(40, 40), new Point(50, 50), new Point(60, 60)};
        Blueprint user1=new Blueprint("Pablo", "Perez",lpuntos1);
        Point[] lpuntos2=new Point[]{new Point(20, 20),new Point(40, 40), new Point(60, 60)};
        Blueprint user2=new Blueprint("Felipe", "Roman",lpuntos2);
        persistencia.saveBlueprint(user1);
        persistencia.saveBlueprint(user2);
        Blueprint bpToTest = filtro.filtrar(persistencia.getBlueprint("Pablo", "Perez"));
        assertEquals(user2.getPoints().get(0).getX(),bpToTest.getPoints().get(0).getX()); 
    }
 
   

    
}
