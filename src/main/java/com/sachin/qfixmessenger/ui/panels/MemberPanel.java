package com.sachin.qfixmessenger.ui.panels;

import com.sachin.fix.model.Member;

/**
 * MemberPanel
 * <p>
 * Common interface for all member panels.
 * </p>
 * 

 */
public interface MemberPanel<M extends Member, Q, X>
{
	/**
	 * Returns the FIX String representation of this MemberPanel
	 * 
	 * @return the FIX String representation of this MemberPanel
	 */
	String getFixString();

	/**
	 * Returns the member
	 * 
	 * @return the member
	 */
	M getMember();

	/**
	 * Returns the QuickFIX representation of this MemberPanel
	 * 
	 * @return the QuickFIX representation of this MemberPanel
	 */
	Q getQuickFixMember();

	/**
	 * Returns the XML representation of this MemberPanel
	 * 
	 * @return the XML representation of this MemberPanel
	 */
	X getXmlMember();

	/**
	 * Returns whether the contents of this MemberPanel has valid format
	 * 
	 * @return whether the contents of this MemberPanel has valid format
	 */
	boolean hasValidFormat();

	/**
	 * Populates this MemberPanel from its XML representation
	 * 
	 * @param xmlObject
	 *            an XML representation
	 */
	void populateXml(X xmlObject);
}
