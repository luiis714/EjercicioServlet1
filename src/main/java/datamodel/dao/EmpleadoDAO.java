package datamodel.dao;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import datamodel.entities.Empleado;

public class EmpleadoDAO {
	
	/**Método que inserta un empleado en la BD*/
	public static void insertEmpleado(Session s, Empleado empleado) {
		
		s.save(empleado);
		//logger.info("Empleado insertado. " + empleado.toString());
		
	}
	
	/**Método que devuelve una lista con todos los empleados*/
	public static List<Empleado> getAllEmpleados(Session s){
		String hQuery = "from Empleado";
		List<Empleado> listaEmpleados = s.createQuery(hQuery, Empleado.class)
				   	   			           	.list();
		return listaEmpleados;
	}
	
	/**Método que devuelve un empleado a través de su codigo*/
	public static Empleado getEmpleado(Session s, int codEmpleado) {
		String hQuery = " from Empleado e " +
                " where e.codigoEmpleado = :codEmpleado";
		
		Empleado empleado= s.createQuery(hQuery, Empleado.class)
									.setParameter("codEmpleado", codEmpleado)
									.setMaxResults(1)
									.uniqueResult();
		return empleado;
	}

	/**Método que devuelve una lista de empleados a través del codigo del departamento
	 * */
	public static List<Empleado> getEmpleados_Departamento(Session session, int codDepartamento) {
		String hQuery = " from Empleado e " +
                " where e.codDepartamento = :codDepartamento";
		
		List<Empleado> listaEmpleados = session.createQuery(hQuery, Empleado.class)
												.setParameter("codDepartamento", codDepartamento)
												.list();
		
		return listaEmpleados;
	}

	/**Devuelve una lista de todos los empleados mayores a una edad pasada por parametro*/
	public static List<Empleado> getAllEmpleados(Session session, int edad) {
		Criteria criteria = session.createCriteria(Empleado.class);
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);//Año actual
		
		String diff = Integer.toString(year - edad);//Año en el que nacio
		
		//Coge los dos últimos digitos y los añade a un String que pone el primer dia del primer mes
		String yearDiff = "01-01-" + diff.substring(2);
		
		
		
		List<Empleado> empleados = criteria.list();
		
		return empleados;
	}
}
