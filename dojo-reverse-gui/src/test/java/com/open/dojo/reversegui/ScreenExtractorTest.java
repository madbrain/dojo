package com.open.dojo.reversegui;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.open.dojo.reversegui.model.FormField;
import com.open.dojo.reversegui.model.GroupBox;
import com.open.dojo.reversegui.model.ScreenModel;
import com.open.dojo.reversegui.model.TextField;
import com.open.dojo.reversegui.model.ToolBar;

public class ScreenExtractorTest {
	
	@Test
	public void testExtractSimpleScreen() {
		List<Widget> widgets = ExampleScreens.exampleSimple();
		
		ScreenModel model = new ScreenExtractor().extract(widgets);
		
		Assert.assertNotNull(model);
		Assert.assertEquals(1, model.getElements().size());
		
		Assert.assertEquals(FormField.class, model.getElements().get(0).getClass());
		FormField field0 = (FormField) model.getElements().get(0);
		Assert.assertEquals("Ancien", field0.getLabel());
		Assert.assertEquals(2, field0.getFields().size());
		Assert.assertEquals("dfAncTel", field0.getFields().get(0).getId());
		Assert.assertEquals(TextField.class, field0.getFields().get(0).getClass());
		Assert.assertEquals("dfAncTes", field0.getFields().get(1).getId());
		Assert.assertEquals(TextField.class, field0.getFields().get(1).getClass());
	}

	@Test
	public void testExtractScreen() {
		List<Widget> widgets = ExampleScreens.exampleFrmFichChgNtt();
		
		ScreenModel model = new ScreenExtractor().extract(widgets);
		
		Assert.assertNotNull(model);
		Assert.assertEquals(2, model.getElements().size());
		
		Assert.assertEquals(GroupBox.class, model.getElements().get(0).getClass());
		GroupBox groupBox = (GroupBox) model.getElements().get(0);
		Assert.assertEquals("Numéros d'accès", groupBox.getLabel());
		Assert.assertEquals(2, groupBox.getElements().size());

		Assert.assertEquals(FormField.class, groupBox.getElements().get(0).getClass());
		FormField field0 = (FormField) groupBox.getElements().get(0);
		Assert.assertEquals("Ancien", field0.getLabel());
		Assert.assertEquals(2, field0.getFields().size());
		Assert.assertEquals("dfAncTel", field0.getFields().get(0).getId());
		Assert.assertEquals(TextField.class, field0.getFields().get(0).getClass());
		Assert.assertEquals("dfAncTes", field0.getFields().get(1).getId());
		Assert.assertEquals(TextField.class, field0.getFields().get(1).getClass());
		
		Assert.assertEquals(FormField.class, groupBox.getElements().get(0).getClass());
		FormField field1 = (FormField) groupBox.getElements().get(1);
		Assert.assertEquals("Nouveau", field1.getLabel());
		Assert.assertEquals(2, field1.getFields().size());
		Assert.assertEquals("dfNouvTel", field1.getFields().get(0).getId());
		Assert.assertEquals(TextField.class, field1.getFields().get(0).getClass());
		Assert.assertEquals("dfNouvTes", field1.getFields().get(1).getId());
		Assert.assertEquals(TextField.class, field1.getFields().get(1).getClass());
		
		Assert.assertEquals(ToolBar.class, model.getElements().get(1).getClass());
		ToolBar toolBar = (ToolBar) model.getElements().get(1);
		Assert.assertEquals(2, toolBar.getButtons().size());
		Assert.assertEquals("pbValid", toolBar.getButtons().get(0).getId());
		Assert.assertEquals("pbSortie", toolBar.getButtons().get(0).getId());
	}
	
}
