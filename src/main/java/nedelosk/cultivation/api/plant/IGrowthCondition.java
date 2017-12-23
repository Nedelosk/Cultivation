package nedelosk.cultivation.api.plant;

public interface IGrowthCondition {

	float getChance(IPlantContainer container);

	String getDescription();
}
