package com.Jackalantern29.QCGrandTheftAuto.Listeners;

import java.util.Random;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.Jackalantern29.QCGrandTheftAuto.QubeMC;

public class PlayerKillListener implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerAttack(EntityDamageByEntityEvent event) {
		if(event.isCancelled())
			return;
		if((event.getEntity() instanceof Skeleton) || (event.getEntity() instanceof Player) || (event.getEntity() instanceof PigZombie) || (event.getEntity() instanceof IronGolem) || (event.getEntity() instanceof Villager)) {
			if(!(event.getDamager() instanceof Player))
				return;
			Player killer = (Player) event.getDamager();
			if(QubeMC.wantedLevel.get(killer.getUniqueId()) == 0) {
				QubeMC.wantedLevel.replace(killer.getUniqueId(), 1);
				for(Entity entity : killer.getNearbyEntities(10, 10, 10)) {
					if((entity instanceof PigZombie) || (entity instanceof IronGolem)) {
						Creature pigzombie = (Creature)entity;
						pigzombie.setTarget(killer);
					} else if(entity instanceof Skeleton) {
						if(entity.getCustomName().equals("§9Army Soldier")) {
							Creature pigzombie = (Creature)entity;
							pigzombie.setTarget(killer);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerKill(EntityDeathEvent event) {
		if((event.getEntity() instanceof PigZombie) || (event.getEntity() instanceof IronGolem)) {
			if(event.getEntity().getKiller() instanceof Player) {
				if(QubeMC.amount.get(event.getEntity().getKiller().getUniqueId()) != 0) {
					QubeMC.amount.replace(event.getEntity().getKiller().getUniqueId(), QubeMC.amount.get(event.getEntity().getKiller().getUniqueId()) - 1);
				}
			}
		}
		if((event.getEntity() instanceof Skeleton) || (event.getEntity() instanceof Player) || (event.getEntity() instanceof PigZombie) || (event.getEntity() instanceof IronGolem) || (event.getEntity() instanceof Villager)) {
			if(!(event.getEntity().getKiller() instanceof Player))
				return;
			Player killer = event.getEntity().getKiller();
			if(!QubeMC.wantedLevel.containsKey(killer.getUniqueId()))
				return;
			if(QubeMC.wantedLevel.get(killer.getUniqueId()) == 0) {
				QubeMC.wantedLevel.replace(killer.getUniqueId(), 1);
				for(Entity entity : killer.getNearbyEntities(10, 10, 10)) {
					if((entity instanceof PigZombie) || (entity instanceof IronGolem)) {
						Creature pigzombie = (Creature)entity;
						pigzombie.setTarget(killer);
					} else if(entity instanceof Skeleton) {
						if(entity.getCustomName().equals("§9Army Soldier")) {
							Creature pigzombie = (Creature)entity;
							pigzombie.setTarget(killer);
						}
					}
				}
			} else if((QubeMC.wantedLevel.get(killer.getUniqueId()) == 1) && ((event.getEntity() instanceof PigZombie) || (event.getEntity() instanceof IronGolem) || (event.getEntity() instanceof Skeleton))) {
				QubeMC.wantedLevel.replace(killer.getUniqueId(), 2);
				for(Entity entity : killer.getNearbyEntities(10, 10, 10)) {
					if(((entity instanceof PigZombie) || entity instanceof IronGolem)) {
						Creature pigzombie = (Creature)entity;
						pigzombie.setTarget(killer);
					} else if(entity instanceof Skeleton) {
						if(entity.getCustomName().equals("§9Army Soldier")) {
							Creature pigzombie = (Creature)entity;
							pigzombie.setTarget(killer);
						}
					}
				}
			} else if((QubeMC.wantedLevel.get(killer.getUniqueId()) == 2) && ((event.getEntity() instanceof Skeleton) || (event.getEntity() instanceof PigZombie) || (event.getEntity() instanceof IronGolem))) {
				Random r = new Random();
				int random = r.nextInt(4);
				if((random == 0) || (random == 1)) {
					QubeMC.wantedLevel.replace(killer.getUniqueId(), 3);
					for(Entity entity : killer.getNearbyEntities(10, 10, 10)) {
						if(((entity instanceof PigZombie) || entity instanceof IronGolem)) {
							Creature pigzombie = (Creature)entity;
							pigzombie.setTarget(killer);
						} else if(entity instanceof Skeleton) {
							if(entity.getCustomName().equals("§9Army Soldier")) {
								Creature pigzombie = (Creature)entity;
								pigzombie.setTarget(killer);
							}
						}
					}
				}
			} else if((QubeMC.wantedLevel.get(killer.getUniqueId()) == 3) && ((event.getEntity() instanceof Skeleton) || (event.getEntity() instanceof PigZombie) || (event.getEntity() instanceof IronGolem))) {
				Random r = new Random();
				int random = r.nextInt(4);
				if(random == 0) {
					QubeMC.wantedLevel.replace(killer.getUniqueId(), 4);
					for(Entity entity : killer.getNearbyEntities(10, 10, 10)) {
						if(((entity instanceof PigZombie) || entity instanceof IronGolem)) {
							Creature pigzombie = (Creature)entity;
							pigzombie.setTarget(killer);
						} else if(entity instanceof Skeleton) {
							if(entity.getCustomName().equals("§9Army Soldier")) {
								Creature pigzombie = (Creature)entity;
								pigzombie.setTarget(killer);
							}
						}
					}
				}
			} else if((QubeMC.wantedLevel.get(killer.getUniqueId()) == 4) && ((event.getEntity() instanceof Skeleton) || (event.getEntity() instanceof PigZombie) || (event.getEntity() instanceof IronGolem))) {
				Random r = new Random();
				int random = r.nextInt(19);
				if(random == 0) {
					QubeMC.wantedLevel.replace(killer.getUniqueId(), 5);
					for(Entity entity : killer.getNearbyEntities(10, 10, 10)) {
						if(((entity instanceof PigZombie) || entity instanceof IronGolem)) {
							Creature pigzombie = (Creature)entity;
							pigzombie.setTarget(killer);
						} else if(entity instanceof Skeleton) {
							if(entity.getCustomName().equals("§9Army Soldier")) {
								Creature pigzombie = (Creature)entity;
								pigzombie.setTarget(killer);
							}
						}
					}
				}
			} else if((QubeMC.wantedLevel.get(killer.getUniqueId()) == 5) && ((event.getEntity() instanceof Skeleton) || (event.getEntity() instanceof PigZombie) || (event.getEntity() instanceof IronGolem))) {
				Random r = new Random();
				int random = r.nextInt(19);
				if(random == 0) {
					QubeMC.wantedLevel.replace(killer.getUniqueId(), 6);
					for(Entity entity : killer.getNearbyEntities(10, 10, 10)) {
						if(((entity instanceof PigZombie) || entity instanceof IronGolem)) {
							Creature pigzombie = (Creature)entity;
							pigzombie.setTarget(killer);
						} else if(entity instanceof Skeleton) {
							if(entity.getCustomName().equals("§9Army Soldier")) {
								Creature pigzombie = (Creature)entity;
								pigzombie.setTarget(killer);
							}
						}
					}
				}
			}
		}
	}
}