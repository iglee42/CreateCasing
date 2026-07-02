package fr.iglee42.createcasing.compat.sliceanddice;

import com.possible_triangle.sliceanddice.block.slicer.SlicerBlock;
import com.possible_triangle.sliceanddice.block.slicer.SlicerBlockEntity;
import com.possible_triangle.sliceanddice.block.slicer.SlicerRenderer;
import com.possible_triangle.sliceanddice.block.slicer.SlicerVisual;
import com.possible_triangle.sliceanddice.compat.ModCompat;
import com.possible_triangle.sliceanddice.index.SDBlockEntities;
import com.possible_triangle.sliceanddice.index.SDBlocks;
import com.simibubi.create.content.kinetics.press.PressingBehaviour;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.casings.CasingSet;
import fr.iglee42.createcasing.casings.CasingSets;
import fr.iglee42.createcasing.config.CCStress;
import fr.iglee42.createcasing.ponder.CustomPonderScenes;
import fr.iglee42.createcasing.registries.EncasedBlockEntities;
import fr.iglee42.createcasing.registries.EncasedBlockRegistrationHelpers;
import fr.iglee42.createcasing.registries.EncasedBlockStateGens;
import fr.iglee42.createcasing.registries.EncasedCreativeModeTabs;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.createmod.catnip.math.Pointing;
import net.createmod.catnip.math.VecHelper;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

import java.util.Objects;

import static fr.iglee42.createcasing.CreateCasing.REGISTRATE;
import static fr.iglee42.createcasing.registries.EncasedBlockStateGens.mixerModel;

public class EncasedSliceAndDiceCompat {

