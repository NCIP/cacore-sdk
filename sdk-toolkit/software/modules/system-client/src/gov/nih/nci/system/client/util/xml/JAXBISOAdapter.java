package gov.nih.nci.system.client.util.xml;

import gov.nih.nci.iso21090.*;
import gov.nih.nci.iso21090.grid.dto.transform.iso.*;
import gov.nih.nci.iso21090.grid.dto.transform.iso.ENTransformer.ENONTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.iso.ENTransformer.ENPNTransformer;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.log4j.Logger;
import org.iso._21090.*;

public class JAXBISOAdapter extends XmlAdapter<ANY, gov.nih.nci.iso21090.Any> {
	
	private static Logger log = Logger.getLogger(JAXBISOAdapter.class.getName());
	
//	put("EN", "En");
//	put("PQV", "Pqv");
	
//	put("IVL<INT>", "Ivl<Int>");
//	put("IVL<REAL>", "Ivl<Real>");
//	put("IVL<TS>", "Ivl<Ts>");
//	put("IVL<PQV>", "Ivl<Pqv>");
//	put("IVL<PQ>", "Ivl<Pq>");
//	put("DSET<II>", "DSet<Ii>");
//	put("DSET<TEL>", "DSet<Tel>");
//	put("DSET<CD>", "DSet<Cd>");
//	put("DSET<AD>", "DSet<Ad>");

	public static final String Ad_NAME="gov.nih.nci.iso21090.Ad";
	public static final String Bl_NAME="gov.nih.nci.iso21090.Bl";	
	public static final String BlNonNull_NAME="gov.nih.nci.iso21090.BlNonNull";
	public static final String Cd_NAME="gov.nih.nci.iso21090.Cd";
	public static final String Ed_NAME="gov.nih.nci.iso21090.Ed";	
	public static final String EdText_NAME="gov.nih.nci.iso21090.EdText";	
//	public static final String En_NAME="gov.nih.nci.iso21090.En";	
	public static final String EnOn_NAME="gov.nih.nci.iso21090.EnOn";
	public static final String EnPn_NAME="gov.nih.nci.iso21090.EnPn";
	public static final String Ii_NAME="gov.nih.nci.iso21090.Ii";
	public static final String Int_NAME="gov.nih.nci.iso21090.Int";	
	public static final String Pq_NAME="gov.nih.nci.iso21090.Pq";
//	public static final String Pqv_NAME="gov.nih.nci.iso21090.Pqv";		
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

	public static final String AD_NAME="org.iso._21090.AD";
	public static final String BL_NAME="org.iso._21090.BL";
	public static final String BL_NONNULL_NAME="org.iso._21090.BL.NONNULL";
	public static final String CD_NAME="org.iso._21090.CD";
	public static final String ED_NAME="org.iso._21090.ED";	
	public static final String EDText_NAME="org.iso._21090.EDText";
//	public static final String EN_NAME="org.iso._21090.EN";	
	public static final String ENON_NAME="org.iso._21090.ENON";	
	public static final String ENPN_NAME="org.iso._21090.ENPN";
	public static final String II_NAME="org.iso._21090.II";
	public static final String INT_NAME="org.iso._21090.INT";	
	public static final String PQ_NAME="org.iso._21090.PQ";	
//	public static final String PQV_NAME="org.iso._21090.PQV";		
	public static final String REAL_NAME="org.iso._21090.REAL";	
	public static final String SC_NAME="org.iso._21090.SC";
	public static final String ST_NAME="org.iso._21090.ST";
	public static final String STNT_NAME="org.iso._21090.STNT";
	public static final String TEL_NAME="org.iso._21090.TEL";
	public static final String TEL_EMAIL_NAME="org.iso._21090.TELEmail";	
	public static final String TEL_PERSON_NAME="org.iso._21090.TELPerson";
	public static final String TEL_PHONE_NAME="org.iso._21090.TELPhone";	
	public static final String TEL_URL_NAME="org.iso._21090.TELUrl";
	public static final String TS_NAME="org.iso._21090.TS";	

