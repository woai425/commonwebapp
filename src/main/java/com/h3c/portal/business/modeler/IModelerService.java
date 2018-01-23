package com.h3c.portal.business.modeler;

import com.h3c.framework.H3cException;
import com.h3c.framework.common.dto.AjaxJson;

public interface IModelerService {
	public AjaxJson getModelerList(Integer start, Integer limit) throws H3cException;
}
