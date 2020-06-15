package skyrossm.clandestine.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import skyrossm.clandestine.containers.SymbolChestContainer;
import skyrossm.clandestine.gui.SymbolChestContainerGui;
import skyrossm.clandestine.tiles.SymbolChestTileEntity;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof SymbolChestTileEntity) {
			return new SymbolChestContainer(player.inventory, (SymbolChestTileEntity) te);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof SymbolChestTileEntity) {
			SymbolChestTileEntity containerTileEntity = (SymbolChestTileEntity) te;
			return new SymbolChestContainerGui(containerTileEntity,
					new SymbolChestContainer(player.inventory, containerTileEntity));
		}
		return null;
	}
}
