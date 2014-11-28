package org.witchcraft.base.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.primefaces.model.DefaultUploadedFile;

@Embeddable
public class FileAttachment extends DefaultUploadedFile{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8339731595094765260L;

	@Column(length = 4194304)
	private byte[] data ;

	private String name;
	
	private String contentType;

	

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}
	
	boolean getImage() {
		return contentType.startsWith("image"); 
	}

	@Override
	public byte[] getContents() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return name;
	}

	
	
}
