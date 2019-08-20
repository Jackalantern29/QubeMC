package com.Jackalantern29.QCRewards.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;

public class Listeners {
	static List<Listener> list = new ArrayList<Listener>();
	
	public static List<Listener> getListeners() {
		list.clear();
		list.add(new JoinListener());
		list.add(new SignCreateListener());
		list.add(new SignInteractListener());
		
		return list;
	}
}
