package com.open.dojo.reversegui.gen;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import com.open.dojo.reversegui.model.Button;
import com.open.dojo.reversegui.model.FormField;
import com.open.dojo.reversegui.model.GroupBox;
import com.open.dojo.reversegui.model.ScreenModel;
import com.open.dojo.reversegui.model.TextField;
import com.open.dojo.reversegui.model.ToolBar;

public class ScreenGeneratorTest {

	@Test
	public void testGenerateScreen() throws IOException {
		
		ScreenModel screenModel = new ScreenModel();
		GroupBox groupBox = new GroupBox("Numéros d'accès");
		screenModel.getElements().add(groupBox);
		
		FormField formField0 = new FormField("Ancien");
		formField0.getFields().add(new TextField("dfAncTel"));
		formField0.getFields().add(new TextField("dfAncTes"));
		groupBox.getElements().add(formField0);
		
		FormField formField1 = new FormField("Nouveau");
		formField1.getFields().add(new TextField("dfNouvTel"));
		formField1.getFields().add(new TextField("dfNouvTes"));
		groupBox.getElements().add(formField1);
		
		ToolBar toolbar = new ToolBar();
		screenModel.getElements().add(toolbar);
		toolbar.getButtons().add(new Button("pbValid"));
		toolbar.getButtons().add(new Button("pbSortie"));
		
		VelocityContext context = new VelocityContext();
		context.put("modelgen", new ScreenModelGen(screenModel));

		VelocityEngine ve = new VelocityEngine();
		
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		
		Template template = ve.getTemplate("/templates/html.vm");

		Writer out = new OutputStreamWriter(new FileOutputStream("out.html"), "utf-8");

		template.merge(context, out);
		
		out.close();

	}
}
