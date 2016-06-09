package com.entity;

import com.bing.excel.annotation.CellConfig;

public class Stu {
	/**
	 *  CREATE TABLE stu(
  		id bigint  identity primary key,
  		username  varchar(20) DEFAULT NULL,
  		stuclass  varchar(20) DEFAULT NULL,
  		java double default null,
  		math double default null,
  		linux double default null,
	);
	 */
	@CellConfig(index = 0)
	private String username;
	@CellConfig(index = 1)
	private String stuclass;
	@CellConfig(index = 2)
	private long id;
	@CellConfig(index = 3)
	private double java;
	@CellConfig(index = 4)
	private double math;
	@CellConfig(index = 5)
	private double linux;

	public Stu() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStuclass() {
		return stuclass;
	}

	public void setStuclass(String stuclass) {
		this.stuclass = stuclass;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getJava() {
		return java;
	}

	public void setJava(double java) {
		this.java = java;
	}

	public double getMath() {
		return math;
	}

	public void setMath(double math) {
		this.math = math;
	}

	public double getLinux() {
		return linux;
	}

	public void setLinux(double linux) {
		this.linux = linux;
	}

	@Override
	public String toString() {
		return "Stu [username=" + username + ", stuclass=" + stuclass + ", id="
				+ id + ", java=" + java + ", math=" + math + ", linux=" + linux
				+ "]";
	}


	
}
