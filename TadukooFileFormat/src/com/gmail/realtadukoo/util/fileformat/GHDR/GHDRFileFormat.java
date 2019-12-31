package com.gmail.realtadukoo.util.fileformat.GHDR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.gmail.realtadukoo.util.fileformat.FileFormat;
import com.gmail.realtadukoo.util.fileformat.FileFormatSchema;
import com.gmail.realtadukoo.util.fileformat.FormatNode;
import com.gmail.realtadukoo.util.fileformat.Node;
import com.gmail.realtadukoo.util.fileformat.TadFormatNodeHeader;

public class GHDRFileFormat extends FileFormat{
	
	public GHDRFileFormat(Logger logger){
		super(logger, "GHDR");
	}
	
	@Override
	protected Map<String, FileFormatSchema> createSchemas(Logger logger){
		Map<String, FileFormatSchema> schemas = new HashMap<String, FileFormatSchema>();
		// Version 1.0 File Format Schema
		String version1 = "Version 1.0";
		ArrayList<FormatNode> v1Nodes = new ArrayList<FormatNode>();
		v1Nodes.add(FormatNode.builder()
								.logger(logger)
								.name("head")
								.titleFormat("<fileTitle>")
								.dataFormat("")
								.level(0)
								.childName("title")
								.prevSiblingName(TadFormatNodeHeader.HEAD_NAME)
								.build());
		v1Nodes.add(FormatNode.builder()
								.logger(logger)
								.name("title")
								.titleFormat("<text>")
								.dataFormat("<imagefile>[$<#>,<#>,<#>,<#>]")
								.level(1)
								.parentName("head")
								.nullParentName()
								.prevSiblingName("title")
								.nullPrevSiblingName()
								.nextSiblingName("title")
								.nullNextSiblingName()
								.build());
		FileFormatSchema v1 = new FileFormatSchema(version1, 1, ".ghdr", v1Nodes);
		schemas.put(version1, v1);
		return schemas;
	}
	
	@Override
	public Node updateFile(Node oldFile, String oldVersion, String newVersion){
		return oldFile;
	}
}
