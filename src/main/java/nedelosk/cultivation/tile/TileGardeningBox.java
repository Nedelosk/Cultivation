package nedelosk.cultivation.tile;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import nedelosk.cultivation.api.soil.ISoil;
import nedelosk.cultivation.api.soil.ISoilContainer;
import nedelosk.cultivation.api.soil.ISoilType;
import nedelosk.cultivation.apiimp.GardeningHelper;
import nedelosk.cultivation.apiimp.soil.Soil;

public class TileGardeningBox extends TileEntity implements ISoilContainer {
	@Nullable
	private ISoil soil;

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(compound.hasKey("Soil")) {
			soil = new Soil(compound.getCompoundTag("Soil"));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if(soil != null && !soil.isEmpty()) {
			compound.setTag("Soil", soil.writeToNBT(new NBTTagCompound()));
		}
		return compound;
	}

	public void setSoil(@Nullable ItemStack soilStack) {
		ISoilType soilType = GardeningHelper.INSTANCE.getType(soilStack);
		if(soilType != null) {
			this.soil = new Soil(soilType);
		}
	}

	@Nullable
	@Override
	public ISoil getSoil(IBlockState state, IBlockAccess world, BlockPos pos) {
		return soil;
	}

	@Override
	public void setSoil(IBlockState state, IBlockAccess world, BlockPos pos, ISoil soil) {
		this.soil = soil;
	}
}