    public static void register(IEventBus bus){
        for (CasingSet set : CasingSets.getSets()) {
            if (set.doesGenerateSlicer())
                set.setSlicer(createSlicer(set.getName()));
        }
        bus.addListener(EncasedSliceAndDiceCompat::modifyBeTypes);
        CasingSets.ANDESITE.setSlicer(()-> SDBlocks.SLICER.get());
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS,EncasedSliceAndDiceCompat::blockTag);
    }

    private static void blockTag(RegistrateTagsProvider<Block> provIn){
        TagGen.CreateTagsProvider<Block> prov = new TagGen.CreateTagsProvider<>(provIn, Block::builtInRegistryHolder);
        for (CasingSet set : CasingSets.getSets()) {
            if (set.doesGenerateSlicer()) {
                prov.tag(BlockTags.MINEABLE_WITH_PICKAXE).addOptional(CreateCasing.asResource(set.getName()+"_slicer"));
                prov.tag(BlockTags.MINEABLE_WITH_AXE).addOptional(CreateCasing.asResource(set.getName()+"_slicer"));
            }
        }
    }

    private static void modifyBeTypes(BlockEntityTypeAddBlocksEvent event){
        EncasedBlockEntities.register(event, SDBlockEntities.SLICER.get(), CasingSet::getSlicer,CasingSet::doesGenerateSlicer);
    }


    private static BlockEntry<SlicerBlock> createSlicer(String name) {
        return REGISTRATE.block(name+"_slicer",SlicerBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .blockstate((c,p)->p.simpleBlock(c.get(),slicerModel(p,name,false)))
                .transform(EncasedBlockRegistrationHelpers::cutoutMipped)
                .transform(CCStress.setImpact(4.0))
                .item(AssemblyOperatorBlockItem::new)
                .model((c,p)->p.getBuilder(c.getName()).parent(slicerModel(p,name,true)))
                .build()
                .register();
    }

    private static ModelFile slicerModel(RegistrateProvider p, String casing, boolean item){
        if (!item)
            return Objects.requireNonNull(EncasedBlockStateGens.createModelInBlock(p, "slicer/" + casing+"/block"))
                    .parent(new ModelFile.UncheckedModelFile("createcasing:block/mixer/"+casing+"/block"));
        else
            return Objects.requireNonNull(EncasedBlockStateGens.createModelInBlock(p, "slicer/" + casing+"/item"))
                    .parent(new ModelFile.UncheckedModelFile("sliceanddice:block/slicer/item"))
                    .texture("2",EncasedBlockStateGens.getCasingTexture(casing))
                    .texture("11",EncasedBlockStateGens.getPressPart(casing,"top"))
                    .texture("4",EncasedBlockStateGens.getMixerPart(casing,"side"))
                    .texture("particle",EncasedBlockStateGens.getCasingTexture(casing));
    }

    public static void registerPonderScenes(PonderSceneRegistrationHelper<ItemLike> helper) {
        helper.forComponents(CasingSets.getSets().stream().filter(CasingSet::doesGenerateSlicer).map(CasingSet::getSlicer).toList())
                .addStoryBoard(ResourceLocation.fromNamespaceAndPath("sliceanddice","slicer"), (builder,util)->{
                    CreateSceneBuilder scene = new CreateSceneBuilder(builder);
                    scene.title("slicer", "Cutting with the slicer");
                    scene.configureBasePlate(0,0,5);
                    scene.world().showSection(util.select().layer(0), Direction.UP);

                    var motor = util.select().fromTo(2, 1, 3, 2, 4, 3);
                    var beltSlicer = util.grid().at(2, 3, 2);

                    var basin = util.grid().at(1, 2, 3);
                    var basinSlicer = util.grid().at(1, 4, 3);

                    var bigCog = util.select().position(3, 0, 5);
                    var belt = util.select().fromTo(1, 1, 2, 4, 1, 2).add(util.select().fromTo(4, 1, 3, 4, 1, 5)).add(bigCog);

                    scene.world().setKineticSpeed(util.select().position(basinSlicer), -64F);
                    scene.world().setKineticSpeed(util.select().position(beltSlicer), -64F);
                    scene.world().setKineticSpeed(motor, 64F);
                    scene.world().setKineticSpeed(belt, -8F);
                    scene.world().setKineticSpeed(bigCog, 4F);

                    var beltSlicerSection =
                            scene.world().showIndependentSection(util.select().position(beltSlicer), Direction.UP);
                    scene.effects().indicateSuccess(beltSlicer);
                    scene.idle(5);

                    scene.world().showSection(motor, Direction.UP);
                    scene.idle(5);

                    scene.world().showSection(belt, Direction.SOUTH);
                    scene.idle(20);

                    var knife = new ItemStack(ModCompat.INSTANCE.getExampleTool());
                    scene.overlay().showControls(VecHelper.getCenterOf(beltSlicer.above()), Pointing.DOWN, 50).withItem(knife);
                    scene.world().modifyBlockEntity(beltSlicer, SlicerBlockEntity.class,it-> {
                        it.setHeldItem(knife);
                    });
                    scene.world().modifyBlockEntity(basinSlicer, SlicerBlockEntity.class,it-> {
                        it.setHeldItem(knife);
                    });

                    scene.overlay().showText(60).text("Right-click it with a varid tool").placeNearTarget()
                            .pointAt(util.vector().blockSurface(beltSlicer, Direction.WEST));

                    scene.idle(5);

                    var beltOutputPos = beltSlicer.below(2);
                    var beltInputPos = beltOutputPos.west();
                    scene.world().createItemOnBeltLike(beltInputPos, Direction.UP, new ItemStack(ModCompat.INSTANCE.getExampleInput()));

                    scene.idleSeconds(4);

                    var slices = new ItemStack(ModCompat.INSTANCE.getExampleOutput(), 7);
                    scene.world().removeItemsFromBelt(beltOutputPos);
                    var slicesInWorld = scene.world().createItemOnBelt(beltOutputPos, Direction.UP, slices);

                    scene.world().modifyBlockEntity(beltSlicer, SlicerBlockEntity.class,it-> {
                        it.getCuttingBehaviour().makePressingParticleEffect(
                                VecHelper.getCenterOf(beltOutputPos).add(0.0, 0.6, 0.0), slices
                        );
                    });

                    scene.idle(5);
                    scene.world().stallBeltItem(slicesInWorld, false);

                    scene.idleSeconds(3);
                    scene.addKeyframe();

                    scene.world().showSection(util.select().fromTo(1, 1, 3, 1, 4, 3), Direction.EAST);
                    scene.idle(5);
                    scene.world().hideIndependentSection(beltSlicerSection, Direction.EAST);

                    scene.overlay().showText(60).text("The slicer can also operate on a basin").placeNearTarget()
                            .pointAt(util.vector().blockSurface(basinSlicer, Direction.WEST));

                    scene.idle(5);
                    scene.world().setBlock(beltSlicer, Blocks.AIR.defaultBlockState(), false);

                    var basinOutputPos = basin.north().below();
                    for (int i=0; i <= 3; i++) {
                        scene.idleSeconds(2);
                        scene.world().modifyBlockEntity(basinSlicer, SlicerBlockEntity.class,it-> {
                            it.getCuttingBehaviour().start(PressingBehaviour.Mode.BASIN);
                        });
                        scene.idleSeconds(1);
                        scene.world().createItemOnBeltLike(basinOutputPos, Direction.SOUTH, slices);
                        scene.idleSeconds(1);
                        scene.world().createItemOnBeltLike(basinOutputPos, Direction.SOUTH, slices);
                    }
                });

    }
}
