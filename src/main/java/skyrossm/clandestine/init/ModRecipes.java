package skyrossm.clandestine.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void init() {
		GameRegistry.addSmelting(ModBlocks.ERTHA_BLOCK, new ItemStack(ModItems.WEAK_SYMBOL, 1), 1.6F);
	}
}
