package nedelosk.cultivation.registry;

import net.minecraft.item.Item;

import nedelosk.cultivation.items.ItemGardeningKit;

public class ModItems {

	public static Item gardeningKit;

	public static void preInit(){
		gardeningKit = new ItemGardeningKit();
		RegistryHandler.registerItem(gardeningKit, "gardening_kit");
	}
}
