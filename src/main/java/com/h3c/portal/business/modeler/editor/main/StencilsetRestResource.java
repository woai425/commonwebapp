package com.h3c.portal.business.modeler.editor.main;

import java.io.InputStream;

import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*@RestController*/
@Controller
public class StencilsetRestResource
{
  @RequestMapping(value={"/editor/stencilset"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json;charset=utf-8"})
  @ResponseBody
  public String getStencilset()
  {
    InputStream stencilsetStream = getClass().getClassLoader().getResourceAsStream("stencilset.json");
    try
    {
      return IOUtils.toString(stencilsetStream, "utf-8");
    }
    catch (Exception e)
    {
      throw new ActivitiException("Error while loading stencil set", e);
    }
  }
}
