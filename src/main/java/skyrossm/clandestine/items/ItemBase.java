package skyrossm.clandestine.items;

import net.minecraft.item.Item;
import skyrossm.clandestine.ClandestineMain;
import skyrossm.clandestine.creativetab.ClandestineCreativeTabs;
import skyrossm.clandestine.init.IHasModel;
import skyrossm.clandestine.init.ModItems;

public class ItemBase extends Item implements IHasModel {
	
	public ItemBase(String name) {
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(ClandestineCreativeTabs.CLANDESTINE_ITEM_TAB);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		ClandestineMain.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
