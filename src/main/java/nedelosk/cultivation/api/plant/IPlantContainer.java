package nedelosk.cultivation.api.plant;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPlantContainer {

	IPlantLogic getLogic();

	BlockPos getCoordinates();

	World getWorldObj();
}
