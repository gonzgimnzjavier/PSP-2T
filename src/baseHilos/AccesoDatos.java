package baseHilos;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

//ACCESO A BD db4o
public class AccesoDatos {

	static ObjectContainer db;

	//Constructor

	public AccesoDatos() {
	db = Conexion.getDBConexion();
	}
	
	// Se procesa la cadena que manda el hilo con el dep a localizar
	synchronized Departamentos procesarCadena(String str) {
			int i;
			Departamentos d = null;
			try {
			i = Integer.parseInt(str);
			} 
			catch (NumberFormatException n) {
			System.out.println("DEPARTAMENTO: " + str + "	INCORRECTO");
			return d;
			}
			Departamentos dep = new Departamentos(i, null, null, null);
			ObjectSet<Departamentos> result = db.queryByExample(dep); 
			
			if (result.size() == 0)
			System.out.println("DEPARTAMENTO: " + i + "NO EXISTE");
			else {
			d = result.next();
			return d;
			// devuelve un objeto Departamentos
			}
			}//procesarCadena
	
}//..fin AccesoDatos
