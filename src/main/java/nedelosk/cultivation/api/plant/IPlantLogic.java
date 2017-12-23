package nedelosk.cultivation.api.plant;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import nedelosk.cultivation.api.soil.ISoilContainer;

public interface IPlantLogic {

	void update(Random rand);

	int getSection();

	void setSection(int section);

	int getAge();

	void setAge(int age);

	boolean canWilt();

	/**
	 * @return A plant can wilt if the plant is ripe and has not been harvested.
	 */
	boolean isWilted();

	void setWilted(boolean wilted);

	boolean isRipe();

	/**
	 * @return The position of the plant container that contains the genetic data.
	 */
	@Nullable
	BlockPos getMaster();

	boolean isMaster();

	@Nullable
	ICultivationPlant getPlant();

	@Nullable
	ISoilContainer getSoil();

	NBTTagCompound writeToNBT(NBTTagCompound compound);

	void readFromNBT(NBTTagCompound compound);
}
