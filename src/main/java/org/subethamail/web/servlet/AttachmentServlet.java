/*
 * $Id: ListServlet.java 378 2006-05-17 00:11:14Z jon $
 * $URL: http://subetha.tigris.org/svn/subetha/trunk/frontend/src/org/subethamail/web/servlet/ListServlet.java $
 */

package org.subethamail.web.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.subethamail.common.MailUtils;
import org.subethamail.common.NotFoundException;
import org.subethamail.core.lists.i.Archiver;
import org.subethamail.entity.i.PermissionException;

/**
 * This servlet will return the attachement.
 * 
 * The url format is something like /id/filename. The id is converted to an attachmentId and streamed from the datasource.
 */
public class AttachmentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Inject Archiver archiver;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Long attachmentId = null;
		
		String[] pathSplit = request.getPathInfo().split("/");
		
		String tmpId  = (pathSplit.length > 1) ? pathSplit[1] : null;
		//String downloadFilename = (pathSplit.length > 2) ? pathSplit[2] : null;
		
		attachmentId = Long.parseLong(tmpId);
		if (attachmentId == null)
		{
			response.setStatus(500);
			return;
		}
		
		try
		{
			String contentType = archiver.getAttachmentContentType(attachmentId);
			String name = MailUtils.getNameFromContentType(contentType);
			response.setContentType(contentType);
			
			// return images inline...
			if (contentType == null || !contentType.toLowerCase().startsWith("image")) {
				response.setHeader("Content-Disposition", " attachment; filename=\"" + name + "\"");
			}
			
			archiver.writeAttachment(attachmentId, response.getOutputStream());
		}
		catch (PermissionException pex)
		{
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/error_permission.jsp");
			request.setAttribute("javax.servlet.error.exception", pex);
			dispatcher.forward(request, response);
		}
		catch (NotFoundException nfex)
		{
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/error_notfound.jsp");
			request.setAttribute("javax.servlet.error.exception", nfex);
			dispatcher.forward(request, response);
		}
	}
}