	@Override
	public ANY marshal(Any arg0) throws Exception {
		if(arg0 == null)
			return null;
		
		String className = arg0.getClass().getName();
		
		if(className.equals(Ad_NAME))
			return ADTransformer.INSTANCE.toXml((Ad)arg0);
		else if(className.equals(Bl_NAME))
			return BLTransformer.INSTANCE.toXml((Bl)arg0);
		else if(className.equals(BlNonNull_NAME))
			return BLNONNULLTransformer.INSTANCE.toXml((BlNonNull)arg0);
		else if(className.equals(Cd_NAME))
			return CDTransformer.INSTANCE.toXml((Cd)arg0);
		else if(className.equals(Ed_NAME))
			return EDTransformer.INSTANCE.toXml((Ed)arg0);	
		else if(className.equals(EdText_NAME))
			return EDTextTransformer.INSTANCE.toXml((EdText)arg0);		
//		else if(className.equals(En_NAME))
//			return ENTransformer..toXml((En)arg0);	
		else if(className.equals(EnOn_NAME))
			return ENONTransformer.ENON_INSTANCE.toXml((EnOn)arg0);	
		else if(className.equals(EnPn_NAME))
			return ENPNTransformer.ENPN_INSTANCE.toXml((EnPn)arg0);
		else if(className.equals(Ii_NAME))
			return IITransformer.INSTANCE.toXml((Ii)arg0);
		else if(className.equals(Int_NAME))
			return INTTransformer.INSTANCE.toXml((Int)arg0);		
		else if(className.equals(Pq_NAME))
			return PQTransformer.INSTANCE.toXml((Pq)arg0);
//		else if(className.equals(Pqv_NAME))
//			return QTYTransformer.INSTANCE.toXml((Pqv)arg0);		
		else if(className.equals(Real_NAME))
			return REALTransformer.INSTANCE.toXml((Real)arg0);		
		else if(className.equals(Sc_NAME))
			return SCTransformer.INSTANCE.toXml((Sc)arg0);
		else if(className.equals(St_NAME))
			return STTransformer.INSTANCE.toXml((St)arg0);
		else if(className.equals(StNt_NAME))
			return STNTTransformer.INSTANCE.toXml((StNt)arg0);
		else if(className.equals(Tel_NAME))
			return TELTransformer.INSTANCE.toXml((Tel)arg0);
		else if(className.equals(Tel_Email_NAME))
			return TELTransformer.INSTANCE.toXml((TelEmail)arg0);		
		else if(className.equals(Tel_Person_NAME))
			return TELTransformer.INSTANCE.toXml((TelPerson)arg0);
		else if(className.equals(Tel_Phone_NAME))
			return TELTransformer.INSTANCE.toXml((TelPhone)arg0);		
		else if(className.equals(Tel_Url_NAME))
			return TELTransformer.INSTANCE.toXml((TelUrl)arg0);
		else if(className.equals(Ts_NAME))
			return TSTransformer.INSTANCE.toXml((Ts)arg0);			
		else {
			log.debug(" * * * Class " + className + " did not match any ISO Datatype classes.");
			return null;
		}
	}

	@Override
	public Any unmarshal(ANY arg0) throws Exception {
		if(arg0 == null)
			return null;
		
		String className = arg0.getClass().getName();

		if (className.equals(AD_NAME))
			return ADTransformer.INSTANCE.toDto((AD)arg0);
		else if(className.equals(BL_NAME))
			return BLTransformer.INSTANCE.toDto((BL)arg0);
		else if(className.equals(BL_NONNULL_NAME))
			return BLNONNULLTransformer.INSTANCE.toDto((BLNonNull)arg0);	
		else if(className.equals(CD_NAME))
			return CDTransformer.INSTANCE.toDto((CD)arg0);	
		else if(className.equals(ED_NAME))
			return EDTransformer.INSTANCE.toDto((ED)arg0);
		else if(className.equals(EDText_NAME))
			return EDTextTransformer.INSTANCE.toDto((EDText)arg0);			
//		else if(className.equals(EN_NAME))
//			return ENTransformer.ENPN_INSTANCE.toDto((EN)arg0);	
		else if(className.equals(ENON_NAME))
			return ENONTransformer.ENON_INSTANCE.toDto((ENON)arg0);		
		else if(className.equals(ENPN_NAME))
			return ENTransformer.ENPN_INSTANCE.toDto((ENPN)arg0);
		else if(className.equals(II_NAME))
			return IITransformer.INSTANCE.toDto((II)arg0);
		else if(className.equals(INT_NAME))
			return INTTransformer.INSTANCE.toDto((INT)arg0);		
		else if(className.equals(PQ_NAME))
			return PQTransformer.INSTANCE.toDto((PQ)arg0);
//		else if(className.equals(PQV_NAME))
//			return PQTransformer.INSTANCE.toDto((PQV)arg0);		
		else if(className.equals(REAL_NAME))
			return REALTransformer.INSTANCE.toDto((REAL)arg0);		
		else if(className.equals(SC_NAME))
			return SCTransformer.INSTANCE.toDto((SC)arg0);
		else if(className.equals(ST_NAME))
			return STTransformer.INSTANCE.toDto((ST)arg0);
		else if(className.equals(STNT_NAME))
			return STNTTransformer.INSTANCE.toDto((STNT)arg0);
		else if(className.equals(TEL_NAME))
			return TELTransformer.INSTANCE.toDto((TEL)arg0);
		else if(className.equals(TEL_EMAIL_NAME))
			return TELTransformer.INSTANCE.toDto((TELEmail)arg0);		
		else if(className.equals(TEL_PERSON_NAME))
			return TELTransformer.INSTANCE.toDto((TELPerson)arg0);
		else if(className.equals(TEL_PHONE_NAME))
			return TELTransformer.INSTANCE.toDto((TELPhone)arg0);		
		else if(className.equals(TEL_URL_NAME))
			return TELTransformer.INSTANCE.toDto((TELUrl)arg0);	
		else if(className.equals(TS_NAME))
			return TSTransformer.INSTANCE.toDto((TS)arg0);			
		else {
			log.debug(" * * * Class " + className + " did not match any ISO Datatype classes.");
			return null;
		}
	}

}
