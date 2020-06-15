package skyrossm.clandestine.gui;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import skyrossm.clandestine.containers.SymbolChestContainer;
import skyrossm.clandestine.tiles.SymbolChestTileEntity;
import skyrossm.clandestine.util.Reference;

public class SymbolChestContainerGui extends GuiContainer {
    public static final int WIDTH = 175;
    public static final int HEIGHT = 165;
    
    private final SymbolChestTileEntity tileEntity;

    private static final ResourceLocation background = new ResourceLocation(Reference.MOD_ID, "textures/gui/symbolchest.png");

    public SymbolChestContainerGui(SymbolChestTileEntity tileEntity, SymbolChestContainer container) {
        super(container);
        this.tileEntity = tileEntity;
        xSize = WIDTH;
        ySize = HEIGHT;
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
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 4, Color.DARK_GRAY.getRGB());
		
		String lvlText = "Lv.99";
		this.fontRenderer.drawString(lvlText, 24 - (this.fontRenderer.getStringWidth(lvlText) / 2), 48, Color.YELLOW.getRGB());
		String lvlText2 = "Lv.27";
		this.fontRenderer.drawString(lvlText2, 56 - (this.fontRenderer.getStringWidth(lvlText2) / 2), 48, Color.YELLOW.getRGB());
	}

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    	GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        int offsetBar = 25;
        drawTexturedModalRect(guiLeft + 11, guiTop + 59 + 12 - offsetBar, 176, 12 - offsetBar, offsetBar - 13, 20);
        drawTexturedModalRect(guiLeft + 43, guiTop + 59 + 25 - offsetBar, 176, 32 - offsetBar, offsetBar + 1, 20);
		
        ArrayList<ItemStack> symbols = tileEntity.getSymbols();
    }

}
