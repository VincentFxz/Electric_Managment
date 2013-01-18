package org.zju.electric_factory.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class SpringMVCDateConverter implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest arg1) {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));	
	}

}