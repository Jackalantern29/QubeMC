package com.Jackalantern29.QCTab.Packet;

import org.bukkit.entity.Player;

public interface TabHeaderFooter {

    public void sendTabHeaderFooter(Player player, String headerJSON, String footerJSON);
}
