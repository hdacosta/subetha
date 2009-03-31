/*
 * $Id$
 * $URL$
 */

package org.subethamail.web.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * For i18n reasons, we need to set the character encoding on 
 * all requests into the server.
 * 
 * @author Jon Stevens
 */
public class SetRequestCharsetFilter extends AbstractFilter
{
	/** */
	private static Log log = LogFactory.getLog(SetRequestCharsetFilter.class);
	
	/**
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		if (log.isDebugEnabled())
			log.debug("*** Starting filter for " + request.getRequestURI());
		
		if (request.getCharacterEncoding() == null)
			request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}
}