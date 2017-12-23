package nedelosk.cultivation.registry;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import net.minecraftforge.event.RegistryEvent;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import nedelosk.cultivation.Cultivation;

public class RegistryHandler {
	public static final Set<Block> blocks = new HashSet<>();
	public static final Set<Item> items = new HashSet<>();

	public static void registerBlock(Block block, String name, Class<? extends ItemBlock> itemClass){
		registerBlock(block, name);
		try {
			ItemBlock itemBlock = itemClass.getConstructor(Block.class).newInstance(block);
			registerItem(itemBlock, name);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void registerBlock(Block block, String name){
		block.setRegistryName(name);
		block.setUnlocalizedName(Cultivation.MOD_ID + "." + name);
		blocks.add(block);
	}

	public static void registerItem(Item item, String name){
		item.setRegistryName(name);
		item.setUnlocalizedName(Cultivation.MOD_ID + "." + name);
		items.add(item);
	}

	@SubscribeEvent
	public void onBlockRegistration(RegistryEvent.Register<Block> event){
		blocks.forEach(b -> event.getRegistry().register(b));
	}

	@SubscribeEvent
	public void onItemRegistration(RegistryEvent.Register<Item> event){
		items.forEach(i -> event.getRegistry().register(i));
	}
}
