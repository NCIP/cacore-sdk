package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.common.util.JSPUtils;
import gov.nih.nci.system.web.ajax.tree.Category;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * <code>Set welcome message.</code>
 */
public class ShowDynamicTree extends BaseActionSupport {

	private static final long serialVersionUID = 1234567890L;

	private static Logger log = Logger.getLogger(ShowDynamicTree.class
			.getName());

	public String execute() throws Exception {

		if (!isAuthenticated()){
			return LOGIN;
		}		
			
		return SUCCESS;
	}

	public Category getTreeRootNode() {
		return Category.getById(1L);
	}

	public Category getClassTreeRootNode() {
		log.debug("Getting Class Tree Root Node");

		JSPUtils jspUtils = JSPUtils.getJSPUtils(servletContext);

		String packageName;
		String className;
		List packageNames = jspUtils.getPackages();
		log.debug("packages: " + packageNames);
		List classNames = new ArrayList();

		List packages = new ArrayList();
		List classes = new ArrayList();

		int pos = 0;
		int categoryId = 1;
		String klassName = null;

		Category root = new Category(categoryId++, getText("showdynamictree.root.title"), "",new Category[0]);
		if (packageNames != null) {

			Category tmpPackageCat = null;
			Category tmpClassCat = null;


			for (int i = 0; i < packageNames.size(); i++) {

				packageName = (String) packageNames.get(i);
				log.debug("packageName(" + i + "): " + packageName);

				classNames = jspUtils.getClassNames(packageName);
				if (classNames != null) {
					for (int j = 0; j < classNames.size(); j++) {
						className = (String) classNames.get(j);
						log.debug("className(" + j + "): " + className);

						pos = className.lastIndexOf(".");

						klassName = className.substring(pos + 1);
						if (klassName != null && klassName.length() <= 0) {
							//in case there is no package
							klassName = className;
						}
						
						// id just needs to be unique
						tmpClassCat = new Category(categoryId++, klassName, packageName, new Category[0]);

						
						log.debug("About to add Category: " + tmpClassCat);
						
						classes.add(tmpClassCat);
					}
				}

				tmpPackageCat = new Category(categoryId++, packageName, "", classes);

				packages.add(tmpPackageCat);
				
				//Reset classes array for the next package
				classes = new ArrayList();

			}

			root.setChildren(packages);
		}

		return root;

		// Here as an Example:

		// return new Category(1L, "Root", "", new Category[] {
		// new Category(2L, "Java", "", new Category[] {
		// new Category(3L, "Web Frameworks", "", new Category[] {
		// new Category(4L, "Struts", "", new Category[0]), new Category(7L,
		// "Stripes", "", new Category[0]), new Category(8L, "Rife", "", new
		// Category[0])
		// }), new Category(9L, "Persistence", "", new Category[] {
		// new Category(10L, "iBatis", "", new Category[0]), new Category(11L,
		// "Hibernate", "", new Category[0]), new Category(12L, "JDO", "", new
		// Category[0]), new Category(13L, "JDBC", "", new Category[0])
		// })
		// }), new Category(14L, "JavaScript", "", new Category[] {
		// new Category(15L, "Dojo", "", new Category[0]), new Category(16L,
		// "Prototype", "", new Category[0]), new Category(17L, "Scriptaculous",
		// "", new Category[0]), new Category(18L, "OpenRico", "", new
		// Category[0]), new Category(19L, "DWR", "", new Category[0])
		// })
		// });
	}

}
