package me.thomas.hungergames;

import me.thomas.hungergames.commands.*;
import me.thomas.hungergames.events.*;
import me.thomas.hungergames.events.kitsevents.*;
import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.KitInventory;
import me.thomas.hungergames.kits.KitsManager;
import me.thomas.hungergames.kits.SelectKit;
import me.thomas.hungergames.player.HgPlayer;
import me.thomas.hungergames.recipes.Soup;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.FileUtil;

import java.net.InetAddress;

public class HungerGames extends JavaPlugin {
	
	/* =-=-=O QUE FAZER=-=-=
	 * 
	 * Escolher items pros baus
	 * Colocar os itens aleatoriamente
	 *
	 * Final do jogo
	 *
	 * MySQL pra salvar status dos players
	 * 
	 */

	public static HungerGames instance;
	public GameManager gameManager;
	public HungerGames(){
		instance = this;
	}
	
	@Override
	public void onEnable() {
		gameManager = new GameManager();
		gameManager.waitingPlayers();
		registerCommands();
		registerEvents();

		Soup.register();
		KitsManager.registerKits();
		KitInventory.register();

		Bukkit.getOnlinePlayers().forEach(player -> {
			HgPlayer hgPlayer = new HgPlayer(player.getUniqueId());
			gameManager.getPlayerManager().addHgPlayer(hgPlayer);
		});
	}
	
	@Override
	public void onDisable() {

	}

	public void registerEvents() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new Compass(), this);
		pm.registerEvents(new DamageDelay(), this);
		pm.registerEvents(new GamePause(), this);
		pm.registerEvents(new HealingSoup(), this);
		pm.registerEvents(new PlayerDamage(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new PlayerJoinLeave(), this);
		pm.registerEvents(new SelectKit(), this);
		pm.registerEvents(new WeaponAttackSpeed(), this);
		
		pm.registerEvents(new Achilles(), this);
		pm.registerEvents(new AntiKB(), this);
		pm.registerEvents(new Archer(), this);
		pm.registerEvents(new Assassin(), this);
		pm.registerEvents(new Backpack(), this);
		pm.registerEvents(new Barbarian(), this);
		pm.registerEvents(new Beserker(), this);
		pm.registerEvents(new Bob(), this);
		pm.registerEvents(new Boxer(), this);
		pm.registerEvents(new Cannibal(), this);
		pm.registerEvents(new Cookiemonster(), this);
		pm.registerEvents(new Cultivator(), this);
		pm.registerEvents(new Endermage(), this);
		pm.registerEvents(new Fisherman(), this);
		pm.registerEvents(new Hulk(), this);
		pm.registerEvents(new Lumberjack(), this);
		pm.registerEvents(new Ninja(), this);
		pm.registerEvents(new Poseidon(), this);
		pm.registerEvents(new Stomper(), this);
		pm.registerEvents(new Thor(), this);
		pm.registerEvents(new Vampire(), this);
		pm.registerEvents(new Viking(), this);
		pm.registerEvents(new Viper(), this);
    }

    public void registerCommands() {
		this.getCommand("kits").setExecutor(new KitsCommand());
		this.getCommand("newspawn").setExecutor(new NewSpawnCommand());
		this.getCommand("game").setExecutor(new GameCommands());
		this.getCommand("chests").setExecutor(new SpawnChests());
		this.getCommand("hgstats").setExecutor(new PlayerStatsCommand());
	}

	public static HungerGames getInstance(){
		return instance;
	}

}
