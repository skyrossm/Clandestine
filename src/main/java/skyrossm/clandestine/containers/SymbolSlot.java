package skyrossm.clandestine.containers;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SymbolSlot extends SlotItemHandler {

	public SymbolSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public int getSlotStackLimit() {
		return 1;
	}

}
