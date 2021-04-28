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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datamodel.dao.DepartamentoDAO;
import datamodel.entities.Departamento;
import datamodel.util.HibernateUtil;

/**
 * Servlet implementation class MostrarDepartamentos
 */
@WebServlet("/MostrarDepartamentos")
public class MostrarDepartamentos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger logger = LogManager.getLogger(MostrarDatos.class);
	
	private Session session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarDepartamentos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		session = HibernateUtil.getSessionFactory().openSession();
		logger.info("Creo la sesión. Conexión con la BBDD. Desde MostraDepartamentos");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Departamento> departamentos = DepartamentoDAO.getAllDepartamentos(session);
		logger.info("Recupero la lista de departamentos");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		logger.info("Muestro la página que muestra la lista");
		imprimirDepartamentos(out, departamentos);
		
		out.close();
		logger.info("Acaba la función del Servlet MuestraDepartamentos");
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void imprimirDepartamentos(PrintWriter out, List<Departamento> departamentos) {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Departamentos Sevlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>TABLA DEPARTAMENTOS</h1>");
		out.println("<table border=1>");
		out.println("<tr>");
		out.println("<td>CODIGO</td>");
		out.println("<td>NOMBRE</td>");
		out.println("<td>COD RESPONSABLE</td>");
		out.println("</tr>");
		//Muestro los datos de tabla departamentos
		for(int i = 0; i < departamentos.size(); i++) {
			Departamento d = departamentos.get(i);
			logger.info("Muestra departamento: " + d.toString());
			
			out.println("<tr>");
			out.println("<td>" + d.getCodigoDepartamento() + "</td>");
			out.println("<td>" + d.getNombreDepartamento() + "</td>");
			out.println("<td>" + d.getCodResponsable() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}
}
