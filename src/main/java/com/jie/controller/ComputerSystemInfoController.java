package com.jie.controller;

import com.jie.utils.SigarUtil;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/computerSystemInfo")
@Controller
public class ComputerSystemInfoController {
	@RequestMapping("/getDiskInfos")
	public void getDiskInfos(){
		try {
			FileSystem fslist[] = SigarUtil.getDiskInfo();
			for (int i = 0; i < fslist.length; i++) {
//				System.out.println("分区的盘符名称" + i);
				FileSystem fs = fslist[i];
//				// 分区的盘符名称
				System.out.println("盘符名称:    " + fs.getDevName());
//				// 分区的盘符路径
				System.out.println("盘符路径:    " + fs.getDirName());
//				System.out.println("盘符标志:    " + fs.getFlags());//
//				// 文件系统类型，比如 FAT32、NTFS
//				System.out.println("盘符类型:    " + fs.getSysTypeName());
//				// 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
//				System.out.println("盘符类型名:    " + fs.getTypeName());
//				// 文件系统类型
//				System.out.println("盘符文件系统类型:    " + fs.getType());
//				FileSystemUsage usage = null;
//				usage = sigar.getFileSystemUsage(fs.getDirName());
//				switch (fs.getType()) {
//					case 0: // TYPE_UNKNOWN ：未知
//						break;
//					case 1: // TYPE_NONE
//						break;
//					case 2: // TYPE_LOCAL_DISK : 本地硬盘
//						// 文件系统总大小
//						System.out.println(fs.getDevName() + "总大小:    " + usage.getTotal() + "KB");
//						// 文件系统剩余大小
//						System.out.println(fs.getDevName() + "剩余大小:    " + usage.getFree() + "KB");
//						// 文件系统可用大小
//						System.out.println(fs.getDevName() + "可用大小:    " + usage.getAvail() + "KB");
//						// 文件系统已经使用量
//						System.out.println(fs.getDevName() + "已经使用量:    " + usage.getUsed() + "KB");
//						double usePercent = usage.getUsePercent() * 100D;
//						// 文件系统资源的利用率
//						System.out.println(fs.getDevName() + "资源的利用率:    " + usePercent + "%");
//						break;
//					case 3:// TYPE_NETWORK ：网络
//						break;
//					case 4:// TYPE_RAM_DISK ：闪存
//						break;
//					case 5:// TYPE_CDROM ：光驱
//						break;
//					case 6:// TYPE_SWAP ：页面交换
//						break;
//				}
//				System.out.println(fs.getDevName() + "读出：    " + usage.getDiskReads());
//				System.out.println(fs.getDevName() + "写入：    " + usage.getDiskWrites());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/test",method = RequestMethod.POST)
	public String test(){
		return "连接成功";
	}
}
