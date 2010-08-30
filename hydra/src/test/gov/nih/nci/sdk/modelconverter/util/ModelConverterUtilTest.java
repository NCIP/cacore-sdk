package gov.nih.nci.sdk.modelconverter.util;

import static org.junit.Assert.*;
import gov.nih.nci.sdk.modelconverter.util.ModelConverterUtil;

import java.util.Set;

import org.junit.Test;

/**
 * ModelConverterUtilTest
 * 
 * @author John Chen
 *
 */
public class ModelConverterUtilTest {
	@Test
	public void loadTags() {
		Set<String> tagNames = ModelConverterUtil.getAllSDKTagNames();
		assertEquals("All tags loaded", 54, tagNames.size());
	}
	
	@Test
	public void isSDKTag() {
		assertFalse(ModelConverterUtil.isSDKTag("this.is.no.sdk.tag"));
		
		assertTrue(ModelConverterUtil.isSDKTag("package.prop.sec.encrypt"));
		assertTrue(ModelConverterUtil.isSDKTag("package.oper.sec.role"));
		assertTrue(ModelConverterUtil.isSDKTag("oper.sec.role"));
		assertTrue(ModelConverterUtil.isSDKTag("oper.mea.desc"));
		assertTrue(ModelConverterUtil.isSDKTag("rel.mea.desc"));
		assertTrue(ModelConverterUtil.isSDKTag("class.per.table.name"));
		assertTrue(ModelConverterUtil.isSDKTag("prop.per.primary.key"));
		assertTrue(ModelConverterUtil.isSDKTag("prop.val.required"));
		assertTrue(ModelConverterUtil.isSDKTag("prop.val.max.length"));
		assertTrue(ModelConverterUtil.isSDKTag("prop.val.min.length"));
	}
}
