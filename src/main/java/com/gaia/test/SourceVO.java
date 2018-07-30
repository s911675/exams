package com.gaia.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SourceVO {

	@JsonProperty("ATCLAIM_NO")
	private String atclaimNo;
	
	@JsonProperty("ATCLAIM_CNTS")
	private String atclaimCnts;
	
	public String getAtclaimNo() {
		return atclaimNo;
	}
	public void setAtclaimNo(String atclaimNo) {
		this.atclaimNo = atclaimNo;
	}
	public String getAtclaimCnts() {
		return atclaimCnts;
	}
	public void setAtclaimCnts(String atclaimCnts) {
		this.atclaimCnts = atclaimCnts;
	}
}
