package com.hcss.bean;

import java.sql.Blob;

import org.apache.struts.action.ActionForm;

public class UserImpFilesTO extends ActionForm{
	private int fileid;
	private int useridref;
	private String filename;
	private String filedescription;
	private String filedatapath;
	private Blob filedata;
	private String filetype;
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	public int getUseridref() {
		return useridref;
	}
	public void setUseridref(int useridref) {
		this.useridref = useridref;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiledescription() {
		return filedescription;
	}
	public void setFiledescription(String filedescription) {
		this.filedescription = filedescription;
	}

	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public Blob getFiledata() {
		return filedata;
	}
	public void setFiledata(Blob filedata) {
		this.filedata = filedata;
	}
	public String getFiledatapath() {
		return filedatapath;
	}
	public void setFiledatapath(String filedatapath) {
		this.filedatapath = filedatapath;
	}

}
