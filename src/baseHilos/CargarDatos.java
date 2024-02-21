package baseHilos;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentActivationSupport;
import com.db4o.ta.TransparentPersistenceSupport;

import java.util.Date;

public class CargarDatos {

    public static void main(String[] args) {
        // Configuración para db4o
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().add(new TransparentActivationSupport());
        config.common().add(new TransparentPersistenceSupport());

        // Abre la base de datos (o la crea si no existe)
        ObjectContainer db = Db4oEmbedded.openFile(config, "EMPLEDEP.yap");

        // Crea instancias de Departamentos
        Departamentos departamento1 = new Departamentos(1, "Departamento 1", "Ubicación 1", null);
        Departamentos departamento2 = new Departamentos(2, "Departamento 2", "Ubicación 2", null);

        // Almacena los departamentos en la base de datos
        db.store(departamento1);
        db.store(departamento2);

        // Crea instancias de Empleados y asócialas a los departamentos
        Empleados empleado1 = new Empleados(1, departamento1, "Apellido1", "Oficio1", 1, new Date(), 1000.0f, 0.0f);
        Empleados empleado2 = new Empleados(2, departamento2, "Apellido2", "Oficio2", 2, new Date(), 2000.0f, 100.0f);

        // Almacena los empleados en la base de datos
        db.store(empleado1);
        db.store(empleado2);

        // Cierra la base de datos
        db.close();
    }
}
