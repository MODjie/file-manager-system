package com.jie.utils;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.Sigar;

public class SigarUtil {
	public static FileSystem[] getDiskInfo() throws Exception{
		Sigar sigar = new Sigar();
		FileSystem fslist[] = sigar.getFileSystemList();
		return fslist;
	}
}
