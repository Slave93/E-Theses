package rs.fon.elab.pzr.other;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetCookieServlet
 */
public class SetCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SetCookieServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * Cookie userCookie = new Cookie("someCP", "HiFromCP"); Cookie userCookie2 = new Cookie("someCP2",
		 * "HiFromCP2"); userCookie.setMaxAge(60*60*24); //Store cookie for 1 day userCookie2.setMaxAge(60*60*24);
		 * //Store cookie for 1 day response.addCookie(userCookie); response.addCookie(userCookie2); PrintWriter pr =
		 * response.getWriter(); pr.println("<h1>Cookie: "+userCookie.getName()+" was set with value "
		 * +userCookie.getValue()+"<h1>"); pr.println("<h1>Cookie: "+userCookie2.getName()+" was set with value "
		 * +userCookie2.getValue()+"<h1>");
		 */

		
		/*
		 * ProfileDAO pd = new ProfileDAOImpl(); List<Profile> lp = pd.getAllProfiles(); for (Profile p : lp) {
		 * System.out.println(p.getProfileName()); }
		 */
	}

}
