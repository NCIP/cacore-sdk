package gov.nih.nci.system.client.util.xml;

import java.util.Set;

import gov.nih.nci.iso21090.*;
import gov.nih.nci.iso21090.grid.dto.transform.iso.*;
import gov.nih.nci.iso21090.grid.dto.transform.iso.ENTransformer.ENONTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.iso.ENTransformer.ENPNTransformer;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.log4j.Logger;
//import org.iso._21090.*;

public class JAXBISOAdapter<T1 extends org.iso._21090.ANY, T2 extends gov.nih.nci.iso21090.Any> extends XmlAdapter<T1,T2> {
	
	private static Logger log = Logger.getLogger(JAXBISOAdapter.class.getName());

	public static final String Ad_NAME="gov.nih.nci.iso21090.Ad";
	public static final String Bl_NAME="gov.nih.nci.iso21090.Bl";	
	public static final String BlNonNull_NAME="gov.nih.nci.iso21090.BlNonNull";
	public static final String Cd_NAME="gov.nih.nci.iso21090.Cd";
	public static final String DSet_NAME="gov.nih.nci.iso21090.DSet";
	public static final String Ed_NAME="gov.nih.nci.iso21090.Ed";	
	public static final String EdText_NAME="gov.nih.nci.iso21090.EdText";	
	public static final String En_NAME="gov.nih.nci.iso21090.En";	
	public static final String EnOn_NAME="gov.nih.nci.iso21090.EnOn";
	public static final String EnPn_NAME="gov.nih.nci.iso21090.EnPn";
	public static final String Ii_NAME="gov.nih.nci.iso21090.Ii";
	public static final String Int_NAME="gov.nih.nci.iso21090.Int";
	public static final String Ivl_NAME="gov.nih.nci.iso21090.Ivl";
	public static final String Pq_NAME="gov.nih.nci.iso21090.Pq";
	public static final String Real_NAME="gov.nih.nci.iso21090.Real";	
	public static final String Sc_NAME="gov.nih.nci.iso21090.Sc";	
	public static final String St_NAME="gov.nih.nci.iso21090.St";
	public static final String StNt_NAME="gov.nih.nci.iso21090.StNt";
	public static final String Tel_NAME="gov.nih.nci.iso21090.Tel";
	public static final String Tel_Email_NAME="gov.nih.nci.iso21090.TelEmail";	
	public static final String Tel_Person_NAME="gov.nih.nci.iso21090.TelPerson";
	public static final String Tel_Phone_NAME="gov.nih.nci.iso21090.TelPhone";	
	public static final String Tel_Url_NAME="gov.nih.nci.iso21090.TelUrl";
	public static final String Ts_NAME="gov.nih.nci.iso21090.Ts";		

	public static final String AD_NAME="org.iso._21090.Ad";
	public static final String BL_NAME="org.iso._21090.BL";
	public static final String BL_NONNULL_NAME="org.iso._21090.BlNonNull";
	public static final String CD_NAME="org.iso._21090.CD";
	public static final String DSETAD_NAME="org.iso._21090.DSetAd";	
	public static final String DSETCD_NAME="org.iso._21090.DSetCd";
	public static final String DSETII_NAME="org.iso._21090.DSETII";
	public static final String DSETTEL_NAME="org.iso._21090.DSetTel";
	public static final String ED_NAME="org.iso._21090.ED";	
	public static final String EDText_NAME="org.iso._21090.EdText";
	public static final String EN_NAME="org.iso._21090.EN";	
	public static final String ENON_NAME="org.iso._21090.EnOn";	
	public static final String ENPN_NAME="org.iso._21090.EnPn";
	public static final String II_NAME="org.iso._21090.Ii";
	public static final String INT_NAME="org.iso._21090.INT";
	public static final String IVLINT_NAME="org.iso._21090.IVLINT";
	public static final String IVLPQ_NAME="org.iso._21090.IVLPQ";
	public static final String IVLREAL_NAME="org.iso._21090.IVLREAL";
	public static final String IVLTS_NAME="org.iso._21090.IVLTS";
	public static final String PQ_NAME="org.iso._21090.PQ";	
	public static final String REAL_NAME="org.iso._21090.Real";	
	public static final String SC_NAME="org.iso._21090.SC";
	public static final String ST_NAME="org.iso._21090.ST";
	public static final String STNT_NAME="org.iso._21090.StNt";
	public static final String TEL_NAME="org.iso._21090.TEL";
	public static final String TEL_EMAIL_NAME="org.iso._21090.TelEmail";	
	public static final String TEL_PERSON_NAME="org.iso._21090.TELPerson";
	public static final String TEL_PHONE_NAME="org.iso._21090.TelPhone";	
	public static final String TEL_URL_NAME="org.iso._21090.TelUrl";
	public static final String TS_NAME="org.iso._21090.TS";	

