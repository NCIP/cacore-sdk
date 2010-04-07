package gov.nih.nci.codegen.util;

import java.util.ArrayList;
import java.util.List;

public class ComplexNode extends Node
{

	private List<Node> innerNodes = new ArrayList<Node>();

	public ComplexNode(String name)
	{
		nodeType = "complex";
		this.name = name;
	}

	public List<Node> getInnerNodes()
	{
		return innerNodes;
	}

	public void setInnerNodes(List<Node> innerNodes)
	{
		this.innerNodes = innerNodes;
	}
	
	public void addInnerNode(Node node)
	{
		innerNodes.add(node);
	}

	
}
