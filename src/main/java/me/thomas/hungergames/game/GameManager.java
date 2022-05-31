package me.thomas.hungergames.game;

import me.thomas.hungergames.HungerGames;
import me.thomas.hungergames.kits.KitsManager;
import me.thomas.hungergames.player.HgPlayer;
import me.thomas.hungergames.player.PlayerManager;
import me.thomas.hungergames.player.PlayerStats;
import me.thomas.hungergames.scoreboard.ScoreboardManager;
import me.thomas.hungergames.world.WorldManager;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;

public class GameManager {

    private static GameManager gameManager;
    private GameState gameState;
    private final ScoreboardManager scoreboardManager;
    private final KitsManager kitsManager;
    private final PlayerManager playerManager;
    private final PlayerStats playerStats;
    private final WorldManager worldManager;
    private boolean pause;
    private boolean invinciblePeriod;
    public GameManager() {
        gameManager = this;
        scoreboardManager = new ScoreboardManager();
        kitsManager = new KitsManager();
        playerManager = new PlayerManager();
        playerStats = new PlayerStats();
        worldManager = new WorldManager();
    }

    public static GameManager getGameManager(){
        return gameManager;
    }

    public ScoreboardManager getScoreboardManager(){
        return scoreboardManager;
    }

    public KitsManager getKitsManager(){
        return kitsManager;
    }

    public PlayerManager getPlayerManager(){
        return playerManager;
    }

    public PlayerStats getPlayerStats(){
        return playerStats;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public GameState getGameState(){
        return gameState;
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isInvinciblePeriod(){
        return invinciblePeriod;
    }

    public void setInvinciblePeriod(boolean invinciblePeriod){
        this.invinciblePeriod = invinciblePeriod;
    }

    public World getWorld() {
        for (World world : Bukkit.getWorlds()) {
            if (world.getEnvironment() == World.Environment.NORMAL)
                return world;
        }
        return null;
    }

    public void loadNewGame() {
        World world = getWorld();
        WorldCreator creator = new WorldCreator("test");
        creator.environment(World.Environment.NORMAL);
        creator.type(WorldType.NORMAL);

        File file = world.getWorldFolder();
        worldManager.unloadWorld(world);
        worldManager.deleteWorld(file);
        //if (getWorldManager().deleteWorld(file))
        World world1 = creator.createWorld();
        world1.getWorldFolder().renameTo(new File("world"));
        setGameState(GameState.WAITING);
        //Bukkit.getServer().reload();
    }

    public void waitingPlayers() {
        this.setGameState(GameState.WAITING);
        Bukkit.getLogger().info("[HungerGames] Players are allowed to join");
        Bukkit.getScheduler().scheduleSyncDelayedTask(HungerGames.getPlugin(HungerGames.class), (Runnable) new Timer(), 1L);
    }

    public void startGame() {
        this.setGameState(GameState.PLAYING);
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.getScoreboardManager().setScoreboard(player);
            this.getKitsManager().giveKitItems(player);
            player.teleport(player.getWorld().getSpawnLocation());
        });
        Bukkit.getWorlds().forEach(world -> world.getWorldBorder().setSize(2000));
        this.setInvinciblePeriod(true);
        this.broadcastMessage(ChatColor.RED + "Invincibility period will end in " + ChatColor.GOLD + "1.5 minutes");
    }

    public void endGame() {
        this.setGameState(GameState.ENDED);
        HgPlayer hgPlayer = getPlayerManager().getAlivePlayers().get(0);
        hgPlayer.getPlayer().sendTitle(ChatColor.GREEN + "Congratulations!", ChatColor.RED + "You won the game!", 5, 70, 5);
        getPlayerManager().getDeadPlayers().forEach(player -> player.getPlayer().sendTitle(
                ChatColor.YELLOW + "Game ended!", ChatColor.RED + "Thank you for playing", 5, 70, 5));

        // stats message
        /*TextComponent message = new TextComponent("here");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()));
        broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aClick &b" + message +  "&ato see your stats for this game."));*/
    }

    public void broadcastMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }
}
