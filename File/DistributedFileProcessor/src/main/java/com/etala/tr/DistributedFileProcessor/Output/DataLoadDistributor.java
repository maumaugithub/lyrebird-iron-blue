/**
 * 
 */
package com.etala.tr.DistributedFileProcessor.Output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.etala.tr.DistributedFileProcessor.Input.CompressedFile;
import com.etala.tr.DistributedFileProcessor.Input.FileSizeComparator;

/**
 * @author ETALA
 *
 */
public class DataLoadDistributor {
	public static Map<String, List<CompressedFile>> fileLoadDistributor(List<CompressedFile> fileList, int threadPoolSize) {
		
		Map<String, List<CompressedFile>> parserLoad = new LinkedHashMap<String, List<CompressedFile>>(threadPoolSize);
		
		if (fileList != null && fileList.size() > 0 && threadPoolSize > 0) {

			Collections.sort(fileList, new FileSizeComparator());

			long total = getUncompressedTotalSize(fileList);
			long threadLoad = (total / threadPoolSize);

			threadLoad = threadLoad - (threadLoad / 10);

			int threadNumber = 1;
			long loadCount = 0;
			List<CompressedFile> threadFiles = new ArrayList<CompressedFile>();

			for (CompressedFile file : fileList) {
				threadFiles.add(file);
				loadCount += file.getUncompressedSize();

				if (loadCount >= threadLoad) {
					parserLoad.put("Thread " + (threadNumber++), threadFiles);

					threadFiles = new ArrayList<CompressedFile>();
					loadCount = 0;
				}
			}
			if (loadCount > 0) {
				parserLoad.put("Thread " + (threadNumber++), threadFiles);
			}
		}

		printOutput(parserLoad, fileList);

		return parserLoad;
	}

	protected static void printOutput(Map<String, List<CompressedFile>> outputList, List<CompressedFile> inputList) {

		for (String threadName : outputList.keySet()) {
			long totalSize = 0;
			for (CompressedFile file : outputList.get(threadName)) {
				totalSize += file.getUncompressedSize();
			}
			System.out.println(threadName + outputList.get(threadName) + " Total UnCompressed Size :" + totalSize);
		}

		long maxSize = inputList.get(0).getUncompressedSize();
		long minSize = inputList.get(inputList.size() - 1).getUncompressedSize();
		System.out.println("maxSize:" + maxSize);
		System.out.println("minSzie:" + minSize);
		System.out.println("Data Skew : " + ((maxSize - minSize) * 1.0D / maxSize) * 100);

	}

	protected static long getUncompressedTotalSize(List<CompressedFile> inputList) {
		long totalUncompressedSize = 0;
		for (CompressedFile file : inputList) {
			totalUncompressedSize += file.getUncompressedSize();
		}
		return totalUncompressedSize;
	}
}
