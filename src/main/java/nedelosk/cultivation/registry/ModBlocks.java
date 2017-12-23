package nedelosk.cultivation.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import nedelosk.cultivation.api.soil.EnumNutrition;
import nedelosk.cultivation.blocks.BlockCompost;
import nedelosk.cultivation.blocks.BlockFarmland;
import nedelosk.cultivation.blocks.BlockGardeningBox;
import nedelosk.cultivation.blocks.BlockPlant;

public class ModBlocks {
	public static Block farmland;
	public static Block raisedBedPoor;
	public static Block raisedBed;
	public static Block raisedBedRich;
	public static Block compost;
	public static Block plant;

	public static void preInit(){
		farmland = new BlockFarmland();
		RegistryHandler.registerBlock(farmland, "farmland", ItemBlock.class);
		raisedBedPoor = new BlockGardeningBox(EnumNutrition.POOR);
		RegistryHandler.registerBlock(raisedBedPoor, "raised_bed_poor");
		raisedBed = new BlockGardeningBox(EnumNutrition.NORMAL);
		RegistryHandler.registerBlock(raisedBed, "gardening_box", ItemBlock.class);
		raisedBedRich = new BlockGardeningBox(EnumNutrition.RICH);
		RegistryHandler.registerBlock(raisedBedRich, "raised_bed_rich");
		compost = new BlockCompost();
		RegistryHandler.registerBlock(compost, "compost", ItemBlock.class);
		plant = new BlockPlant();
		RegistryHandler.registerBlock(plant, "plant", ItemBlock.class);
	}

	public static Block getGardeningBox(EnumNutrition nutrition){
		if(nutrition == EnumNutrition.NORMAL){
			return raisedBed;
		}else if(nutrition == EnumNutrition.RICH){
			return raisedBedRich;
		}else{
			return raisedBedPoor;
		}
	}
}
