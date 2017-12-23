package nedelosk.cultivation.api.plant;

public interface IGrowthRequirement {

	float getChance();

	float getChance(IPlantContainer container);
}
