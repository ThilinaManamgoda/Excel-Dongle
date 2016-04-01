package com.maanadev.configurations;

public class Configurations {

	public Configurations() {
	}
	private String LOGFILE;
	private String PATH;
	private String PORT;
	private int FREQUENCY;
	private String GATEWAYNAME;
	public String getPATH() {
		return PATH;
	}

	public void setPATH(String pATH) {
		PATH = pATH;
	}

	public String getPORT() {
		return PORT;
	}

	public void setPORT(String pORT) {
		PORT = pORT;
	}

	public int getFREQUENCY() {
		return FREQUENCY;
	}

	public void setFREQUENCY(int fREQUENCY) {
		FREQUENCY = fREQUENCY;
	}

	public String getGATEWAYNAME() {
		return GATEWAYNAME;
	}

	public void setGATEWAYNAME(String gATEWAYNAME) {
		GATEWAYNAME = gATEWAYNAME;
	}

	public String getLOGFILE() {
		return LOGFILE;
	}

	public void setLOGFILE(String lOGFILE) {
		LOGFILE = lOGFILE;
	}

}
