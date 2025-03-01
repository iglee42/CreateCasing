package fr.iglee42.createcasing.config;

import com.simibubi.create.content.kinetics.BlockStressValues;
import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.infrastructure.config.CClient;
import com.simibubi.create.infrastructure.config.CCommon;
import com.simibubi.create.infrastructure.config.CServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import static com.simibubi.create.infrastructure.config.AllConfigs.server;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModConfigs {

	private static final Map<ModConfig.Type, ConfigBase> CONFIGS = new EnumMap<>(ModConfig.Type.class);

	//private static CCClient client;
	private static CCCommon common;
	//private static CCServer server;

	/*public static CCClient client() {
		return client;
	}*/

	public static CCCommon common() {
		return common;
	}

	/*public static CCServer server() {
		return server;
	}*/

	public static ConfigBase byType(ModConfig.Type type) {
		return CONFIGS.get(type);
	}

	private static <T extends ConfigBase> T register(Supplier<T> factory, ModConfig.Type side) {
		Pair<T, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(builder -> {
			T config = factory.get();
			config.registerAll(builder);
			return config;
		});

		T config = specPair.getLeft();
		config.specification = specPair.getRight();
		CONFIGS.put(side, config);
		return config;
	}

	public static void register(ModLoadingContext context, ModContainer container) {
		//client = register(CCClient::new, ModConfig.Type.CLIENT);
		common = register(CCCommon::new, ModConfig.Type.COMMON);
		//server = register(CCServer::new, ModConfig.Type.SERVER);

		for (Entry<ModConfig.Type, ConfigBase> pair : CONFIGS.entrySet())
			container.registerConfig(pair.getKey(), pair.getValue().specification);


		//BlockStressValues.registerProvider(context.getActiveNamespace(), common().kinetics.stressValues);


	}

	@SubscribeEvent
	public static void onLoad(ModConfigEvent.Loading event) {
		for (ConfigBase config : CONFIGS.values())
			if (config.specification == event.getConfig()
				.getSpec())
				config.onLoad();
	}

	@SubscribeEvent
	public static void onReload(ModConfigEvent.Reloading event) {
		for (ConfigBase config : CONFIGS.values())
			if (config.specification == event.getConfig()
				.getSpec())
				config.onReload();
	}

}