	@SuppressWarnings("unchecked")
	@Override
	public T1 marshal(Any arg0) throws Exception {
		if(arg0 == null)
			return null;
		
		String className = arg0.getClass().getName();
		
		if(className.equals(Ad_NAME))
			return (T1)ADTransformer.INSTANCE.toXml((Ad)arg0);
		else if(className.equals(Bl_NAME))
			return (T1)BLTransformer.INSTANCE.toXml((Bl)arg0);
		else if(className.equals(BlNonNull_NAME))
			return (T1)BLNONNULLTransformer.INSTANCE.toXml((BlNonNull)arg0);
		else if(className.equals(Cd_NAME))
			return (T1)CDTransformer.INSTANCE.toXml((Cd)arg0);
		else if(className.equals(Cd_NAME))
			return (T1)CDTransformer.INSTANCE.toXml((Cd)arg0);
		else if(className.equals(Ed_NAME))
			return (T1)EDTransformer.INSTANCE.toXml((Ed)arg0);	
		else if(className.equals(EdText_NAME))
			return (T1)EDTextTransformer.INSTANCE.toXml((EdText)arg0);		
		else if(className.equals(En_NAME))
			return (T1)ENTransformer.EN_INSTANCE.toXml((En)arg0);	
		else if(className.equals(EnOn_NAME))
			return (T1)ENONTransformer.ENON_INSTANCE.toXml((EnOn)arg0);	
		else if(className.equals(EnPn_NAME))
			return (T1)ENPNTransformer.ENPN_INSTANCE.toXml((EnPn)arg0);
		else if(className.equals(Ii_NAME))
			return (T1)IITransformer.INSTANCE.toXml((Ii)arg0);
		else if(className.equals(Int_NAME))
			return (T1)INTTransformer.INSTANCE.toXml((Int)arg0);
		else if(className.equals(Pq_NAME))
			return (T1)PQTransformer.INSTANCE.toXml((Pq)arg0);
		else if(className.equals(Real_NAME))
			return (T1)REALTransformer.INSTANCE.toXml((Real)arg0);		
		else if(className.equals(Sc_NAME))
			return (T1)SCTransformer.INSTANCE.toXml((Sc)arg0);
		else if(className.equals(St_NAME))
			return (T1)STTransformer.INSTANCE.toXml((St)arg0);
		else if(className.equals(StNt_NAME))
			return (T1)STNTTransformer.INSTANCE.toXml((StNt)arg0);
		else if(className.equals(Tel_NAME))
			return (T1)TELTransformer.INSTANCE.toXml((Tel)arg0);
		else if(className.equals(Tel_Email_NAME))
			return (T1)TELTransformer.INSTANCE.toXml((TelEmail)arg0);		
		else if(className.equals(Tel_Person_NAME))
			return (T1)TELTransformer.INSTANCE.toXml((TelPerson)arg0);
		else if(className.equals(Tel_Phone_NAME))
			return (T1)TELTransformer.INSTANCE.toXml((TelPhone)arg0);		
		else if(className.equals(Tel_Url_NAME))
			return (T1)TELTransformer.INSTANCE.toXml((TelUrl)arg0);
		else if(className.equals(Ts_NAME))
			return (T1)TSTransformer.INSTANCE.toXml((Ts)arg0);			
		else {
			log.debug(" * * * Class " + className + " did not match any ISO Datatype classes, and will be ignored.");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T2 unmarshal(org.iso._21090.ANY arg0) throws Exception {
		if(arg0 == null)
			return null;
		
		String className = arg0.getClass().getName();

		if (className.equals(AD_NAME))
			return (T2)ADTransformer.INSTANCE.toDto((org.iso._21090.Ad)arg0);
		else if(className.equals(BL_NAME))
			return (T2)BLTransformer.INSTANCE.toDto((org.iso._21090.BL)arg0);
		else if(className.equals(BL_NONNULL_NAME))
			return (T2)BLNONNULLTransformer.INSTANCE.toDto((org.iso._21090.BlNonNull)arg0);	
		else if(className.equals(CD_NAME))
			return (T2)CDTransformer.INSTANCE.toDto((org.iso._21090.CD)arg0);
		else if(className.equals(ED_NAME))
			return (T2)EDTransformer.INSTANCE.toDto((org.iso._21090.ED)arg0);
		else if(className.equals(EDText_NAME))
			return (T2)EDTextTransformer.INSTANCE.toDto((org.iso._21090.EdText)arg0);			
		else if(className.equals(EN_NAME))
			return (T2)ENTransformer.EN_INSTANCE.toDto((org.iso._21090.EN)arg0);	
		else if(className.equals(ENON_NAME))
			return (T2)ENONTransformer.ENON_INSTANCE.toDto((org.iso._21090.EnOn)arg0);		
		else if(className.equals(ENPN_NAME))
			return (T2)ENTransformer.ENPN_INSTANCE.toDto((org.iso._21090.EnPn)arg0);
		else if(className.equals(II_NAME))
			return (T2)IITransformer.INSTANCE.toDto((org.iso._21090.Ii)arg0);
		else if(className.equals(INT_NAME))
			return (T2)INTTransformer.INSTANCE.toDto((org.iso._21090.INT)arg0);
		else if(className.equals(PQ_NAME))
			return (T2)PQTransformer.INSTANCE.toDto((org.iso._21090.PQ)arg0);
		else if(className.equals(REAL_NAME))
			return (T2)REALTransformer.INSTANCE.toDto((org.iso._21090.Real)arg0);		
		else if(className.equals(SC_NAME))
			return (T2)SCTransformer.INSTANCE.toDto((org.iso._21090.SC)arg0);
		else if(className.equals(ST_NAME))
			return (T2)STTransformer.INSTANCE.toDto((org.iso._21090.ST)arg0);
		else if(className.equals(STNT_NAME))
			return (T2)STNTTransformer.INSTANCE.toDto((org.iso._21090.StNt)arg0);
		else if(className.equals(TEL_NAME))
			return (T2)TELTransformer.INSTANCE.toDto((org.iso._21090.TEL)arg0);
		else if(className.equals(TEL_EMAIL_NAME))
			return (T2)TELTransformer.INSTANCE.toDto((org.iso._21090.TelEmail)arg0);		
		else if(className.equals(TEL_PERSON_NAME))
			return (T2)TELTransformer.INSTANCE.toDto((org.iso._21090.TELPerson)arg0);
		else if(className.equals(TEL_PHONE_NAME))
			return (T2)TELTransformer.INSTANCE.toDto((org.iso._21090.TelPhone)arg0);		
		else if(className.equals(TEL_URL_NAME))
			return (T2)TELTransformer.INSTANCE.toDto((org.iso._21090.TelUrl)arg0);	
		else if(className.equals(TS_NAME))
			return (T2)TSTransformer.INSTANCE.toDto((org.iso._21090.TS)arg0);			
		else {
			log.debug(" * * * Class " + className + " did not match any ISO Datatype classes.");
			return null;
		}
	}
}
