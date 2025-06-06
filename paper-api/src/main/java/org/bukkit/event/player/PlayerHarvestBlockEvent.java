package org.bukkit.event.player;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called whenever a player harvests a block.
 * <br>
 * A 'harvest' is when a block drops an item (usually some sort of crop) and
 * changes state, but is not broken in order to drop the item.
 * <br>
 * This event is not called for when a block is broken, to handle that, listen
 * for {@link org.bukkit.event.block.BlockBreakEvent} and
 * {@link org.bukkit.event.block.BlockDropItemEvent}.
 */
public class PlayerHarvestBlockEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block harvestedBlock;
    private final EquipmentSlot hand;
    private final List<ItemStack> itemsHarvested;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerHarvestBlockEvent(@NotNull Player player, @NotNull Block harvestedBlock, @NotNull EquipmentSlot hand, @NotNull List<ItemStack> itemsHarvested) {
        super(player);
        this.harvestedBlock = harvestedBlock;
        this.hand = hand;
        this.itemsHarvested = itemsHarvested;
    }

    @ApiStatus.Internal
    @Deprecated(since = "1.19.2", forRemoval = true)
    public PlayerHarvestBlockEvent(@NotNull Player player, @NotNull Block harvestedBlock, @NotNull List<ItemStack> itemsHarvested) {
        this(player, harvestedBlock, EquipmentSlot.HAND, itemsHarvested);
    }

    /**
     * Gets the block that is being harvested.
     *
     * @return The block that is being harvested
     */
    @NotNull
    public Block getHarvestedBlock() {
        return this.harvestedBlock;
    }

    /**
     * Get the hand used to harvest the block.
     *
     * @return the hand
     */
    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
    }

    /**
     * Gets a list of items that are being harvested from this block.
     *
     * @return A list of items that are being harvested from this block
     */
    @NotNull
    public List<ItemStack> getItemsHarvested() {
        return this.itemsHarvested;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
