package com.h3c.portal.wbms.common.dto;

public class MemoryDTO {

	private float jvm_totalMemory;
	private float jvm_usedMemory;
	private float jvm_freeMemory;
	private float jvm_usedRatio;

	private float sys_totalMemory;
	private float sys_usedMemory;
	private float sys_freeMemory;
	private float sys_usedRatio;

	public float getJvm_totalMemory() {
		return jvm_totalMemory;
	}

	public void setJvm_totalMemory(float jvm_totalMemory) {
		this.jvm_totalMemory = jvm_totalMemory;
	}

	public float getJvm_usedMemory() {
		return jvm_usedMemory;
	}

	public void setJvm_usedMemory(float jvm_usedMemory) {
		this.jvm_usedMemory = jvm_usedMemory;
	}

	public float getJvm_freeMemory() {
		return jvm_freeMemory;
	}

	public void setJvm_freeMemory(float jvm_freeMemory) {
		this.jvm_freeMemory = jvm_freeMemory;
	}

	public float getJvm_usedRatio() {
		return jvm_usedRatio;
	}

	public void setJvm_usedRatio(float jvm_usedRatio) {
		this.jvm_usedRatio = jvm_usedRatio;
	}

	public float getSys_totalMemory() {
		return sys_totalMemory;
	}

	public void setSys_totalMemory(float sys_totalMemory) {
		this.sys_totalMemory = sys_totalMemory;
	}

	public float getSys_usedMemory() {
		return sys_usedMemory;
	}

	public void setSys_usedMemory(float sys_usedMemory) {
		this.sys_usedMemory = sys_usedMemory;
	}

	public float getSys_freeMemory() {
		return sys_freeMemory;
	}

	public void setSys_freeMemory(float sys_freeMemory) {
		this.sys_freeMemory = sys_freeMemory;
	}

	public float getSys_usedRatio() {
		return sys_usedRatio;
	}

	public void setSys_usedRatio(float sys_usedRatio) {
		this.sys_usedRatio = sys_usedRatio;
	}

}
