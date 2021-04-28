package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import datamodel.entities.Departamento;
import datamodel.util.HibernateUtil;

/**
 * Servlet implementation class MostrarDatos
 */
@WebServlet("/MostrarDatos")
public class MostrarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String parameter = request.getParameter("table");
		
		//Dependiendo de lo que se le pase se llama a un servlet u otro
		if(parameter.equals("empleado")) {
			//Se muestra el servlet de MostrarEmpleados creado anteriormente
			request.getRequestDispatcher("MostrarEmpleados").forward(request, response);
		}else if(parameter.equals("departamento")) {
			//Se muestra el servlet de MostrarDepartamentos creado anteriormente
			request.getRequestDispatcher("MostrarDepartamentos").forward(request, response);
		}else {
			//Muestro una pagina de error
			mostrarError(out);
		}
		
		out.close();
		
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
		out.println("</body>");
		out.println("</html>");
	}
}
