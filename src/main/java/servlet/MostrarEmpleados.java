package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import datamodel.dao.EmpleadoDAO;
import datamodel.entities.Departamento;
import datamodel.entities.Empleado;
import datamodel.util.HibernateUtil;

/**
 * Servlet implementation class MostrarEmpleados
 */
@WebServlet("/MostrarEmpleados")
public class MostrarEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Session session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarEmpleados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Empleado> empleados = EmpleadoDAO.getAllEmpleados(session);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		imprimirEmpleados(out, empleados);
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void imprimirEmpleados(PrintWriter out, List<Empleado> empleados) {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Departamentos Sevlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>TABLA EMPLEADOS</h1>");
		out.println("<table border=1>");
		out.println("<tr>");
		out.println("<td>CODIGO</td>");
		out.println("<td>NOMBRE</td>");
		out.println("<td>PRIMER APELLIDO</td>");
		out.println("<td>SEGUNDO APELLIDO</td>");
		out.println("<td>LUGAR NACIMIENTO</td>");
		out.println("<td>FECHA NACIMIENTO</td>");
		out.println("<td>DIRECCIÓN</td>");
		out.println("<td>TELEFONO</td>");
		out.println("<td>PUESTO</td>");
		out.println("<td>COD DEPARTAMENTO</td>");
		out.println("</tr>");
		//Muestro los datos de tabla EMPLEADOS
		for(int i = 0; i < empleados.size(); i++) {
			Empleado e = empleados.get(i);
			out.println("<tr>");
			out.println("<td>" + e.getCodigoEmpleado() +"</td>");
			out.println("<td>" + e.getNombreEmpleado() + "</td>");
			out.println("<td>" + e.getApellido1() + "</td>");
			out.println("<td>" + e.getApellido2() + "</td>");
			out.println("<td>" + e.getLugarNacimiento() + "</td>");
			out.println("<td>" + e.getFechaNacimiento() + "</td>");
			out.println("<td>" + e.getDireccion() + "</td>");
			out.println("<td>" + e.getTelefono() + "</td>");
			out.println("<td>" + e.getPuesto() + "</td>");
			out.println("<td>" + e.getCodDepartamento() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
