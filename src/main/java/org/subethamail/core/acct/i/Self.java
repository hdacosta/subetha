package org.subethamail.core.acct.i;

import java.util.List;


/**
 * Some detail about the current user, suitable for display on
 * a page for editing data.
 *
 * @author Jeff Schnitzer
 */
public class Self extends PersonData
{
	private static final long serialVersionUID = 1L;

	protected List<SubscribedList> subscriptions;
	
	/** */
	boolean siteAdmin;

	protected Self()
	{
		// http://forums.java.net/jive/thread.jspa?threadID=26539&tstart=0
	}

	/**
	 */
	public Self(Long id, 
				String name,
				List<String> emailAddresses,
				boolean siteAdmin,
				List<SubscribedList> subscriptions)
	{
		super(id, name, emailAddresses);
		
		this.siteAdmin = siteAdmin;
		this.subscriptions = subscriptions;
	}

	/** */
	public boolean isSiteAdmin()
	{
		return this.siteAdmin;
	}
	
	public List<SubscribedList> getSubscriptions()
	{
		return this.subscriptions;
	}
}
