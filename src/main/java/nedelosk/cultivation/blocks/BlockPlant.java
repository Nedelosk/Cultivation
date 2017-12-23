package nedelosk.cultivation.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import nedelosk.cultivation.api.plant.IPlantContainer;
import nedelosk.cultivation.api.plant.IPlantLogic;
import nedelosk.cultivation.api.plant.PlantSectionType;
import nedelosk.cultivation.blocks.properties.PropertyPlantType;

public class BlockPlant extends Block {
	public static final PropertyEnum<PlantSectionType> SECTION = PropertyEnum.create("section", PlantSectionType.class);
	public static final PropertyPlantType TYPE = new PropertyPlantType("type");

	public BlockPlant() {
		super(Material.PLANTS);
		setTickRandomly(true);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, SECTION, TYPE);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if(tileEntity instanceof IPlantContainer){
			IPlantContainer container = (IPlantContainer) tileEntity;
			IPlantLogic logic = container.getLogic();
			logic.update(rand);
		}
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if(tileEntity instanceof IPlantContainer){
			IPlantContainer container = (IPlantContainer) tileEntity;
			IPlantLogic logic = container.getLogic();
			int maxSections = logic.getPlant().getSections();
			int section = logic.getSection();
			return state.withProperty(SECTION, section == 0 ? PlantSectionType.BOTTOM : section == maxSections ? PlantSectionType.TOP : PlantSectionType.CENTER).withProperty(TYPE, logic.getPlant().getType());
		}
		return state;
	}
}
