package com.gmail.realtadukoo.util.fileformat;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.gmail.realtadukoo.util.fileformat.FileFormat;
import com.gmail.realtadukoo.util.fileformat.FileFormatSchemaVerification;
import com.gmail.realtadukoo.util.fileformat.GHDR.GHDRFileFormat;

public class FileFormatSchemaVerificationTest{
	private static final String subfolder = "FileFormatSchemaVerificationTest";
	private String filepath;
	private FileFormat fileFormat;
	
	@Before
	public void setup() throws SecurityException, IOException{
		Logger logger = LoggerUtil.setupLogger(subfolder, "setup");
		filepath = "1959.ghdr";
		fileFormat = new GHDRFileFormat(logger);
	}
	
	@Test
	public void testVerifyFileFormat() throws SecurityException, IOException{
		Logger logger = LoggerUtil.setupLogger(subfolder, "testVerifyFileFormat");
		assertTrue(FileFormatSchemaVerification.verifyFileFormat(logger, fileFormat.getSchema("Version 1.0"), filepath));
	}
}