package gov.nih.nci.sdk.ide.views;

import org.eclipse.core.runtime.IAdaptable;

class TreeObject implements IAdaptable {
	private static final int MAX_VISIBLE_LENGTH = 30;

	private String name;
	private TreeParent parent;

	public TreeObject(String name) {
		this.name = name;
	}

	public String getName() {
		return (name.length() < MAX_VISIBLE_LENGTH) ? name : ("..." + name
				.substring(name.length() - MAX_VISIBLE_LENGTH));
	}

	public void setParent(TreeParent parent) {
		this.parent = parent;
	}

	public TreeParent getParent() {
		return parent;
	}

	public String toString() {
		return getName();
	}

	public Object getAdapter(Class key) {
		return null;
	}
}