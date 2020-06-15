package skyrossm.clandestine.dimensions.sky;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;
import skyrossm.clandestine.util.Config;
import skyrossm.clandestine.util.Reference;

public class ArcaneSkyRenderer extends IRenderHandler {

	private static final ResourceLocation SKY_TEXTURE_TOP = new ResourceLocation(Reference.MOD_ID,
			"textures/environment/top.png");
	private static final ResourceLocation SKY_TEXTURE_BOTTOM = new ResourceLocation(Reference.MOD_ID,
			"textures/environment/bottom.png");
	private static final ResourceLocation SKY_TEXTURE_FRONT = new ResourceLocation(Reference.MOD_ID,
			"textures/environment/front.png");
	private static final ResourceLocation SKY_TEXTURE_RIGHT = new ResourceLocation(Reference.MOD_ID,
			"textures/environment/right.png");
	private static final ResourceLocation SKY_TEXTURE_BACK = new ResourceLocation(Reference.MOD_ID,
			"textures/environment/back.png");
	private static final ResourceLocation SKY_TEXTURE_LEFT = new ResourceLocation(Reference.MOD_ID,
			"textures/environment/left.png");
	
	//private static final ResourceLocation BLACKHOLE_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/environment/blackhole.png");

	private int starGLCallList = -1;

	private VertexBuffer starVBO;

	private final VertexFormat vertexBufferFormat;

	private boolean vboEnabled;

