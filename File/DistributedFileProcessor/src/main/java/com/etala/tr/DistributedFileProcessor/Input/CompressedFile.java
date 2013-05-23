/**
 * 
 */
package com.etala.tr.DistributedFileProcessor.Input;

/**
 * @author ETALA
 *
 */
public class CompressedFile {
	private String filename;
	private long uncompressedSize;

	public CompressedFile(String filename, long uncompressedSize) {
		super();
		this.filename = filename;
		this.uncompressedSize = uncompressedSize;
	}

	public String getName() {
		return filename;
	}

	public void setName(String name) {
		this.filename = name;
	}

	public long getUncompressedSize() {
		return uncompressedSize;
	}

	public void setUncompressedSize(long uncompressedSize) {
		this.uncompressedSize = uncompressedSize;
	}

	@Override
	public String toString() {
		return filename;
	}
}
