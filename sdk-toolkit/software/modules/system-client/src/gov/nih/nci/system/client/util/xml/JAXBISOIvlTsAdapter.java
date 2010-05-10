package gov.nih.nci.system.client.util.xml;

import gov.nih.nci.iso21090.*;
import gov.nih.nci.iso21090.grid.dto.transform.iso.IVLTSTransformer;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.iso._21090.ANY;
import org.iso._21090.IVLTS;

public class JAXBISOIvlTsAdapter<T1 extends org.iso._21090.ANY, T2 extends gov.nih.nci.iso21090.Any> extends XmlAdapter<T1,T2> {
	
	@SuppressWarnings("unchecked")
	@Override
	public ANY marshal(Any arg0) throws Exception {
		if(arg0 == null)
			return null;

		return (org.iso._21090.ANY)IVLTSTransformer.INSTANCE.toXml((Ivl<Ts>)arg0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Any unmarshal(org.iso._21090.ANY arg0) throws Exception {
		if(arg0 == null)
			return null;

		return (gov.nih.nci.iso21090.Any)IVLTSTransformer.INSTANCE.toDto((IVLTS)arg0);
		
	}
	
}