	public ArcaneSkyRenderer() {
        this.vboEnabled = OpenGlHelper.useVbo();
		this.vertexBufferFormat = new VertexFormat();
		this.vertexBufferFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT,
				VertexFormatElement.EnumUsage.POSITION, 3));
		this.generateStars();
	}

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		if (this.starGLCallList == -1 && !vboEnabled) {
			System.out.println("Re-creating stars");
			generateStars();
		}
		this.renderSky(mc, world, partialTicks);
	}

	private void generateStars() {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();

		if (this.starVBO != null) {
			this.starVBO.deleteGlBuffers();
		}

		if (this.starGLCallList >= 0) {
			GLAllocation.deleteDisplayLists(this.starGLCallList);
			this.starGLCallList = -1;
		}

		if (this.vboEnabled) {
			this.starVBO = new VertexBuffer(this.vertexBufferFormat);
			this.renderStars(bufferbuilder);
			bufferbuilder.finishDrawing();
			bufferbuilder.reset();
			this.starVBO.bufferData(bufferbuilder.getByteBuffer());
		} else {
			this.starGLCallList = GLAllocation.generateDisplayLists(1);
			GlStateManager.pushMatrix();
			GlStateManager.glNewList(this.starGLCallList, 4864);
			this.renderStars(bufferbuilder);
			tessellator.draw();
			GlStateManager.glEndList();
			GlStateManager.popMatrix();
		}
	}

	private void renderSky(Minecraft mc, WorldClient world, float partialTicks) {
		GlStateManager.disableFog();
		GlStateManager.disableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.depthMask(false);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();

		// Rotate sky texture
		float ang = this.getSkyRotation(world.getWorldTime(), partialTicks) * 360.0F;
		GlStateManager.pushMatrix();
		GlStateManager.rotate(ang, 0.0F, 1.0F, 0.5F);
		
		for (int k1 = 0; k1 < 6; ++k1) {
			GlStateManager.pushMatrix();
			
			if (k1 == 0) {
				mc.getTextureManager().bindTexture(SKY_TEXTURE_BOTTOM);
			}

			if (k1 == 1) {
				mc.getTextureManager().bindTexture(SKY_TEXTURE_LEFT);
				GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
			}

			if (k1 == 2) {

				mc.getTextureManager().bindTexture(SKY_TEXTURE_RIGHT);
				GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
			}

			if (k1 == 3) {

				mc.getTextureManager().bindTexture(SKY_TEXTURE_TOP);
				GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
			}

			if (k1 == 4) {
				// Facing +X
				mc.getTextureManager().bindTexture(SKY_TEXTURE_FRONT);
				GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
			}

			if (k1 == 5) {

				mc.getTextureManager().bindTexture(SKY_TEXTURE_BACK);
				GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
			}

			bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
			bufferbuilder.pos(-100.0D, -100.0D, -100.0D).tex(0.0D, 0.0D).color(150, 150, 150, 255).endVertex();
			bufferbuilder.pos(-100.0D, -100.0D, 100.0D).tex(0.0D, 1.0D).color(150, 150, 150, 255).endVertex();
			bufferbuilder.pos(100.0D, -100.0D, 100.0D).tex(1.0D, 1.0D).color(150, 150, 150, 255).endVertex();
			bufferbuilder.pos(100.0D, -100.0D, -100.0D).tex(1.0D, 0.0D).color(150, 150, 150, 255).endVertex();
			tessellator.draw();
			GlStateManager.popMatrix();
		}
		
		// Render blue stars
		GlStateManager.disableTexture2D();
		GlStateManager.color(0.4F, 0.4F, 0.6F, 0.8F);
		if (this.vboEnabled) {
			this.starVBO.bindBuffer();
			GlStateManager.glEnableClientState(32884);
			GlStateManager.glVertexPointer(3, 5126, 12, 0);
			this.starVBO.drawArrays(7);
			this.starVBO.unbindBuffer();
			GlStateManager.glDisableClientState(32884);
		} else {
			GlStateManager.callList(this.starGLCallList);
		}
		//Render red stars
		GlStateManager.rotate(30.0F, 1.0F, 0.0F, 1.0F);
		GlStateManager.color(0.4F, 0.2F, 0.2F, 1.0F);
		if (this.vboEnabled) {
			this.starVBO.bindBuffer();
			GlStateManager.glEnableClientState(32884);
			GlStateManager.glVertexPointer(3, 5126, 12, 0);
			this.starVBO.drawArrays(7);
			this.starVBO.unbindBuffer();
			GlStateManager.glDisableClientState(32884);
		} else {
			GlStateManager.callList(this.starGLCallList);
		}

		
		//Render black hole
		/*
		GlStateManager.enableTexture2D();
		float f17 = 15.0F;
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.rotate(ang * 200.0F, 0.0F, -1.0F, 0.0F);
        mc.getTextureManager().bindTexture(BLACKHOLE_TEXTURE);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(-f17), 100.0D, (double)(-f17)).tex(0.0D, 0.0D).endVertex();
        bufferbuilder.pos((double)f17, 100.0D, (double)(-f17)).tex(1.0D, 0.0D).endVertex();
        bufferbuilder.pos((double)f17, 100.0D, (double)f17).tex(1.0D, 1.0D).endVertex();
        bufferbuilder.pos((double)(-f17), 100.0D, (double)f17).tex(0.0D, 1.0D).endVertex();
        tessellator.draw();
        */
		

		GlStateManager.popMatrix();
		GlStateManager.depthMask(true);
		GlStateManager.enableTexture2D();
		GlStateManager.enableAlpha();
	}

	private void renderStars(BufferBuilder bufferBuilderIn) {
		Random random = new Random(10842L);
		bufferBuilderIn.begin(7, DefaultVertexFormats.POSITION);

		for (int i = 0; i < Config.arcaneDimStars; ++i) {
			double d0 = (double) (random.nextFloat() * 2.0F - 1.0F);
			double d1 = (double) (random.nextFloat() * 2.0F - 1.0F);
			double d2 = (double) (random.nextFloat() * 2.0F - 1.0F);
			double d3 = (double) (0.15F + random.nextFloat() * 0.1F);
			double d4 = d0 * d0 + d1 * d1 + d2 * d2;

			if (d4 < 1.0D && d4 > 0.01D) {
				//Size of the star
				double randStarSize = (random.nextInt(2) * 1.0D) + 1.0D;
				d4 = randStarSize / Math.sqrt(d4);
				d0 = d0 * d4;
				d1 = d1 * d4;
				d2 = d2 * d4;
				double d5 = d0 * 100.0D;
				double d6 = d1 * 100.0D;
				double d7 = d2 * 100.0D;
				double d8 = Math.atan2(d0, d2);
				double d9 = Math.sin(d8);
				double d10 = Math.cos(d8);
				double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
				double d12 = Math.sin(d11);
				double d13 = Math.cos(d11);
				double d14 = random.nextDouble() * Math.PI * 2.0D;
				double d15 = Math.sin(d14);
				double d16 = Math.cos(d14);

				for (int j = 0; j < 4; ++j) {
					double d18 = (double) ((j & 2) - 1) * d3;
					double d19 = (double) ((j + 1 & 2) - 1) * d3;
					double d21 = d18 * d16 - d19 * d15;
					double d22 = d19 * d16 + d18 * d15;
					double d23 = d21 * d12 + 0.0D * d13;
					double d24 = 0.0D * d12 - d21 * d13;
					double d25 = d24 * d9 - d22 * d10;
					double d26 = d22 * d9 + d24 * d10;
					bufferBuilderIn.pos(d5 + d25, d6 + d23, d7 + d26).endVertex();
				}
			}
		}
	}

	public float getSkyRotation(long worldTime, float partialTicks) {
		int i = (int) (worldTime % 24000L);
		float f = ((float) i + partialTicks) / 24000.0F - 0.25F;

		if (f < 0.0F) {
			++f;
		}

		if (f > 1.0F) {
			--f;
		}

		float f1 = 1.0F - (float) ((Math.cos((double) f * Math.PI) + 1.0D) / 2.0D);
		f = f + (f1 - f) / 3.0F;
		return f;
	}

}
