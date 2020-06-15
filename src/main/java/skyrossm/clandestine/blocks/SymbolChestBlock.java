package skyrossm.clandestine.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import skyrossm.clandestine.ClandestineMain;
import skyrossm.clandestine.tiles.SymbolChestTileEntity;

public class SymbolChestBlock extends BlockBase {

	public static final int GUI_ID = 1;

	public SymbolChestBlock(String name, Material mat) {
		super(name, mat);

		this.setSoundType(SoundType.WOOD);
		this.setHardness(0.2F);
		this.setResistance(0.5F);
		this.setLightLevel(0.2F);
		this.setHarvestLevel("axe", -1);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		return new SymbolChestTileEntity();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof SymbolChestTileEntity) {
			IItemHandler ish = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			for (int i = 0; i < ish.getSlots(); ++i) {
				ItemStack itemstack = ish.getStackInSlot(i);

				if (!itemstack.isEmpty()) {
					InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemstack);
				}
			}
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof SymbolChestTileEntity) {
			IItemHandler ish = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			if (ish.getSlots() == 0) {
				return 0;
			} else {
				int i = 0;
				float f = 0.0F;

				for (int j = 0; j < ish.getSlots(); ++j) {
					ItemStack itemstack = ish.getStackInSlot(j);

					if (!itemstack.isEmpty()) {
						f += (float) itemstack.getCount()
								/ (float) Math.min(ish.getSlotLimit(j), itemstack.getMaxStackSize());
						++i;
					}
				}

				f = f / (float) ish.getSlots();
				return MathHelper.floor(f * 14.0F) + (i > 0 ? 1 : 0);
			}
		}else {
			return 0;
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if (world.isRemote) {
			return true;
		}
		TileEntity te = world.getTileEntity(pos);
		if (!(te instanceof SymbolChestTileEntity)) {
			return false;
		}
		player.openGui(ClandestineMain.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

}
