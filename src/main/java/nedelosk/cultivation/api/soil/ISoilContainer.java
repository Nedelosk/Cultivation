package nedelosk.cultivation.api.soil;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface ISoilContainer {

	@Nullable
	ISoil getSoil(IBlockState state, IBlockAccess world, BlockPos pos);

	void setSoil(IBlockState state, IBlockAccess world, BlockPos pos, ISoil soil);
}
