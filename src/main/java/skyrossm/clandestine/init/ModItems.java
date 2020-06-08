package skyrossm.clandestine.init;

import java.util.ArrayList;

import net.minecraft.item.Item;
import skyrossm.clandestine.items.SymbolBase;

public class ModItems {
	
	public static final ArrayList<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item WEAK_SYMBOL = new SymbolBase("weak_symbol", 30, 1.0F, 12);
	public static final Item ERTHA_SYMBOL = new SymbolBase("ertha_symbol", 30, 1.0F, 12);
	public static final Item MEDIAL_SYMBOL = new SymbolBase("medial_symbol", 30, 1.0F, 12);
	public static final Item AFICIONADO_SYMBOL = new SymbolBase("aficionado_symbol", 30, 1.0F, 12);
	public static final Item DOYEN_SYMBOL = new SymbolBase("doyen_symbol", 30, 1.0F, 12);
	public static final Item PERORATION_SYMBOL = new SymbolBase("peroration_symbol", 30, 1.0F, 12);
}
