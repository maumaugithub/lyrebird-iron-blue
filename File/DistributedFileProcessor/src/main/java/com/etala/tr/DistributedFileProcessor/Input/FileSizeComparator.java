/**
 * 
 */
package com.etala.tr.DistributedFileProcessor.Input;

import java.util.Comparator;

/**
 * @author ETALA
 *
 */
public class FileSizeComparator implements Comparator<CompressedFile>{

	public int compare(CompressedFile o1, CompressedFile o2) {
		return o1.getUncompressedSize() < o2.getUncompressedSize() ? 1 : 0;
	}

}
