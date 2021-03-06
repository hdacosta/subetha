/*
 * $Id$
 * $URL$
 */

package org.subethamail.web.action;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.subethamail.core.lists.i.ListDataPlus;
import org.subethamail.web.Backend;
import org.subethamail.web.action.auth.AuthRequired;
import org.subethamail.web.model.PaginateModel;

/**
 * Searches all lists for a query term (if provided), or
 * just gets basic list if no term provided.  Results
 * are paginated.
 * 
 * @author Jon Stevens
 * @author Jeff Schnitzer
 */
public class GetLists extends AuthRequired 
{
	public static class Model extends PaginateModel
	{
		/** */
		@Getter @Setter String query = "";
		@Getter @Setter List<ListDataPlus> lists;
	}
	
	public void initialize()
	{
		this.getCtx().setModel(new Model());
	}

	/** */
	public void authExecute() throws Exception
	{
		Model model = (Model)this.getCtx().getModel();
		
		if (model.query.trim().length() == 0)
		{
			model.lists = Backend.instance().getAdmin().getListsPlus(model.getSkip(), model.getCount());
			model.setTotalCount(Backend.instance().getAdmin().countLists());
		}
		else
		{
			model.lists = Backend.instance().getAdmin().searchListsPlus(model.query, model.getSkip(), model.getCount());
			model.setTotalCount(Backend.instance().getAdmin().countListsQuery(model.query));
			
			// If we are doing a query, then we need to find out how many results would
			// have been returned for our query (before the limit was applied) in 
			// order to do the pagination right.
			//
			// this is highly inefficient as we are doing a full table scan again.
			// Since we know and love MySQL, it would be better to do something like this:
			// http://www.mysqlfreaks.com/statements/101.php
			//			SELECT SQL_CALC_FOUND_ROWS *
			//			FROM tbl_name
			//			WHERE id > 100 LIMIT 10;
			//
			//			SELECT FOUND_ROWS();
			//
		}
	}
}
