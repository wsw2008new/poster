package org.pti.poster.filter;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;

public class PostFilter extends SimpleBeanPropertyFilter{
	@Override
	public void serializeAsField(Object post, JsonGenerator jgen, SerializerProvider prov, BeanPropertyWriter writer) throws Exception {
			if (!writer.getName().equals("type")) {
				writer.serializeAsField(post, jgen, prov);
			}
	}
}
