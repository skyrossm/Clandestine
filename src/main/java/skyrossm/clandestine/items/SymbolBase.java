package skyrossm.clandestine.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SymbolBase extends ItemBase {
	
	private int initialArcaneForce;
	private float initialDamageBonus;
	
	private int level;
	
	private int experience;
	
	private int requiredEXP;
	
	private boolean readyToLevel;
	
	public SymbolBase(String name, int initialAF, float initialDB, int initialReqEXP) {
		super(name);
		initialArcaneForce = initialAF;
		initialDamageBonus = initialDB;
		setLevel(1);
		setRequiredEXP(initialReqEXP);
		setExperience(1);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ChatFormatting.DARK_RED + "Current symbol level: " + getLevel());
		tooltip.add(ChatFormatting.AQUA + "Current symbol experience: " + getExperience() + "/" + getRequiredEXP());
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(@Nullable World worldIn, EntityPlayer player, EnumHand handIn) {
		this.addExperience(1);
		return super.onItemRightClick(worldIn, player, handIn);
	}
	
	
	/**
	 * @return the arcane force for this symbol
	 */
	public int getArcaneForce() {
		return getLevel() * (getInitialArcaneForce() / 3);
	}

	/**
	 * @return the initial arcane force for this symbol
	 */
	public int getInitialArcaneForce() {
		return initialArcaneForce;
	}

	/**
	 * @return the initial damage bonus for this symbol
	 */
	public float getInitialDamageBonus() {
		return initialDamageBonus;
	}

	/**
	 * @return the level of this symbol
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set this symbol to
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Increases symbol level
	 */
	public void levelUp() {
		setLevel(getLevel() + 1);
		setRequiredEXP(getRequiredEXP() + (getLevel() * 3));
		setReadyToLevel(false);
	}

	/**
	 * @return the experience (progress to next level) of this symbol
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * @param experience the experience to set this symbol to
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	/**
	 * @param experience the experience to add to this symbol
	 * 
	 */
	public void addExperience(int experience) {
		int experienceToAdd = getExperience() + experience;
		if(experienceToAdd >= getRequiredEXP()) {
			int extraEXP = getRequiredEXP() - experienceToAdd;
			setReadyToLevel(true);
			if(extraEXP < 1) extraEXP = 1;
			setExperience(extraEXP);
		}else {
			setExperience(experienceToAdd);
		}
	}

	/**
	 * @return the required exp for next symbol level
	 */
	public int getRequiredEXP() {
		return requiredEXP;
	}

	/**
	 * @param requiredEXP the required exp for the next symbol level
	 */
	public void setRequiredEXP(int requiredEXP) {
		this.requiredEXP = requiredEXP;
	}

	/**
	 * @return the readyToLevel
	 */
	public boolean isReadyToLevel() {
		return readyToLevel;
	}

	/**
	 * @param readyToLevel the readyToLevel to set
	 */
	public void setReadyToLevel(boolean readyToLevel) {
		this.readyToLevel = readyToLevel;
	}
	

}
