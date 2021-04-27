package datamodel.dao;

import java.util.List;
import org.hibernate.Session;

import datamodel.entities.Departamento;

public class DepartamentoDAO {
	
	
	public static void insertDepartamento(Session s, Departamento departamento) {
		s.save(departamento);
	}
	
	public static List<Departamento> getAllDepartamentos (Session s){
		String hQuery = "from Departamento";
		List<Departamento> listaDepartamentos = s.createQuery(hQuery, Departamento.class)
				   	   			           	.list();
		return listaDepartamentos;
	}
	
	public static Departamento getDepartamento(Session s, int codDepartamento) {
		String hQuery = " from Departamento d " +
                " where d.codigoDepartamento = :codDepartamento";
		
		Departamento departamento = s.createQuery(hQuery, Departamento.class)
									.setParameter("codDepartamento", codDepartamento)
									.setMaxResults(1)
									.uniqueResult();
		return departamento;
	}
}
