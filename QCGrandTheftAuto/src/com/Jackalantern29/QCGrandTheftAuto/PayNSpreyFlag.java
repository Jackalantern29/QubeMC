package com.Jackalantern29.QCGrandTheftAuto;

import org.bukkit.command.CommandSender;

import com.mewin.WGCustomFlags.flags.CustomFlag;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;
import com.sk89q.worldguard.protection.flags.RegionGroup;

public class PayNSpreyFlag extends CustomFlag<PayNSpreyDesc> {
  public PayNSpreyFlag(String name, RegionGroup rg) {
    super(name, rg);
  }

  public PayNSpreyFlag(String name) {
    super(name);
  }

  public PayNSpreyDesc loadFromDb(String str) {

    return new PayNSpreyDesc(Boolean.parseBoolean(str));
  }

  public String saveToDb(PayNSpreyDesc o) {
    return o.getValue() + "";
  }

  public PayNSpreyDesc parseInput(WorldGuardPlugin plugin, CommandSender sender, String input) throws InvalidFlagFormat {
    return new PayNSpreyDesc(Boolean.valueOf(input));
  }

  public PayNSpreyDesc unmarshal(Object o) {
    return new PayNSpreyDesc(Boolean.parseBoolean(o + ""));
  }

  public Object marshal(PayNSpreyDesc o) {
    return o.getValue();
  }
}