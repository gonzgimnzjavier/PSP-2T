package baseHilos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Departamentos implements Serializable {

	private int deptNo;
	private String dnombre;
	private String loc;
	private Set<Empleados> empleadoses = new HashSet<Empleados>(0);

	// constructores
	public Departamentos() {
	}

	public Departamentos(int deptNo) {
		this.deptNo = deptNo;
	}

	public Departamentos(int deptNo, String dnombre, String loc, Set<Empleados> empleadoses) {
		this.deptNo = deptNo;
		this.dnombre = dnombre;
		this.loc = loc;
		this.empleadoses = empleadoses;
	}

	public int getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(int i) {
		this.deptNo = i;
	}

	public String getDnombre() {
		return this.dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Set<Empleados> getEmpleadoses() {
		return this.empleadoses;
	}

	public void setEmpleadoses(Set<Empleados> empleadoses) {
		this.empleadoses = empleadoses;
	}// ..Departamentos

	@Override
	public String toString() {
		return "Departamentos [deptNo=" + deptNo + ", dnombre=" + dnombre + ", loc=" + loc + ", empleadoses="
				+ empleadoses + "]";
	}
	
	

}