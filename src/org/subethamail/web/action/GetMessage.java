/*
 * $Id$
 * $URL$
 */

package org.subethamail.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subethamail.web.Backend;
import org.subethamail.web.action.auth.AuthAction;
import org.tagonist.propertize.Property;

/**
 * Gets detail information about one piece of mail.  Model becomes a MailData.
 * 
 * @author Jeff Schnitzer
 */
public class GetMessage extends AuthAction 
{
	/** */
	@SuppressWarnings("unused")
	private final static Logger log = LoggerFactory.getLogger(GetMessage.class);

	/** */
	@Property Long msgId;

	/** */
	public void execute() throws Exception
	{
		this.getCtx().setModel(Backend.instance().getArchiver().getMail(this.msgId));
	}
}