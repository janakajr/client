package com.all.client.view.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.all.appControl.control.ViewEngine;
import com.all.core.actions.Actions;

@Component
public class BackTrackAction implements ExecutingAction {
	private ViewEngine viewEngine;

	public BackTrackAction() {
	}

	@Autowired
	void setViewEngine(ViewEngine viewEngine) {
		this.viewEngine = viewEngine;
	}

	public void execute() {
		viewEngine.send(Actions.Player.PREVIOUS);
	}

}