package com.jie.domain;

public class Disk {
	/**
	 * 磁盘名称
	 */
	private String diskName;
	/**
	 * 磁盘路径
	 */
	private String diskPath;
	/**
	 * 文件系统总大小
	 */
	private double total;
	/**
	 * 剩余大小
	 */
	private double free;
	/**
	 * 可用大小
	 */
	private double avail;
	/**
	 * 已经使用量
	 */
	private double used;
	/**
	 * 资源使用率(磁盘已用率)
	 */
	private double usePercent;

	public String getDiskName() {
		return diskName;
	}

	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}

	public String getDiskPath() {
		return diskPath;
	}

	public void setDiskPath(String diskPath) {
		this.diskPath = diskPath;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getFree() {
		return free;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public double getAvail() {
		return avail;
	}

	public void setAvail(double avail) {
		this.avail = avail;
	}

	public double getUsed() {
		return used;
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public double getUsePercent() {
		return usePercent;
	}

	public void setUsePercent(double usePercent) {
		this.usePercent = usePercent;
	}
}
