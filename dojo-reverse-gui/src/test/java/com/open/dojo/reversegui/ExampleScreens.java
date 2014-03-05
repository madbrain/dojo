package com.open.dojo.reversegui;

import java.util.ArrayList;
import java.util.List;

public class ExampleScreens {

	public static List<Widget> exampleFrmFichChgNtt() {
		List<Widget> widgets = new ArrayList<Widget>();
		widgets.add(new Widget(WidgetType.FRAME, "", new Rectangle(37, 27, 331, 84)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Numéros d'accès", new Rectangle(59, 17, 108, 15)));
//		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "N° principal", new Rectangle(131, 30, 79, 15)));
//		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "N° de secours", new Rectangle(239, 30, 93, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Ancien", new Rectangle(52, 58, 57, 12)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Nouveau", new Rectangle(52, 82, 57, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfAncTel", new Rectangle(124, 57, 108, 15)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfAncTes", new Rectangle(246, 57, 108, 15)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfNouvTel", new Rectangle(124, 81, 108, 15)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfNouvTes", new Rectangle(246, 81, 108, 15)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbValid", new Rectangle(290, 123, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbSortie", new Rectangle(333, 123, 36, 30)));
		return widgets;
	}
	
	public static List<Widget> exampleSimple() {
		List<Widget> widgets = new ArrayList<Widget>();
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Ancien", new Rectangle(52, 58, 57, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfAncTel", new Rectangle(124, 57, 108, 15)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfAncTes", new Rectangle(246, 57, 108, 15)));
		return widgets;
	}

	public static List<Widget> exampleFrmFichPla() {
		List<Widget> widgets = new ArrayList<Widget>();
		widgets.add(new Widget(WidgetType.FRAME, "", new Rectangle(11, 9, 374, 54)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Serveur tel.", new Rectangle(33, 17, 72, 14)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfCmsCod", new Rectangle(112, 14, 21, 15)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfCmsLib", new Rectangle(148, 14, 230, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Etablissement", new Rectangle(18, 40, 86, 14)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfEtbCod", new Rectangle(112, 39, 28, 15)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfEtbLib", new Rectangle(148, 39, 230, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "N° de porteur", new Rectangle(26, 75, 79, 14)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPlaPor", new Rectangle(112, 75, 151, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Expiration, le", new Rectangle(26, 99, 79, 14)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPlaExp", new Rectangle(112, 99, 50, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Réseau", new Rectangle(54, 122, 50, 14)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPlaRes", new Rectangle(112, 122, 28, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Perte du code", new Rectangle(18, 147, 86, 14)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPlaSec", new Rectangle(112, 147, 21, 15)));
		widgets.add(new Widget(WidgetType.FRAME, "", new Rectangle(11, 183, 136, 41)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Recherche", new Rectangle(54, 174, 64, 12)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbRech", new Rectangle(18, 189, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbPremier", new Rectangle(62, 189, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbSuivant", new Rectangle(105, 189, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbCréer", new Rectangle(155, 189, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbSupp", new Rectangle(198, 189, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbImpr", new Rectangle(263, 189, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbValid", new Rectangle(306, 189, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbSortie", new Rectangle(350, 189, 36, 30)));
		return widgets;
	}
	
	public static List<Widget> exampleFrmFichPmc() {
		List<Widget> widgets = new ArrayList<Widget>();
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "CMS", new Rectangle(13, 2, 28, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfCmsLib", new Rectangle(49, 17, 223, 15)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfCmsCod", new Rectangle(13, 17, 28, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "ETB", new Rectangle(287, 2, 28, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfEtbCod", new Rectangle(287, 17, 28, 15)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfEtbLib", new Rectangle(323, 17, 223, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Référence", new Rectangle(118, 38, 59, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPmcRef", new Rectangle(243, 35, 28, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Libellé", new Rectangle(288, 38, 59, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPmcLib", new Rectangle(387, 35, 158, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Date de début de période", new Rectangle(13, 56, 165, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPmcDdt", new Rectangle(193, 53, 79, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Date de fin de période", new Rectangle(287, 56, 165, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPmcFin", new Rectangle(459, 53, 86, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Code langue", new Rectangle(49, 96, 86, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPmcCla", new Rectangle(150, 95, 36, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Abréviation langue", new Rectangle(13, 126, 122, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPmcAla", new Rectangle(150, 125, 36, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Numéro de message", new Rectangle(6, 156, 129, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPmcNme", new Rectangle(150, 155, 36, 15)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Message", new Rectangle(78, 186, 59, 12)));
		widgets.add(new Widget(WidgetType.DATA_FIELD, "dfPmcMes", new Rectangle(150, 185, 331, 15)));
		widgets.add(new Widget(WidgetType.FRAME, "", new Rectangle(20, 245, 129, 41)));
		widgets.add(new Widget(WidgetType.BACKGROUND_TEXT, "Recherche", new Rectangle(56, 235, 72, 12)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbRech", new Rectangle(26, 251, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbPremier", new Rectangle(67, 251, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbSuivant", new Rectangle(108, 251, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbCréer", new Rectangle(188, 251, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbModif", new Rectangle(238, 251, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbSupp", new Rectangle(287, 251, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbImpr", new Rectangle(375, 251, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbValid", new Rectangle(459, 251, 36, 30)));
		widgets.add(new Widget(WidgetType.PUSHBUTTON, "pbSortie", new Rectangle(510, 251, 36, 30)));
		widgets.add(new Widget(WidgetType.FRAME, "", new Rectangle(6, 71, 540, 1)));
		return widgets;
	}
}
