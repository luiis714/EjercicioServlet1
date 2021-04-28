package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import datamodel.entities.Departamento;
import datamodel.util.HibernateUtil;

/**
 * Servlet implementation class MostrarDatos
 */
@WebServlet("/MostrarDatos")
public class MostrarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger logger = LogManager.getLogger(MostrarDatos.class);
	
	private Session session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		session = HibernateUtil.getSessionFactory().openSession();
		logger.info("Creo la sesión. Conexión con la BBDD. Desde MostrarDatos");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String parameter = request.getParameter("table");
		logger.info("Recojo el parametro que es: " + parameter);
		
		//Dependiendo de lo que se le pase se llama a un servlet u otro
		if(parameter.equals("empleado")) {
			logger.info("Muestra la tabla de empleados");
			//Se muestra el servlet de MostrarEmpleados creado anteriormente
			request.getRequestDispatcher("MostrarEmpleados").forward(request, response);
		}else if(parameter.equals("departamento")) {
			logger.info("Muestra la tabla de departamentos");
			//Se muestra el servlet de MostrarDepartamentos creado anteriormente
			request.getRequestDispatcher("MostrarDepartamentos").forward(request, response);
		}else {
			logger.info("Muestra la página de error. No se ha indicado un parametro correcto");
			//Muestro una pagina de error
			mostrarError(out);
		}
		
		out.close();
		logger.info("Acaba la función del Servlet MuestraDatos");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**Metodo que muestra una pagina de error*/
	private void mostrarError(PrintWriter out) {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Error Sevlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>ERROR. NO SE PUEDEN MOSTRAR DATOS</h1>");
		out.println("<h3>No se ha indicado un parametro correcto</h3>");
		out.println("</body>");
		out.println("</html>");
	}
}
