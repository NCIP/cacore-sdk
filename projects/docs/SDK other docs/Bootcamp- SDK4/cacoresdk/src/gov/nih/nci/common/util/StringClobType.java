package gov.nih.nci.common.util;
import java.io.Serializable;
import java.sql.*;
import org.hibernate.*;
import org.hibernate.usertype.*;

/**
 * <!-- LICENSE_TEXT_START -->
* Copyright 2001-2004 SAIC. Copyright 2001-2003 SAIC. This software was developed in conjunction with the National Cancer Institute,
* and so to the extent government employees are co-authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
* Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
* 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the disclaimer of Article 3, below. Redistributions
* in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other
* materials provided with the distribution.
* 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
* "This product includes software developed by the SAIC and the National Cancer Institute."
* If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself,
* wherever such third-party acknowledgments normally appear.
* 3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or promote products derived from this software.
* 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize
* the recipient to use any trademarks owned by either NCI or SAIC-Frederick.
* 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
* MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE,
* SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <!-- LICENSE_TEXT_END -->
*/
/**
 * @author cabio-team
 */
/**
 * StringClobType serializes an instances of a Clob to a String. 
 * 
 */
public class StringClobType implements UserType{
	
	/**
	 * Restructs an object from the cachable representation
	 * @param cached 
	 * @owner
	 */
	public Object assemble(Serializable cached, Object owner){
		return cached;
		}
	/**
	 * Returns a deep copy of the persistent state
	 */
	 public Object deepCopy(Object value)
	    {
	        if (value == null) return null;
			return new String((String) value);
	    }
	 /**
	  * Transforms an object into it's cacheable representation
	  * @param value
	  */
	public Serializable disassemble (Object value){
		return (Serializable)value;
		}   
	
	/**
	 * Compares two instances of the class mapped by this type of persistance entity
	 * @param x
	 * @param y
	 */
	public boolean equals(Object x, Object y)
    {
        return (x == y)
            || (x != null
                && y != null
                && (x.equals(y)));
    }
	
	/**
	 * Returns a hashcode for the instance
	 * @param x
	 */
	   public int hashCode(Object x){
    	int code = 0;
    	if(x!= null){
    		code =  x.hashCode();	    	
    	}
    	return code;
    	}
    
	/**
	 * Checks if object is mutable
	 */
    public boolean isMutable()
    {
        return false;
    }
	
    /**
     * Retrieves an instance of the mapped class from a JDBC resultset
     * @param rs - resultset
     * @param names
     * @param owner
     * @return Object
     */
	 public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
     throws HibernateException, SQLException
	 {
     Clob clob = rs.getClob(names[0]);
     if(clob == null)
         return null;
     else     
         return clob.getSubString(1, (int) clob.length());
	 }

	 
	 /**
	  * Writes an instance of the mapped class to a prepared statement
	  * @param st - PreparedStatement
	  * @param value
	  * @param index
	  */
	   public void nullSafeSet(PreparedStatement st, Object value, int index)
       throws HibernateException, SQLException
	   {	             
	        st.setClob(index, Hibernate.createClob((String) value));     
	   }
	   /**
	    * Replace the existing value with a new value
	    * @param original
	    * @param target
	    * @param owner
	    * @return
	    */
	   public Object replace(Object original, Object target, Object owner){
	   	target = original;
	   	return target;	   	
	   	}
	   
	   /**
	    * Class returned by nullSafeGet
	    */
	   public Class returnedClass()
	    {
	        return String.class;
	    }
	   
	   /**
	    * Returns the SQL tpe codes for the columns mapped by this type
	    */

	   public int[] sqlTypes()
	    {
	        return new int[] { Types.CLOB };
	    }

	   
	    

	   
	 
	   
	 

}
