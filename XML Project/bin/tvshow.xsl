<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html"/>

	<xsl:template match="/">
	
		<html>
		<head><title>TV Shows</title></head>
		
		<body>
			
			Version: <xsl:value-of select="system-property('xsl:version')"/><br/>
			Vendor: <xsl:value-of select="system-property('xsl:vendor')"/><br/>
			Vendor URL: <xsl:value-of select="system-property('xsl:vendor-url')"/><br/>
				
			<xsl:for-each select="tvshow/show">
				<xsl:sort select="name" order="ascending" data-type="text"/>
				<a href="#{generate-id(name)}"><xsl:value-of select="name"/></a>
				<br/>
			</xsl:for-each>	
			
			<xsl:for-each select="tvshow/show">
				
				<xsl:sort select="name" order="ascending" data-type="text"/>
				
				<h3><a name="{generate-id(name)}">
				<xsl:value-of select="name"/></a></h3>
				
				<img>
					<xsl:attribute name="src">
						<xsl:value-of select="poster/@href" />
					</xsl:attribute>
				
				  </img><br/>
				<xsl:value-of select="current()" />
				<br/>
				
				<p>The show <xsl:value-of select="name"/> was released in 
				<xsl:value-of select="release"/> by <xsl:value-of select="network"/>.
				 It had and average viewership of <xsl:value-of select="viewers"/> 
				 <xsl:value-of select="viewers/@unit"/> people. 
				 It was cancelled in <xsl:value-of select="end-date"/>.  
				</p>
			
				<br/>
			
			</xsl:for-each>
			
			<table border="2">
				<tr>
					<th>Name</th>
					<th>Network</th>
					<th>Viewers</th>
				</tr>
				
				<xsl:for-each select="tvshow/show">
				<xsl:sort select="release" order="ascending" data-type="text"/>
				<xsl:if test="release &lt; 2006">
					<tr>
						<td><xsl:value-of select="name" /></td>				
						<td><xsl:value-of select="network" /></td>
						<td><xsl:value-of select="viewers" /></td>
					</tr>
				</xsl:if>		
				
				<xsl:choose>
					<xsl:when test="release > 2006">
					<tr bgcolor="yellow">
						<td><xsl:value-of select="name" /></td>				
						<td><xsl:value-of select="network" /></td>
						<td><xsl:value-of select="viewers" /></td>
					</tr>
					</xsl:when>
					
					<xsl:when test="release = 2006">
					<tr bgcolor="orange">
						<td><xsl:value-of select="name" /></td>				
						<td><xsl:value-of select="network" /></td>
						<td><xsl:value-of select="viewers" /></td>
					</tr>
					</xsl:when>
					
					<xsl:otherwise>
					<tr bgcolor="pink">
						<td><xsl:value-of select="name" /></td>				
						<td><xsl:value-of select="network" /></td>
						<td><xsl:value-of select="viewers" /></td>
					</tr>
					</xsl:otherwise>
					
				</xsl:choose>
				</xsl:for-each>
			
			</table>
			
			
					
		</body>
		
		</html>
	</xsl:template>
</xsl:stylesheet>