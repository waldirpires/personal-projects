package com.ericsson.msoimport.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;

import com.ericsson.msoimport.Main;
import com.ericsson.msoimport.ReadSites;
import com.ericsson.msoimport.domain.Site;
import com.ericsson.msoimport.utils.CopyResultStats;
import com.ericsson.msoimport.utils.HttpLogAppender;
import com.ericsson.msoimport.utils.StringUtils;

/**
 * Servlet implementation class CopyServlet
 */
@WebServlet("/Copy")
public class CopyServlet extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(CopyServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CopyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processDoGet(request, response);
	}

	private void processDoGet(HttpServletRequest request,
			HttpServletResponse response) {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		ServletOutputStream outstream = response.getOutputStream();
//        configureLogForCurrentRequest(outstream);
		processdoPost(request, response);
	}

	private void processdoPost(HttpServletRequest request,
			HttpServletResponse response) {
		LongProcess lp = new LongProcess();
		lp.setProgress(0);
		String srcSiteId = request.getParameter("srcSiteId");
		String destSiteId = request.getParameter("destSiteId");
		List<String> msgs = new ArrayList<String>();

		boolean containsError = false;
		String msg = null;
		if (StringUtils.isEmpty(srcSiteId)) {
			msg = "ERROR: Invalid SRC Site Group ID!";
			msgs.add(String
					.format("<font size=\"3\" color=\"red\">"+msg+"</font>"));
			LOG.error(msg);
			containsError = true;
		} else if (StringUtils.isEmpty(destSiteId)) {
			msg = "ERROR: Invalid DEST Site Group ID!";
			msgs.add(String
					.format("<font size=\"3\" color=\"red\">"+msg+"</font>"));
			containsError = true;
		} else if (srcSiteId.equals(destSiteId)) {
			msg = "ERROR: Cannot copy from SAME Site Group ID. Please change SOURCE or DESTINATION Site Group.";
			msgs.add(String
					.format("<font size=\"3\" color=\"red\">"+msg+"</font>"));
			containsError = true;
		}
		if (containsError) {
			request.setAttribute("msgs", msgs);
			request.setAttribute("color", "red");
			try {
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				ReadSites rs = new ReadSites();
				Map<String, Site> sitesMap = rs.getSitesMap();
				msg = String
						.format("Copying <br/>FROM Site Group ID <b>[%s]: %s</b> <br/>TO Site Group ID <b>[%s]: %s</b> . . .",
								srcSiteId, sitesMap.get(srcSiteId), destSiteId, sitesMap.get(destSiteId));
				msgs.add(msg);
				LOG.info(msg);
				lp.setProgress(10);
				Main main = new Main(lp);
				LOG.info("Starting Copy between site Groups . . .");
				CopyResultStats stats = main.msoCopyToSite(srcSiteId, destSiteId);
				request.setAttribute("msgs", msgs);
				request.setAttribute("color", "black");
				request.setAttribute("stats", stats);
				// response.sendRedirect("done.jsp");
				request.getRequestDispatcher("done.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
