package org.witchcraft.base.entity;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;


public class EntityTemplateListener {

	@SuppressWarnings("unchecked")
	@PrePersist
	public void encode(EntityTemplate entity) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 20);
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(bos ));
		encoder.writeObject(entity.getEntity());
		encoder.close();
		System.out.println(" ecoded xml : " + new String(bos.toByteArray()));
		entity.setEncodedXml(new String(bos.toByteArray()));

	}
	
	@SuppressWarnings("unchecked")
	@PostLoad
	public void decode(EntityTemplate entity){
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new ByteArrayInputStream(entity.getEncodedXml().getBytes()) ));
		entity.setEntity((BaseEntity) decoder.readObject());
		decoder.close();
	}

}
