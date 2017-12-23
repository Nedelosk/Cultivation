package nedelosk.cultivation.apiimp.plant;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import nedelosk.cultivation.api.CultivationAPI;
import nedelosk.cultivation.api.plant.ICultivationPlant;
import nedelosk.cultivation.api.plant.IPlantContainer;
import nedelosk.cultivation.api.plant.IPlantLogic;
import nedelosk.cultivation.api.soil.ISoilContainer;
import nedelosk.cultivation.apiimp.GardeningFactory;
import nedelosk.cultivation.apiimp.GardeningHelper;
import nedelosk.cultivation.config.Config;
import nedelosk.cultivation.registry.ModBlocks;

public class PlantLogic implements IPlantLogic {
	private final IPlantContainer container;
	@Nullable
	private ICultivationPlant plant;
	@Nullable
	private BlockPos master;
	private int section;
	private int age;
	private boolean wilted;

	public PlantLogic(IPlantContainer container) {
		this.container = container;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		if(master != null) {
			compound.setInteger("masterX", master.getX());
			compound.setInteger("masterY", master.getY());
			compound.setInteger("masterZ", master.getZ());
		}
		if(plant != null){
			compound.setTag("plant", plant.writeToNBT(new NBTTagCompound()));
		}
		compound.setByte("section", (byte)section);
		compound.setByte("age", (byte) age);
		compound.setBoolean("wilted", wilted);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		NBTTagCompound tagCompound = compound.getCompoundTag("plant");
		plant = GardeningFactory.INSTANCE.createPlant(tagCompound);
		master = new BlockPos(compound.getInteger("masterX"), compound.getInteger("masterY"), compound.getInteger("masterZ"));
		section = compound.getByte("section");
		age = compound.getByte("age");
		wilted = compound.getBoolean("wilted");
	}

	@Override
	public boolean canWilt() {
		return false;
	}

	@Override
	public boolean isWilted() {
		return wilted;
	}

	@Override
	public void setWilted(boolean wilted) {
		this.wilted = wilted;
	}

	@Override
	public boolean isRipe() {
		return plant != null && age == plant.getMaxAge();
	}

	@Override
	public int getSection() {
		return section;
	}

	@Override
	public void setSection(int section) {
		this.section = section;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public void update(Random rand) {
		World world = container.getWorldObj();
		BlockPos pos = container.getCoordinates();
		IBlockState blockState = world.getBlockState(pos);
		if(blockState.getBlock() != ModBlocks.plant){
			return;
		}
		if(getSection() > 0){
			return;
		}
		if(plant == null){
			return;
		}
		ISoilContainer soilContainer = getSoil();
		if(soilContainer == null){
			return;
		}
		float growthChance = plant.getGrowthRequirement().getChance(container);
		if(growthChance > rand.nextFloat() * 100){
			doAge();
		}
		matePlant(rand, 0.0F, 0.0F);
		plantOffspring(rand);
	}

	private void doAge() {
		if(age < plant.getMaxAge()){
			age++;
		}
		if(age == 1){
			CultivationAPI.gardeningHelper.onGrowFromSeed(container);
		}else {
			CultivationAPI.gardeningHelper.onGrowPlant(container);
		}
		markForUpdate();
	}

	private void matePlant(Random rand, float chancePollinate, float chanceSelfPollinate){
		if(Config.canSelfPollinate){

		}
		if(Config.canPollinateOtherPlants){

		}
	}

	private void plantOffspring(Random rand){
		if(Config.canPlantOffspring){

		}
	}

	private void mutatePlant(){

	}

	private void kill() {
		World world = container.getWorldObj();
		BlockPos pos = container.getCoordinates();
		if(age > 0){
			world.setBlockToAir(pos);
		}
		for(int yOffset = 1; world.getTileEntity(pos.up(yOffset)) instanceof IPlantContainer;yOffset++){
			world.setBlockToAir(pos);
		}
	}

	private void markForUpdate(){
		World world = container.getWorldObj();
		BlockPos pos = container.getCoordinates();
		if (!world.isRemote) {
			IBlockState blockState = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, blockState, blockState, 0);
		} else {
			world.markBlockRangeForRenderUpdate(pos, pos);
		}
	}

	@Nullable
	@Override
	public ICultivationPlant getPlant() {
		return plant;
	}

	@Nullable
	@Override
	public ISoilContainer getSoil() {
		World world = container.getWorldObj();
		BlockPos pos = container.getCoordinates();
		return GardeningHelper.INSTANCE.getContainer(world, pos);
	}

	@Override
	public BlockPos getMaster() {
		return master;
	}

	@Override
	public boolean isMaster() {
		return master == null;
	}
}
