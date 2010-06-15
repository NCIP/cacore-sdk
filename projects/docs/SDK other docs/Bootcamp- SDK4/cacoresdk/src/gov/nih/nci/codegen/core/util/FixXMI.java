package gov.nih.nci.codegen.core.util;

import gov.nih.nci.codegen.framework.XMIPreprocessor;

public class FixXMI {

	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			throw new Exception("XMI preprocessor requires three parameters");
		} else {
			try {
				XMIPreprocessor f = (XMIPreprocessor) Class.forName(args[0]).newInstance();
				f.fix(args[1], args[2]);
			} catch (Exception e) {
				throw new Exception("XMI Preprocessor failed",e);
			}
		}
	}

}