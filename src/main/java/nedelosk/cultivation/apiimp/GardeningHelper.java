package nedelosk.cultivation.apiimp;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import nedelosk.cultivation.api.IGardeningHelper;
import nedelosk.cultivation.api.plant.ICultivationPlant;
import nedelosk.cultivation.api.plant.IPlantContainer;
import nedelosk.cultivation.api.soil.ISoilContainer;
import nedelosk.cultivation.api.soil.ISoilType;

public enum GardeningHelper implements IGardeningHelper {
	INSTANCE;

	@Nullable
	@CapabilityInject(ISoilContainer.class)
	private static Capability<ISoilContainer> CONTAINER_CAPABILITY = null;

	@Nullable
	@Override
	public ISoilType getType(ItemStack itemStack) {
		return null;
	}

	@Override
	public boolean plant(IBlockAccess world, BlockPos pos, ICultivationPlant plant) {
		return false;
	}

	@Override
	public void onGrowPlant(IPlantContainer container) {

	}

	@Override
	public void onGrowFromSeed(IPlantContainer container) {

	}

	@Nullable
	@Override
	public ISoilContainer getContainer(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if(block instanceof ISoilContainer){
			return (ISoilContainer) block;
		}
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity instanceof ISoilContainer){
			return (ISoilContainer) tileEntity;
		}
		if(CONTAINER_CAPABILITY != null && tileEntity != null && tileEntity.hasCapability(CONTAINER_CAPABILITY, EnumFacing.UP)){
			return tileEntity.getCapability(CONTAINER_CAPABILITY, EnumFacing.UP);
		}
		return null;
	}


}
