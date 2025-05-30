package com.destroystokyo.paper.event.player;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Called when a player's spawn is set, either by themselves or otherwise.
 * <br>
 * Cancelling this event will prevent the spawn from being set.
 */
@NullMarked
public class PlayerSetSpawnEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Cause cause;
    private @Nullable Location location;
    private boolean forced;
    private boolean notifyPlayer;
    private @Nullable Component notification;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerSetSpawnEvent(final Player player, final Cause cause, final @Nullable Location location, final boolean forced, final boolean notifyPlayer, final @Nullable Component notification) {
        super(player);
        this.cause = cause;
        this.location = location;
        this.forced = forced;
        this.notifyPlayer = notifyPlayer;
        this.notification = notification;
    }

    /**
     * Gets the cause of this event.
     *
     * @return the cause
     */
    public Cause getCause() {
        return this.cause;
    }

    /**
     * Gets the location that the spawn is set to. The yaw
     * of this location is the spawn angle. Mutating this location
     * will change the resulting spawn point of the player. Use
     * {@link Location#clone()} to get a copy of this location.
     *
     * @return the spawn location, or {@code null} if removing the location
     */
    public @Nullable Location getLocation() {
        return this.location;
    }

    /**
     * Sets the location to be set as the spawn location. The yaw
     * of this location is the spawn angle.
     *
     * @param location the spawn location, or {@code null} to remove the spawn location
     */
    public void setLocation(final @Nullable Location location) {
        this.location = location != null ? location.clone() : null;
    }

    /**
     * Gets if this is a force spawn location
     *
     * @return {@code true} if forced
     */
    public boolean isForced() {
        return this.forced;
    }

    /**
     * Sets if this is a forced spawn location
     *
     * @param forced {@code true} to force
     */
    public void setForced(final boolean forced) {
        this.forced = forced;
    }

    /**
     * Gets if this action will notify the player their spawn
     * has been set.
     *
     * @return {@code true} to notify
     */
    public boolean willNotifyPlayer() {
        return this.notifyPlayer;
    }

    /**
     * Sets if this action will notify the player that their spawn
     * has been set.
     *
     * @param notifyPlayer {@code true} to notify
     */
    public void setNotifyPlayer(final boolean notifyPlayer) {
        this.notifyPlayer = notifyPlayer;
    }

    /**
     * Gets the notification message that will be sent to the player
     * if {@link #willNotifyPlayer()} returns {@code true}.
     *
     * @return {@code null} if no notification
     */
    public @Nullable Component getNotification() {
        return this.notification;
    }

    /**
     * Sets the notification message that will be sent to the player.
     *
     * @param notification {@code null} to send no message
     */
    public void setNotification(final @Nullable Component notification) {
        this.notification = notification;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public enum Cause {
        /**
         * When a player interacts successfully with a bed.
         */
        BED,
        /**
         * When a player interacts successfully with a respawn anchor.
         */
        RESPAWN_ANCHOR,
        /**
         * When a player respawns.
         */
        PLAYER_RESPAWN,
        /**
         * When the {@code /spawnpoint} command is used on a player.
         */
        COMMAND,
        /**
         * When a plugin uses {@link Player#setRespawnLocation(Location)} or
         * {@link Player#setRespawnLocation(Location, boolean)}.
         */
        PLUGIN,
        /**
         * Fallback cause.
         */
        UNKNOWN,
    }
}
