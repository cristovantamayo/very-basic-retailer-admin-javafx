package com.cristovantamayo.veryBasicRetailerAdmin.gui.util;

import java.util.TimerTask;

import javafx.application.Platform;

public class Executor extends TimerTask {
	
	Runnable runnable;
	
	public Executor(Runnable run) {
		runnable = run;
	}

	@Override
	public void run() {
		Platform.runLater(runnable);
	}

}
