package nedelosk.cultivation;

import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import nedelosk.cultivation.api.CultivationAPI;
import nedelosk.cultivation.apiimp.GardeningFactory;
import nedelosk.cultivation.apiimp.GardeningHelper;
import nedelosk.cultivation.apiimp.GardeningManager;
import nedelosk.cultivation.core.CommonProxy;
import nedelosk.cultivation.registry.ModBlocks;
import nedelosk.cultivation.registry.ModItems;
import nedelosk.cultivation.registry.RegistryHandler;

@Mod(modid = Cultivation.MOD_ID, name = Cultivation.NAME, version = Cultivation.VERSION)
public class Cultivation {
	public static final String MOD_ID = "cultivation";
	public static final String NAME = "Nede's Cultivation";
	public static final String VERSION = "@VERSION@";

	@SidedProxy(clientSide = "nedelosk.cultivation.client.core.ClientProxy", serverSide = "nedelosk.cultivation.core.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		CultivationAPI.gardeningFactory = GardeningFactory.INSTANCE;
		CultivationAPI.gardeningHelper = GardeningHelper.INSTANCE;
		CultivationAPI.gardeningManager = GardeningManager.INSTANCE;

		MinecraftForge.EVENT_BUS.register(new RegistryHandler());

		ModBlocks.preInit();
		ModItems.preInit();

		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);
	}
}
