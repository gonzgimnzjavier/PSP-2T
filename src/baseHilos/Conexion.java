package baseHilos;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Conexion {

	final static String BDPer = "EMPLEDEP.YAP";
	static ObjectContainer db;
	static {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);
	}

	public static ObjectContainer getDBConexion() {
		return db;
	}
}
