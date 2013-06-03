/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.core.ModelPackageVO;
import gov.nih.nci.sdk.util.EcoreUtil;
import gov.nih.nci.sdk.util.SDKUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

public class SDKModelExplorerUtil {
	
	public static List<ModelPackageVO> getModelPackages(EPackage rootEPackage) {
		if (rootEPackage == null) return null;
		
		List<ModelPackageVO> list = new ArrayList<ModelPackageVO>();
		Map<String, SortedSet<String>> map = EcoreUtil.getAllDomainPackageClassNamesMap(rootEPackage);
		for (Map.Entry<String, SortedSet<String>> entry: map.entrySet()) {
			String packageName = entry.getKey();
			SortedSet<String> classNames = entry.getValue();
			ModelPackageVO mpVO = new ModelPackageVO();
			mpVO.setPackageName(packageName);
			mpVO.setModels(classNames);
			list.add(mpVO);
		}
		return list;
	}
	
	/**
	 * Converts a set of objects to a list of objects.
	 * @param <T>
	 * 
	 * @param set the set to be converted.
	 * @return list of objects
	 */
	public static <T> List<T> convertToList(Set<T> set) {
		if (set == null) return null;
		List<T> list = new ArrayList<T>(set.size());
		Iterator<T> it = set.iterator();
		while(it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}
	
	public static String getDomainName(ModelSelectionEvent event) {
		if (event == null) return null;
		EClass eClass = SDKUIManager.getInstance().getEClass(event.getFullModelName());	
		return SDKUtil.getTagValue(eClass, "class.mea.domain");
	}
}
