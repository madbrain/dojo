package com.open.dojo.reversegui.gen;

import java.util.List;

import com.open.dojo.reversegui.model.GroupBox;
import com.open.dojo.reversegui.model.ScreenElement;
import com.open.dojo.reversegui.model.ScreenModel;
import com.open.dojo.reversegui.model.ToolBar;

public class ScreenModelGen {

	private ScreenModel screenModel;

	public ScreenModelGen(ScreenModel screenModel) {
		this.screenModel = screenModel;
	}
	
	public List<ScreenElement> getElements() {
		return screenModel.getElements();
	}
	
	public boolean isGroupBox(ScreenElement element) {
		return element instanceof GroupBox;
	}
	
	public boolean isToolbar(ScreenElement element) {
		return element instanceof ToolBar;
	}

}
