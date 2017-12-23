package nedelosk.cultivation.utils;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBlockModelRegister {

	@SideOnly(Side.CLIENT)
	void registerModel();
}
