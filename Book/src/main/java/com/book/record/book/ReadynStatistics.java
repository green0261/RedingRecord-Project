package com.book.record.book;

public class ReadynStatistics {
	private String readyn;
	private int count;
	public String getReadyn() {
		return readyn;
	}
	public void setReadyn(String readyn) {
		this.readyn = readyn;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "ReadynStatistics [readyn=" + readyn + ", count=" + count + "]";
	}
	
	
}
