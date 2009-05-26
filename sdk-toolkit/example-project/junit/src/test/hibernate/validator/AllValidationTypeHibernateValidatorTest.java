package test.hibernate.validator;

import gov.nih.nci.cacoresdk.domain.other.validationtype.AllValidationType;

import java.util.Calendar;

import org.hibernate.validator.ClassValidator;


/**
 * @author Daniel Dumitru
 */
public class AllValidationTypeHibernateValidatorTest extends SDKHibernateValidatorTestBase {
	
	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "All Validation Type Test Case";
	}
	
	private ClassValidator<AllValidationType> instanceValidator;

	
	protected void setUp() throws Exception{ 
		super.setUp();
		instanceValidator = new ClassValidator<AllValidationType>( AllValidationType.class );
	}

	//The following tests are only valid if the validator-Config.xml file  
	//located within the junit/settings/validator folder is used during code generation
	
//	public void testEmail() throws Exception {
//		isRight( instanceValidator, "email", "dumitrud@mail.nih.org" );
//		isRight( instanceValidator, "email", "" );
//		isRight( instanceValidator, "email", null );// Why???
//		isWrong( instanceValidator, "email", "dumitrud.mail.nih.org" );
//		isRight( instanceValidator, "email", "dumitrud@hibernate" );
//		isRight( instanceValidator, "email", "dumit-r_ud@hibernate" );
//		isWrong( instanceValidator, "email", "dumitr ud@mail.nih.org" );
//		isWrong( instanceValidator, "email", "dumitr(ud@mail.nih.org" );
//		isWrong( instanceValidator, "email", "dumitrud@" ); 
//		isRight( instanceValidator, "email", "dumitr+ud@mail.nih.org" );
//		isRight( instanceValidator, "email", "dumitr=ud@mail.nih.org" );
//		isWrong( instanceValidator, "email", "dumitr\nud@mail.nih.org" );
//		isWrong( instanceValidator, "email", "dumitr@ud@mail.nih.org" );
//		isRight( instanceValidator, "email", "dumitrud@[123.12.2.11]" );
//	}
//	
//	public void testLength() throws Exception {
//		isRight( instanceValidator, "length", "1" );
//		isRight( instanceValidator, "length", "123" );
//		isWrong( instanceValidator, "length", "" );
//		isRight( instanceValidator, "length", null );// Why???
//		isWrong( instanceValidator, "length", "1234" );
//	}
//	
//	public void testMaxString() throws Exception {
//		isRight( instanceValidator, "maxString", "999" );
//		isRight( instanceValidator, "maxString", "-99999" );
//		isWrong( instanceValidator, "maxString", "1000" );
//		isRight( instanceValidator, "maxString", null );// Why???
//		isWrong( instanceValidator, "maxString", "9999" );
//	}
//	
//	public void testMaxNumeric() throws Exception {
//		isRight( instanceValidator, "maxNumeric", "999" );
//		isRight( instanceValidator, "maxNumeric", "-99999" );
//		isWrong( instanceValidator, "maxNumeric", "1000" );
//		isRight( instanceValidator, "maxNumeric", null );// Why???
//		isWrong( instanceValidator, "maxNumeric", "9999" );
//	}
//	
//	public void testMinString() throws Exception {
//		isRight( instanceValidator, "minString", "1" );
//		isRight( instanceValidator, "minString", "99999" );
//		isWrong( instanceValidator, "minString", "0" );
//		isRight( instanceValidator, "minString", null );// Why???
//		isWrong( instanceValidator, "minString", "-99999" );
//	}
//	
//	public void testMinNumeric() throws Exception {
//		isRight( instanceValidator, "minNumeric", "1" );
//		isRight( instanceValidator, "minNumeric", "99999" );
//		isWrong( instanceValidator, "minNumeric", "0" );
//		isRight( instanceValidator, "minNumeric", null );// Why???
//		isWrong( instanceValidator, "minNumeric", "-99999" );
//	}
//	
//	public void testNotNull() throws Exception {
//		isRight( instanceValidator, "notNull", "" );
//		isRight( instanceValidator, "notNull", "abc" );
//		isWrong( instanceValidator, "notNull", null );
//		isRight( instanceValidator, "notNull", "-9999" );
//	}
//	
//	public void testNotEmpty() throws Exception {
//		isWrong( instanceValidator, "notEmpty", "" );
//		isRight( instanceValidator, "notEmpty", "abc" );
//		isWrong( instanceValidator, "notEmpty", null );
//		isRight( instanceValidator, "notEmpty", "-9999" );
//	}
//	
//	public void testPast() throws Exception {
//		Calendar cal = Calendar.getInstance();
//		isWrongDate( instanceValidator, "past", cal.getTime() );//Today
//		
//		cal.add(Calendar.DATE, 1); //Tomorrow
//		isWrongDate( instanceValidator, "past", cal.getTime() );
//		
//		cal.add(Calendar.DATE, -2); //Yesterday
//		isRightDate( instanceValidator, "past", cal.getTime());
//	}
//	
//	public void testFuture() throws Exception {
//		Calendar cal = Calendar.getInstance();
//		isWrongDate( instanceValidator, "future", cal.getTime() );//Today
//		
//		cal.add(Calendar.DATE, 1); //Tomorrow
//		isRightDate( instanceValidator, "future", cal.getTime() );
//		
//		cal.add(Calendar.DATE, -2); //Yesterday
//		isWrongDate( instanceValidator, "future", cal.getTime());
//	}
//	
//	public void testRangeString() throws Exception {
//		isRight( instanceValidator, "rangeString", "1" );
//		isRight( instanceValidator, "rangeString", "3" );
//		isWrong( instanceValidator, "rangeString", "0" );
//		isRight( instanceValidator, "rangeString", null );// Why???
//		isWrong( instanceValidator, "rangeString", "4" );
//	}
//	
//	public void testRangeNumeric() throws Exception {
//		isRight( instanceValidator, "rangeNumeric", "1" );
//		isRight( instanceValidator, "rangeNumeric", "3" );
//		isWrong( instanceValidator, "rangeNumeric", "0" );
//		isRight( instanceValidator, "rangeNumeric", null );// Why???
//		isWrong( instanceValidator, "rangeNumeric", "4" );
//	}
//	
//	public void testPattern() throws Exception {
//		isRight( instanceValidator, "pattern", "cat" );
//		isRight( instanceValidator, "pattern", "dog" );
//		isWrong( instanceValidator, "pattern", "ca" );
//		isRight( instanceValidator, "pattern", null );// Why???
//		isWrong( instanceValidator, "pattern", "dog house" );
//		isWrong( instanceValidator, "pattern", "catdog" );
//	}
	
	// The following test is only valid if connecting to the caDSR DEV environment  
	// using the following deploy.properties setting:
	//
	//	NAMESPACE_PREFIX=gme://Satish Project.caCORE/1/
	//
	//DEPT|BLDG|FL|RM|APT|STE
	public void testPattern() throws Exception {
		isRight( instanceValidator, "pattern", "DEPT");
		isRight( instanceValidator, "pattern", "BLDG" );
		isRight( instanceValidator, "pattern", "FL" );
		isRight( instanceValidator, "pattern", "RM");
		isRight( instanceValidator, "pattern", "APT" );
		isRight( instanceValidator, "pattern", "STE" );
		isWrong( instanceValidator, "pattern", "Department" );
		isWrong( instanceValidator, "pattern", "Suite" );
	}
	
}
