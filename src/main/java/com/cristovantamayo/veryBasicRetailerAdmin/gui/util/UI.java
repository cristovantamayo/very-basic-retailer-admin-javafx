package com.cristovantamayo.veryBasicRetailerAdmin.gui.util;

import javafx.scene.Cursor;
import javafx.scene.control.Control;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class UI {
	public static Control dropDownItem(Control element){
		element.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		element.setOnMouseEntered(e -> {
			element.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
			element.setCursor(Cursor.HAND);
		});
		element.setOnMouseExited(e -> {
			element.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		});
		return element;
	}
}
