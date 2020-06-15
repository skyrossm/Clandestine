package skyrossm.clandestine.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import skyrossm.clandestine.tiles.SymbolChestTileEntity;

public class SymbolChestContainer extends Container {
	private SymbolChestTileEntity te;
	
	public SymbolChestContainer(IInventory pInv, SymbolChestTileEntity tileEntity) {
		this.te = tileEntity;
		addOwnSlots();
		addPlayerSlots(pInv);
	}
	
    private void addPlayerSlots(IInventory playerInventory) {
    	int offsetX = 8;
    	int offsetY = 84;
        // Slots for the main inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int x = offsetX + col * 18;
                int y = row * 18 + offsetY;
                this.addSlotToContainer(new Slot(playerInventory, (row * 9) + col + 9, x, y));
            }
        }

        // Slots for the hotbar
        for (int col = 0; col < 9; col++) {
            int x = offsetX + col * 18;
            int y = 58 + offsetY;
            this.addSlotToContainer(new Slot(playerInventory, col, x, y));
        }
    }

    private void addOwnSlots() {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
    	int offsetX = 15;
    	int offsetY = 23;

        // Add our own slots
        int slotIndex = 0;
        for (int i = 0; i < 5; i++) {
        	int x = offsetX + i * 32;
            int y = offsetY;
            this.addSlotToContainer(new SlotItemHandler(itemHandler, slotIndex++, x, y));
        }

    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < SymbolChestTileEntity.SIZE) {
                if (!this.mergeItemStack(itemstack1, SymbolChestTileEntity.SIZE, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, SymbolChestTileEntity.SIZE, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.canInteractWith(playerIn);
    }
}
