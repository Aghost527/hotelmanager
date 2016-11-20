/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.common.utils.jackson;

import com.dhc.common.utils.io.ClobUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Clob;
/**
 * 自定义Jackson Clob类型转换.
 *  
 *   
 *
 */
public class ClobSerializer extends JsonSerializer<Clob> {

		@Override
		public void serialize(Clob arg0, JsonGenerator arg1,
				SerializerProvider arg2) throws IOException,
				JsonProcessingException {
			String json = ClobUtil.getString(arg0);
			arg1.writeString(json);
		}
}