package gov.nih.nci.system.web.ajax.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

/**
 */
public class Category
{

    public static Category getById(String id)
    {
        return (Category)catMap.get(id);
    }

    public Category(String id, String name, String packageName, Category children[])
    {
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.children = new ArrayList();
        Category arr[] = children;
        int len = arr.length;
        for(int i = 0; i < len; i++)
        {
            Category child = arr[i];
            this.children.add(child);
        }

        catMap.put(id, this);
    }

    public Category(String id, String name, String packageName, List children)
    {
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.children = children;

        catMap.put(id, this);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List getChildren()
    {
        return children;
    }

    public void setChildren(List children)
    {
        this.children = children;
    }

    public void toggle()
    {
        toggle = !toggle;
    }

    public boolean isToggle()
    {
        return toggle;
    }

    private static Map catMap = new HashMap();
    private String id;
    private String packageName;
    private String name;
    private List children;
    private boolean toggle;

    static
    {
        new Category("1", "Root", "", new Category[] {
            new Category("2", "Java", "", new Category[] {
                new Category("3", "Web Frameworks", "", new Category[] {
                    new Category("4", "Struts", "", new Category[0]), new Category("7", "Stripes", "", new Category[0]), new Category("8", "Rife", "", new Category[0])
                }), new Category("9", "Persistence", "", new Category[] {
                    new Category("10", "iBatis", "", new Category[0]), new Category("11", "Hibernate", "", new Category[0]), new Category("12", "JDO", "", new Category[0]), new Category("13", "JDBC", "", new Category[0])
                })
            }), new Category("14", "JavaScript", "", new Category[] {
                new Category("15", "Dojo", "", new Category[0]), new Category("16", "Prototype", "", new Category[0]), new Category("17", "Scriptaculous", "", new Category[0]), new Category("18", "OpenRico", "", new Category[0]), new Category("19", "DWR", "", new Category[0])
            })
        });
    }

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String toString() {
		return 	"Category id: " + this.id + " ||\n " +
				"Category name: " + this.name + " ||\n " +
				"Category packageName: " + this.packageName;
	}


	public String getTreeData()
	{
		StringBuffer treeData = new StringBuffer();
		String rootNodeStr = "{ id: '"+getId()+"', name: '"+getName()+"'}";

		treeData.append(rootNodeStr);
		List children = getChildren();
		Iterator iter = children.iterator();
		while(iter.hasNext())
		{
			Category childCategory = (Category) iter.next();
			String nodeStr = "{ id: '"+childCategory.getId()+"', name: '"+childCategory.getName()+"', parent: '"+getId()+"'}";
			treeData.append(",");
			treeData.append(nodeStr);

			List grandChildren = childCategory.getChildren();
			Iterator childIter = grandChildren.iterator();
			while(childIter.hasNext())
			{
				Category childChildCategory = (Category) childIter.next();
				String childNodeStr = "{ id: '"+childChildCategory.getId()+"', name: '"+childChildCategory.getName()+"', parent: '"+childCategory.getId()+"'}";
				treeData.append(",");
				treeData.append(childNodeStr);
			}
		}
		return treeData.toString();
	}


}

