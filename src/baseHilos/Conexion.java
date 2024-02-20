package baseHilos;

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
