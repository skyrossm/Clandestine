package skyrossm.clandestine.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import skyrossm.clandestine.ClandestineMain;
import skyrossm.clandestine.creativetab.ClandestineCreativeTabs;
import skyrossm.clandestine.init.ModBlocks;
import skyrossm.clandestine.init.ModItems;
import skyrossm.clandestine.util.interfaces.IHasModel;

public class BlockBase extends Block implements IHasModel {

	public BlockBase(String name, Material mat) {
		super(mat);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(ClandestineCreativeTabs.CLANDESTINE_BLOCK_TAB);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		ClandestineMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

}
