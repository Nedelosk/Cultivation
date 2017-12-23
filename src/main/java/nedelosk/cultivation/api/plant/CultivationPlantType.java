package nedelosk.cultivation.api.plant;

import java.util.EnumMap;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CultivationPlantType implements IPlantType {
	public static final IPlantType CROP = new CultivationPlantType("crop");
	public static final IPlantType SHRUB = new CultivationPlantType("shrub");

	private final EnumMap<PlantSectionType, ModelResourceLocation> locations = new EnumMap<>(PlantSectionType.class);
	private final String name;

	private CultivationPlantType(String name) {
		this(name, "normal");
	}

	private CultivationPlantType(String name, String variant) {
		for(PlantSectionType type : PlantSectionType.values()) {
			this.locations.put(type, new ModelResourceLocation("cultivation:" + name, variant));
		}
		this.name = name;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelResourceLocation getModel(PlantSectionType type) {
		return locations.get(type);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int compareTo(IPlantType o) {
		return 0;
	}
}
