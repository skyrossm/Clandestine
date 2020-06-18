package skyrossm.clandestine.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import skyrossm.clandestine.containers.SymbolChestContainer;
import skyrossm.clandestine.items.SymbolBase;
import skyrossm.clandestine.tiles.SymbolChestTileEntity;
import skyrossm.clandestine.util.Reference;

public class SymbolChestContainerGui extends GuiContainer {
	public static final int WIDTH = 175;
	public static final int HEIGHT = 190;

	private final SymbolChestTileEntity tileEntity;

	private static final ResourceLocation background = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/symbolchest.png");

	public SymbolChestContainerGui(SymbolChestTileEntity tileEntity, SymbolChestContainer container) {
		super(container);
		this.tileEntity = tileEntity;
		xSize = WIDTH;
		ySize = HEIGHT;
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException  {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		HashMap<ItemStack, Integer> symbols = tileEntity.getSymbols();
		for(ItemStack symbol : symbols.keySet()) {
			SymbolBase symbolItem = (SymbolBase) symbol.getItem();
			
			int slot = symbols.get(symbol);
			if(symbolItem.isReadyToLevel()) {
				if(mouseY >= guiTop + 18 && mouseY <= guiTop + 70) {
					if(mouseX >= guiLeft + 9 + (slot * 32) && mouseX <= (slot * 32) + guiLeft + 38) {
						symbolItem.levelUp();
					}
				}
			}
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = "Arcane Symbols";
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 4,
				Color.DARK_GRAY.getRGB());
		
		String arcaneForce = "+ 30 AF";
		this.fontRenderer.drawStringWithShadow(arcaneForce, 64 - (this.fontRenderer.getStringWidth(arcaneForce) / 2), (this.ySize / 2) - 12,
				Color.MAGENTA.getRGB());
		
		String damageBoost = "+ 1.0 \u2764";
		this.fontRenderer.drawStringWithShadow(damageBoost, 144 - (this.fontRenderer.getStringWidth(damageBoost) / 2), (this.ySize / 2) - 12,
				Color.ORANGE.getRGB());
		
		HashMap<ItemStack, Integer> symbols = tileEntity.getSymbols();
		for(ItemStack symbol : symbols.keySet()) {
			SymbolBase symbolItem = (SymbolBase) symbol.getItem();
			
			//Draw level text
			int slot = symbols.get(symbol);
			int slotX = 24 + (slot * 32);
			int slotY = 48;
			int color = Color.YELLOW.getRGB();
			String lvlText = "Lv." + symbolItem.getLevel();
			if(mouseY >= guiTop + 18 && mouseY <= guiTop + 70) {
				if(mouseX >= guiLeft + 9 + (slot * 32) && mouseX <= (slot * 32) + guiLeft + 38) {
					color = Color.WHITE.getRGB();
				}
			}
			this.fontRenderer.drawString(lvlText, slotX - (this.fontRenderer.getStringWidth(lvlText) / 2), slotY, color);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		HashMap<ItemStack, Integer> symbols = tileEntity.getSymbols();
		for(ItemStack symbol : symbols.keySet()) {
			SymbolBase symbolItem = (SymbolBase) symbol.getItem();
			
			//Draw experience bar
			int exp = symbolItem.getExperience();
			int reqExp = symbolItem.getRequiredEXP();
			double percent = exp /(double) reqExp;
			
			int slot = symbols.get(symbol);
			int slotX = 11 + (slot * 32);
			int slotY = 46;
			if(symbolItem.isReadyToLevel()) {
				drawTexturedModalRect(guiLeft + slotX, guiTop + slotY + 13, 176, 7, 26, 7);
			}else {
				int norm = (int) (percent * 26);
				drawTexturedModalRect(guiLeft + slotX, guiTop + slotY, 176, -13, norm, 20);
			}
		}
	}
}
