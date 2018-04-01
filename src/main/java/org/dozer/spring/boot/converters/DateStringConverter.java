package org.dozer.spring.boot.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.converters.DateConverter;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

public final class DateStringConverter implements CustomConverter {
	
	private SimpleDateFormat sdf = new SimpleDateFormat();
	private DateConverter converter = new DateConverter();
	
	/**
	 * destinationFieldValue：目标字段值
	 * sourceFieldValue：源字段值
	 * destinationClass:目标字段类型
	 * sourceClass：源字段类型
	 */
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		//String to Date
		if (sourceClass.equals(String.class)&&sourceFieldValue instanceof String) {
			Object dest = null;
			try {
				if (destinationFieldValue == null) {
					dest = "";
				} else {
					dest = sdf.parse((String) sourceFieldValue);
				}
			} catch (Exception e) {
				throw new MappingException(e);
			}
			return dest;
		}
		//Date to String
		if (sourceClass.equals(Date.class)&&sourceFieldValue instanceof Date) {
			return converter.convert(String.class, sourceFieldValue);
		}
		throw new MappingException( "Converter DateStringConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
}