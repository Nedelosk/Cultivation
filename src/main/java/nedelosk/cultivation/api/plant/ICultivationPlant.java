package nedelosk.cultivation.api.plant;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface ICultivationPlant {

	IPlantType getType();

	int getSections();

	int getMaxAge();

	IGrowthRequirement getGrowthRequirement();

	ICultivationPlant createOffspring(ICultivationPlant mate, IBlockAccess world, BlockPos pos);

	NBTTagCompound writeToNBT(NBTTagCompound compound);
}
