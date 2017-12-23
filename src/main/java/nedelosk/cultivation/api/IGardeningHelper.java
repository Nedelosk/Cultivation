package nedelosk.cultivation.api;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import nedelosk.cultivation.api.plant.ICultivationPlant;
import nedelosk.cultivation.api.plant.IPlantContainer;
import nedelosk.cultivation.api.soil.ISoilContainer;
import nedelosk.cultivation.api.soil.ISoilType;

public interface IGardeningHelper {

	@Nullable
	ISoilContainer getContainer(IBlockAccess world, BlockPos pos);

	@Nullable
	ISoilType getType(ItemStack itemStack);

	boolean plant(IBlockAccess world, BlockPos pos, ICultivationPlant plant);

	void onGrowPlant(IPlantContainer container);

	void onGrowFromSeed(IPlantContainer container);
}
