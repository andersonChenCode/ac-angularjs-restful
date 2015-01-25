package com.util.rtftemplate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResultTableModel {
	private Collection all;
	public void addRtfbo(rtfbo bo) {this.all.add(bo);}
	public List getList() {return (List) all;}
	public ResultTableModel(){
		all = new ArrayList();
	}
}
