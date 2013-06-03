/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.views;

import java.util.ArrayList;

class TreeParent extends TreeObject {
	private ArrayList<TreeObject> children;

	public TreeParent(String name) {
		super(name);
		children = new ArrayList<TreeObject>();
	}

	public void addChild(TreeObject child) {
		children.add(child);
		child.setParent(this);
	}

	public void removeChild(TreeObject child) {
		children.remove(child);
		child.setParent(null);
	}

	public TreeObject[] getChildren() {
		return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
	}
	
	public int getChildrenCount() {
		return children.size();
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}
}
