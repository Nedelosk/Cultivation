package nedelosk.cultivation.api.plant;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.IStringSerializable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IPlantType extends IStringSerializable, Comparable<IPlantType> {

	@SideOnly(Side.CLIENT)
	ModelResourceLocation getModel(PlantSectionType type);
}
