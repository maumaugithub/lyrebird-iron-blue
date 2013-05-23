/**
 * 
 */
package com.etala.tr.DistributedFileProcessor.Output;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.etala.tr.DistributedFileProcessor.Input.CompressedFile;

/**
 * @author ETALA
 *
 */
public class DataLoadDistributorTest {

	@Test
	public void testdistributeFileLoad() {
		Map<String, List<CompressedFile>> output = DataLoadDistributor.fileLoadDistributor(getFiles(), 3);
		assertEquals(3, output.size());
		assertEquals(output.get("Thread 1").size(), 1);
		assertEquals(output.get("Thread 2").size(), 2);
		assertEquals(output.get("Thread 3").size(), 2);

	}

	@Test
	public void testGetUncompressedTotalSize() {
		long totalSize = DataLoadDistributor.getUncompressedTotalSize(getFiles());
		Assert.assertEquals(142, totalSize);
	}

	private List<CompressedFile> getFiles() {
		List<CompressedFile> inputList = new ArrayList<CompressedFile>();
		inputList.add(new CompressedFile("File 1", 1 * 9));
		inputList.add(new CompressedFile("File 2", 2 * 9));
		inputList.add(new CompressedFile("File 5", 5 * 9));
		inputList.add(new CompressedFile("File 3", 3 * 10));
		inputList.add(new CompressedFile("File 4", 4 * 10));
		return inputList;
	}

}
