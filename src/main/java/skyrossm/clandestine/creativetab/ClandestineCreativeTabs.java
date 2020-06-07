package skyrossm.clandestine.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import skyrossm.clandestine.init.ModBlocks;
import skyrossm.clandestine.init.ModItems;
import skyrossm.clandestine.util.Reference;

public class ClandestineCreativeTabs {
    public static final CreativeTabs CLANDESTINE_ITEM_TAB = new CreativeTabs(Reference.MOD_ID)
    {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.WEAK_SYMBOL, 1);
        }
    };
    public static final CreativeTabs CLANDESTINE_BLOCK_TAB = new CreativeTabs(Reference.MOD_ID)
    {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Item.getItemFromBlock(ModBlocks.ERTHA_BLOCK), 1);
        }
    };
}
