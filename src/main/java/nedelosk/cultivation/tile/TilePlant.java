package nedelosk.cultivation.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import nedelosk.cultivation.api.plant.IPlantContainer;
import nedelosk.cultivation.api.plant.IPlantLogic;
import nedelosk.cultivation.apiimp.GardeningFactory;

public class TilePlant extends TileEntity implements IPlantContainer {

	private final IPlantLogic plantLogic;

	public TilePlant() {
		this.plantLogic = GardeningFactory.INSTANCE.createLogic(this);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("Logic", plantLogic.writeToNBT(new NBTTagCompound()));
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(compound.hasKey("Logic")){
			plantLogic.readFromNBT(compound.getCompoundTag("Logic"));
		}
	}

	@Override
	public IPlantLogic getLogic() {
		return plantLogic;
	}

	@Override
	public BlockPos getCoordinates() {
		return pos;
	}

	@Override
	public World getWorldObj() {
		return world;
	}
}
