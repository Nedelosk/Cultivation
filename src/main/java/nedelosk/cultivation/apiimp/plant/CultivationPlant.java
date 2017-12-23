package nedelosk.cultivation.apiimp.plant;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import nedelosk.cultivation.api.plant.ICultivationPlant;
import nedelosk.cultivation.api.plant.IGrowthRequirement;
import nedelosk.cultivation.api.plant.IPlantType;
import nedelosk.cultivation.apiimp.GardeningManager;

public final class CultivationPlant implements ICultivationPlant {

	private final IPlantType plantType;
	private final int sections;
	private final int maxAge;
	private final IGrowthRequirement growthRequirement;

	public CultivationPlant(NBTTagCompound compound) {
		this.plantType = GardeningManager.INSTANCE.getType(compound.getString("type"));
		this.sections = compound.getByte("sections");
		this.maxAge = compound.getByte("age");

	}

	public CultivationPlant(IPlantType plantType, int sections, int maxAge, IGrowthRequirement growthRequirement) {
		this.plantType = plantType;
		this.sections = sections;
		this.maxAge = maxAge;
		this.growthRequirement = growthRequirement;
	}

	@Override
	public IPlantType getType() {
		return plantType;
	}

	@Override
	public int getSections() {
		return sections;
	}

	@Override
	public int getMaxAge() {
		return maxAge;
	}

	@Override
	public IGrowthRequirement getGrowthRequirement() {
		return growthRequirement;
	}

	@Override
	public ICultivationPlant createOffspring(ICultivationPlant mate, IBlockAccess world, BlockPos pos) {
		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setString("type", plantType.getName());
		compound.setInteger("sections", sections);
		compound.setInteger("age", maxAge);
		return compound;
	}
}
