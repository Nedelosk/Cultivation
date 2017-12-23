package nedelosk.cultivation.blocks;

import java.util.Locale;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import nedelosk.cultivation.api.soil.EnumAcidity;
import nedelosk.cultivation.api.soil.EnumMoisture;
import nedelosk.cultivation.api.soil.EnumNutrition;
import nedelosk.cultivation.api.soil.ISoil;
import nedelosk.cultivation.api.soil.ISoilContainer;
import nedelosk.cultivation.apiimp.GardeningHelper;
import nedelosk.cultivation.tile.TileGardeningBox;
import nedelosk.cultivation.utils.IBlockModelRegister;
import nedelosk.cultivation.utils.IItemModelRegister;

public class BlockGardeningBox extends Block implements IItemModelRegister, IBlockModelRegister, ISoilContainer, ITileEntityProvider {
	/*public static final PropertyEnum<Position> POSITION = PropertyEnum.create("position", Position.class);
	public static final PropertyEnum<EnumFacing> FACING = BlockHorizontal.FACING;*/

	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

	public static final PropertyBool NORTH_LEFT = PropertyBool.create("north_left");
	public static final PropertyBool NORTH_RIGHT = PropertyBool.create("north_right");
	public static final PropertyBool SOUTH_LEFT = PropertyBool.create("south_left");
	public static final PropertyBool SOUTH_RIGHT = PropertyBool.create("south_right");

	public static final PropertyEnum<EnumMoisture> MOISTURE = PropertyEnum.create("moisture", EnumMoisture.class);
	public static final PropertyEnum<EnumAcidity> ACIDITY = PropertyEnum.create("acidity", EnumAcidity.class);
	public final EnumNutrition nutrition;

	public BlockGardeningBox(EnumNutrition nutrition) {
		super(Material.WOOD);
		setSoundType(SoundType.WOOD);
		setCreativeTab(CreativeTabs.FOOD);
		setTickRandomly(true);
		this.nutrition = nutrition;
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		super.randomTick(worldIn, pos, state, random);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, MOISTURE, ACIDITY, /*POSITION, FACING*/ NORTH, EAST, SOUTH, WEST, NORTH_LEFT, NORTH_RIGHT, SOUTH_LEFT, SOUTH_RIGHT);
	}

	public static int getMeta(EnumAcidity acid, EnumMoisture moisture) {
		return acid.ordinal() * 3 + moisture.ordinal();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return getMeta(state.getValue(ACIDITY), state.getValue(MOISTURE));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumMoisture moisture = EnumMoisture.values()[meta % 3];
		EnumAcidity acidity = EnumAcidity.values()[meta / 3];
		return getDefaultState().withProperty(MOISTURE, moisture).withProperty(ACIDITY, acidity);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileGardeningBox();
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
		if (direction != EnumFacing.UP) {
			return false;
		}
		EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));
		return plantType == EnumPlantType.Crop || plantType == EnumPlantType.Plains;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		boolean west = canConnect(worldIn, pos.west());
		boolean north = canConnect(worldIn, pos.north());
		boolean south = canConnect(worldIn, pos.south());
		boolean east = canConnect(worldIn, pos.east());
		boolean northLeft = canConnect(worldIn, pos.west().north());
		boolean northRight = canConnect(worldIn, pos.east().north());
		boolean southLeft = canConnect(worldIn, pos.west().south());
		boolean southRight = canConnect(worldIn, pos.east().south());
		return state.withProperty(NORTH, north)
			.withProperty(WEST, west)
			.withProperty(SOUTH, south)
			.withProperty(EAST, east)
			.withProperty(NORTH_LEFT, northLeft)
			.withProperty(NORTH_RIGHT, northRight)
			.withProperty(SOUTH_LEFT, southLeft)
			.withProperty(SOUTH_RIGHT, southRight);
	}

	private boolean canConnect(IBlockAccess world, BlockPos pos){
		ISoilContainer soilContainer = GardeningHelper.INSTANCE.getContainer(world, pos);
		return soilContainer != null;
	}

	@Override
	public ISoil getSoil(IBlockState state, IBlockAccess world, BlockPos pos) {
		return null /*new Soil(nutrition, state.getValue(ACIDITY), state.getValue(MOISTURE), world.getBlockState(pos))*/;
	}

	@Override
	public void setSoil(IBlockState state, IBlockAccess world, BlockPos pos, ISoil soil) {
	}

	/* MODELS */
	@SideOnly(Side.CLIENT)
	@Override
	public void registerItemModels() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel() {
		ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(ACIDITY, MOISTURE).build());
	}

	public static enum Position implements IStringSerializable {
		DEFAULT, CENTER, EDGE, SINGLE, LINE, CROSSING;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ENGLISH);
		}
	}
}
