package fr.iglee42.createcasing.registries;

import com.simibubi.create.AllCreativeModeTabs;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.utils.DontShowInCreativeTab;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {

	private static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
		DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateCasing.MODID);

	public static final RegistryObject<CreativeModeTab> MAIN_TAB = TAB_REGISTER.register("tab",
		() -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.createcasing"))
			.withTabsBefore(AllCreativeModeTabs.MAIN_TAB.getKey())
			.icon(ModBlocks.BRASS_GEARBOX::asStack)
				.displayItems(new RegistrateDisplayItemsGenerator())
			.build());

	
	public static void register(IEventBus modEventBus) {
		TAB_REGISTER.register(modEventBus);
	}

	public static CreativeModeTab getBaseTab() {
		return MAIN_TAB.get();
	}

	public static class RegistrateDisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {

		private static boolean testExclusion(Item item) {
			return item instanceof BlockItem ? !(((BlockItem) item).getBlock() instanceof DontShowInCreativeTab) : !(item instanceof DontShowInCreativeTab);
		}

		private static List<RegistrateDisplayItemsGenerator.ItemOrdering> makeOrderings() {
			List<RegistrateDisplayItemsGenerator.ItemOrdering> orderings = new ReferenceArrayList<>();

			Map<ItemProviderEntry<?>, ItemProviderEntry<?>> simpleBeforeOrderings = Map.of(

			);

			Map<ItemProviderEntry<?>, ItemProviderEntry<?>> simpleAfterOrderings = Map.of(
					ModItems.VERTICAL_BRASS_GEARBOX, ModBlocks.BRASS_GEARBOX,
					ModItems.VERTICAL_COPPER_GEARBOX, ModBlocks.COPPER_GEARBOX,
					ModItems.VERTICAL_RAILWAY_GEARBOX, ModBlocks.RAILWAY_GEARBOX,
					ModItems.VERTICAL_INDUSTRIAL_IRON_GEARBOX, ModBlocks.INDUSTRIAL_IRON_GEARBOX
			);

			simpleBeforeOrderings.forEach((entry, otherEntry) -> {
				orderings.add(RegistrateDisplayItemsGenerator.ItemOrdering.before(entry.asItem(), otherEntry.asItem()));
			});

			simpleAfterOrderings.forEach((entry, otherEntry) -> {
				orderings.add(RegistrateDisplayItemsGenerator.ItemOrdering.after(entry.asItem(), otherEntry.asItem()));
			});

			return orderings;
		}

		private static Function<Item, ItemStack> makeStackFunc() {
			Map<Item, Function<Item, ItemStack>> factories = new Reference2ReferenceOpenHashMap<>();

			Map<ItemProviderEntry<?>, Function<Item, ItemStack>> simpleFactories = Map.of(

			);

			simpleFactories.forEach((entry, factory) -> {
				factories.put(entry.asItem(), factory);
			});

			return item -> {
				Function<Item, ItemStack> factory = factories.get(item);
				if (factory != null) {
					return factory.apply(item);
				}
				return new ItemStack(item);
			};
		}

		private static Function<Item, CreativeModeTab.TabVisibility> makeVisibilityFunc() {
			Map<Item, CreativeModeTab.TabVisibility> visibilities = new Reference2ObjectOpenHashMap<>();

			Map<ItemProviderEntry<?>, CreativeModeTab.TabVisibility> simpleVisibilities = Map.of(
			);

			simpleVisibilities.forEach((entry, factory) -> {
				visibilities.put(entry.asItem(), factory);
			});



			return item -> {
				CreativeModeTab.TabVisibility visibility = visibilities.get(item);
				if (visibility != null) {
					return visibility;
				}
				return CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;
			};
		}

		@Override
		public void accept(CreativeModeTab.ItemDisplayParameters pParameters, CreativeModeTab.Output output) {
			ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
			List<RegistrateDisplayItemsGenerator.ItemOrdering> orderings = makeOrderings();
			Function<Item, ItemStack> stackFunc = makeStackFunc();
			Function<Item, CreativeModeTab.TabVisibility> visibilityFunc = makeVisibilityFunc();
			RegistryObject<CreativeModeTab> tab = MAIN_TAB;

			List<Item> items = new LinkedList<>();
			items.addAll(collectItems(tab, itemRenderer, true));
			items.addAll(collectBlocks(tab));
			items.addAll(collectItems(tab, itemRenderer, false));

			applyOrderings(items, orderings);
			outputAll(output, items, stackFunc, visibilityFunc);
		}

		private List<Item> collectBlocks(RegistryObject<CreativeModeTab> tab) {
			List<Item> items = new ReferenceArrayList<>();
			for (RegistryEntry<Block> entry : CreateCasing.REGISTRATE.getAll(Registries.BLOCK)) {
				if (!CreateCasing.REGISTRATE.isInCreativeTab(entry, tab))
					continue;
				Item item = entry.get()
						.asItem();
				if (item == Items.AIR)
					continue;
				if (testExclusion(item))
					items.add(item);
			}
			items = new ReferenceArrayList<>(new ReferenceLinkedOpenHashSet<>(items));
			return items;
		}

		private List<Item> collectItems(RegistryObject<CreativeModeTab> tab, ItemRenderer itemRenderer, boolean special) {
			List<Item> items = new ReferenceArrayList<>();


			for (RegistryEntry<Item> entry : CreateCasing.REGISTRATE.getAll(Registries.ITEM)) {
				if (!CreateCasing.REGISTRATE.isInCreativeTab(entry, tab))
					continue;
				Item item = entry.get();
				if (item instanceof BlockItem)
					continue;
				BakedModel model = itemRenderer.getModel(new ItemStack(item), null, null, 0);
				if (model.isGui3d() != special)
					continue;
				if (testExclusion(item))
					items.add(item);
			}
			return items;
		}

		private static void applyOrderings(List<Item> items, List<RegistrateDisplayItemsGenerator.ItemOrdering> orderings) {
			for (RegistrateDisplayItemsGenerator.ItemOrdering ordering : orderings) {
				int anchorIndex = items.indexOf(ordering.anchor());
				if (anchorIndex != -1) {
					Item item = ordering.item();
					int itemIndex = items.indexOf(item);
					if (itemIndex != -1) {
						items.remove(itemIndex);
						if (itemIndex < anchorIndex) {
							anchorIndex--;
						}
					}
					if (ordering.type() == RegistrateDisplayItemsGenerator.ItemOrdering.Type.AFTER) {
						items.add(anchorIndex + 1, item);
					} else {
						items.add(anchorIndex, item);
					}
				}
			}
		}

		private static void outputAll(CreativeModeTab.Output output, List<Item> items, Function<Item, ItemStack> stackFunc, Function<Item, CreativeModeTab.TabVisibility> visibilityFunc) {
			for (Item item : items) {
				output.accept(stackFunc.apply(item), visibilityFunc.apply(item));
			}
		}

		private record ItemOrdering(Item item, Item anchor, RegistrateDisplayItemsGenerator.ItemOrdering.Type type) {
			public static RegistrateDisplayItemsGenerator.ItemOrdering before(Item item, Item anchor) {
				return new RegistrateDisplayItemsGenerator.ItemOrdering(item, anchor, RegistrateDisplayItemsGenerator.ItemOrdering.Type.BEFORE);
			}

			public static RegistrateDisplayItemsGenerator.ItemOrdering after(Item item, Item anchor) {
				return new RegistrateDisplayItemsGenerator.ItemOrdering(item, anchor, RegistrateDisplayItemsGenerator.ItemOrdering.Type.AFTER);
			}

			public enum Type {
				BEFORE,
				AFTER;
			}
		}
	}

}