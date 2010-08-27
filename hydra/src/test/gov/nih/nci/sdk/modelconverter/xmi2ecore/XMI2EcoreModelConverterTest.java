package gov.nih.nci.sdk.modelconverter.xmi2ecore;

import gov.nih.nci.sdk.modelconverter.util.SDKTag;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;

public class XMI2EcoreModelConverterTest {
	public static void processPackages(Collection<Package> pkgs) {
		Iterator<Package> it = pkgs.iterator();
		while(it.hasNext()) {
			Package pkg = it.next();
			processPackage(pkg);
		}
	}
	
	public static void processPackage(Package pkg) {
		System.out.println("pkg: " + pkg);
		
		List<org.eclipse.uml2.uml.Stereotype> ass = pkg.getAppliedStereotypes();
		System.out.println("ass size: " + ass.size());
		Iterator<Stereotype> itAss = ass.iterator();
		while(itAss.hasNext()) {
			Object as = itAss.next();
			System.out.println("as: " + as);
		}
		
		List<EAnnotation> eans = pkg.getEAnnotations();
		System.out.println("eans size: " + eans.size());
		Iterator<EAnnotation> itEans = eans.iterator();
		while(itEans.hasNext()) {
			Object ean = itEans.next();
			System.out.println("ean: " + ean);
		}
		
		List<Element> aoes = pkg.allOwnedElements();
		System.out.println("aoes size: " + aoes.size());
		Iterator<Element> itAoes = aoes.iterator();
		while(itAoes.hasNext()) {
			Element aoe = itAoes.next();
			System.out.println("aoe: " + aoe);
		}
		
		List<Profile> aaps = pkg.getAllAppliedProfiles();
		System.out.println("aaps size: " + aaps.size());
		Iterator<Profile> itAaps = aaps.iterator();
		while(itAaps.hasNext()) {
			Profile aap = itAaps.next();
			System.out.println("aap: " + aap.getOwner());
		}
		
		TreeIterator<EObject> tit = pkg.eAllContents();
		while(tit.hasNext()) {
			EObject eo = tit.next();
			System.out.println("eo: " + eo);
		}
		
		Model mdl = pkg.getModel();
		System.out.println("mdl: " + mdl);
		List<Profile> aps =  mdl.getAppliedProfiles();
		System.out.println("aps size: " + aps.size());
		Iterator<Profile> itAps = aps.iterator();
		while(itAps.hasNext()) {
			Profile ap = itAps.next();
			System.out.println("ap: " + ap);
		}
	}
	
	public static void processPackage_ecore(EPackage packageInterface) {
		if (packageInterface == null) {
			System.out.println("There is no EPackage.");
			return;
		}
		
		EClass eClass = null;
		Iterator<EObject> pkgIter = packageInterface.eContents().iterator();
		EObject eo = null;
		while (pkgIter.hasNext()) {
			eo = pkgIter.next();
			
			if (eo instanceof EClassImpl) {
				eClass = (EClassImpl) eo;
				System.out.println("Parsing attributes for business entity " + eClass.getName());
				EList<EStructuralFeature> attributes = eClass.getEAllStructuralFeatures();
				Iterator<EStructuralFeature> itAttr = attributes.iterator();
				while(itAttr.hasNext()) {
					EStructuralFeature attr = itAttr.next();
					System.out.println("attr: " + attr.getName());
					System.out.println("attr container: " + attr.getEContainingClass().getName());
				}
			} else if (eo instanceof EEnum) {
				System.out.println("Parsing artifacts for enumerator " + ((EEnum) eo).getName());
			} else if (eo instanceof EPackage) {
				processPackage_ecore((EPackage) eo);
			}
		}
	}

	public static void main(String[] args) {
		XMI2EcoreModelConverter test = new XMI2EcoreModelConverter();
		try {
			String pathName = "./test/gov/nih/nci/sdk/modelconverter/xmi2ecore/sdkexample.xmi";
			EPackage epkg = test.convert(pathName);
			EList annsPkg = epkg.getEAnnotations();
			System.out.println("XXXX anns: " + annsPkg.size());
			
			EAnnotation eann = new SDKTag("class.per.table.name", "ORGANIZATION");
			annsPkg.add(eann);
			System.out.println("XXXX anns: " + annsPkg.size());
			processPackage_ecore(epkg);
			
//			Collection<Package> pkgs = test.convert2UML(pathName);
//			processPackages(pkgs);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
}
