<?xml version="1.0"?>
<!DOCTYPE stylesheet [
<!ENTITY sp "<xsl:text> </xsl:text>">
<!ENTITY ind "<xsl:text>    </xsl:text>">
<!ENTITY cr "<xsl:text>
</xsl:text>">
]>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xlink="http://www.w3.org/1999/xlink">

    <xsl:output method="text"/>

    <xsl:template match="/">
    	    
        <xsl:text>{</xsl:text>&cr;
        
        &ind;<xsl:text>'recordCounter':</xsl:text>
        <xsl:value-of select="/xlink:httpQuery/queryResponse/recordCounter"/>
        <xsl:text>,</xsl:text>&cr;
        
        &ind;<xsl:text>'start':</xsl:text>
        <xsl:value-of select="/xlink:httpQuery/queryResponse/start"/>
        <xsl:text>,</xsl:text>&cr;
        
        &ind;<xsl:text>'end':</xsl:text>
        <xsl:value-of select="/xlink:httpQuery/queryResponse/end"/>
        <xsl:text>,</xsl:text>&cr;
        
        &ind;<xsl:text>'pageCount':</xsl:text>
        <xsl:value-of select="/xlink:httpQuery/queryResponse/pages/@count"/>
        <xsl:text>,</xsl:text>&cr;
        
        &ind;<xsl:text>'results':[</xsl:text>&cr;
        <xsl:apply-templates select="/xlink:httpQuery/queryResponse/class"/>&cr;
        &ind;<xsl:text>]</xsl:text>&cr;
        
        <xsl:text>}</xsl:text>&cr;
    </xsl:template>


    <xsl:template match="/xlink:httpQuery/queryResponse/class">
    
        <xsl:if test="position()!=1">
            <xsl:text>,</xsl:text>&cr;
        </xsl:if>
            
        &ind;&ind;<xsl:text>{</xsl:text>&cr;
        
        &ind;&ind;&ind;<xsl:text>'name':'</xsl:text>
        <xsl:value-of select="@name"/>
        <xsl:text>',</xsl:text>&cr;
        
        &ind;&ind;&ind;<xsl:text>'recordNumber':</xsl:text>
        <xsl:value-of select="@recordNumber"/>
        <xsl:text>,</xsl:text>&cr;
        
        &ind;&ind;&ind;<xsl:text>'fields' : {</xsl:text>&cr;
        <xsl:apply-templates select="field"/>&cr;
        &ind;&ind;&ind;<xsl:text>}</xsl:text>&cr;
        
        &ind;&ind;<xsl:text>}</xsl:text>
        
    </xsl:template>

	<xsl:template match="field">
		<xsl:if test="not(@xlink:type = 'simple')">
			<xsl:if test="position()!=1">
				<xsl:text>,</xsl:text>&cr;
			</xsl:if>
            &ind;&ind;&ind;&ind;
			<xsl:text>'</xsl:text>
			<xsl:value-of select="@name" />
			<xsl:text>' : </xsl:text>
			
			<xsl:choose>
				<xsl:when test="not(*)">"<xsl:value-of select="."/>"</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates select="*" />
				</xsl:otherwise>
			</xsl:choose>	
		</xsl:if>
	</xsl:template>


    <!-- Object or Element Property-->
    <xsl:template match="*">
        "<xsl:value-of select="name()"/>" : <xsl:call-template name="Properties"/>
    </xsl:template>

    <!-- Array Element -->
    <xsl:template match="*" mode="ArrayElement">
        <xsl:call-template name="Properties"/>
    </xsl:template>

    <!-- Object Properties -->
    <xsl:template name="Properties">
        <xsl:variable name="childName" select="name(*[1])"/>
        <xsl:choose>
            <xsl:when test="not(*|@*)">"<xsl:value-of select="."/>"</xsl:when>
            <xsl:when test="count(*[name()=$childName]) > 1">{ "<xsl:value-of select="$childName"/>" :[<xsl:apply-templates select="*" mode="ArrayElement"/>] }</xsl:when>
            <xsl:otherwise>{
                <xsl:apply-templates select="@*"/>
                <xsl:apply-templates select="*"/>
    }</xsl:otherwise>
        </xsl:choose>
        <xsl:if test="following-sibling::*">,</xsl:if>
    </xsl:template>

    <!-- Attribute Property -->
    <xsl:template match="@*">
		<xsl:if test="position()!=1">
			<xsl:text>,</xsl:text>&cr;
		</xsl:if>
		"<xsl:value-of select="name()"/>" : "<xsl:value-of select="."/>"
    </xsl:template>
</xsl:stylesheet>