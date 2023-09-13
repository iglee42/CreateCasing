package fr.iglee42.createcasing.api;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.content.fluids.pipes.EncasedPipeBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.*;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import fr.iglee42.createcasing.blocks.api.ApiGearboxBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedCogwheelBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedPipeBlock;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedShaftBlock;
import fr.iglee42.createcasing.items.ApiVerticalGearboxItem;
import fr.iglee42.createcasing.utils.Deferred;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

/**
 * @author iglee42
 */
public class CreateCasingApi {


    /**
     * Create a custom encased shaft (normal shaft from Create)
     * <br>
     * This method just register the block you need to make the blockstate & models (item & block)
     * @param registrate The registrate of your mod (e.g. Create.REGISTRATE)
     * @param name The name of the casing in your shaft (e.g. andesite)
     * @param casing A supplier of the block of the casing will be applied to the shaft (e.g. AllBlocks.ANDESITE_CASING)
     * @param connectedTexturesSprite The sprite with the connected texture of your casing (e.g. AllSpriteShifts.ANDESITE_CASING)
     * @return The Block Entry of the Encased Shaft
     */
    public static BlockEntry<PublicEncasedShaftBlock> createEncasedShaft(CreateRegistrate registrate, String name, Supplier<Block> casing, CTSpriteShiftEntry connectedTexturesSprite){
        return registrate.block(name+"_encased_shaft", p -> new PublicEncasedShaftBlock(p, casing))
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.encasedShaft(name, () -> connectedTexturesSprite))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
                .transform(axeOrPickaxe())
                .register();
    }


    /**
     * Create a custom encased cogwheel (not the large)
     * <br>
     * This method just register the block you need to make the blockstate & models (item & block)
     * @param registrate The registrate of your mod (e.g. Create.REGISTRATE)
     * @param name The name of the casing in your shaft (e.g. andesite)
     * @param casing A supplier of the block of the casing will be applied to the shaft (e.g. AllBlocks.ANDESITE_CASING)
     * @param connectedTexturesSprite The sprite with the connected texture of your casing (e.g. AllSpriteShifts.ANDESITE_CASING)
     * @param verticalCogwheelSide The sprite with the side of the cogwheel (e.g. AllSpriteShifts.ANDESITE_ENCASED_COGWHEEL_SIDE)
     * @param horizontalCogwheelSide The sprite with the other side of the cogwheel (e.g. AllSpriteShifts.ANDESITE_ENCASED_COGWHEEL_OTHERSIDE)
     * @return The Block Entry of the Encased Cogwheel
     */
    public static BlockEntry<PublicEncasedCogwheelBlock> createEncasedCogwheel(CreateRegistrate registrate, String name, Supplier<Block> casing, CTSpriteShiftEntry connectedTexturesSprite,CTSpriteShiftEntry verticalCogwheelSide,CTSpriteShiftEntry horizontalCogwheelSide){
        return registrate.block(name+"_encased_cogwheel", p -> new PublicEncasedCogwheelBlock(p, false, casing))
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.encasedCogwheel(name, () -> connectedTexturesSprite))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.COGWHEEL))
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(connectedTexturesSprite,
                        Couple.create(verticalCogwheelSide,
                                horizontalCogwheelSide))))
                .transform(axeOrPickaxe())
                .register();
    }

    /**
     * Create a custom encased large cogwheel
     * <br>
     * This method just register the block you need to make the blockstate & models (item & block)
     * @param registrate The registrate of your mod (e.g. Create.REGISTRATE)
     * @param name The name of the casing in your shaft (e.g. andesite)
     * @param casing A supplier of the block of the casing will be applied to the shaft (e.g. AllBlocks.ANDESITE_CASING)
     * @param connectedTexturesSprite The sprite with the connected texture of your casing (e.g. AllSpriteShifts.ANDESITE_CASING)
     * @return The Block Entry of the Encased Large Cogwheel
     */
    public static BlockEntry<PublicEncasedCogwheelBlock> createEncasedLargeCogwheel(CreateRegistrate registrate, String name, Supplier<Block> casing, CTSpriteShiftEntry connectedTexturesSprite){
        return registrate.block(name+"_encased_large_cogwheel", p -> new PublicEncasedCogwheelBlock(p, true, casing))
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BuilderTransformers.encasedLargeCogwheel(name, () -> connectedTexturesSprite))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
                .transform(axeOrPickaxe())
                .register();
    }

    /**
     * Create a custom encased pipe
     * <br>
     * This method just register the block you need to make the blockstate & models (item & block)
     * @param registrate The registrate of your mod (e.g. Create.REGISTRATE)
     * @param name The name of the casing in your shaft (e.g. andesite)
     * @param casing A supplier of the block of the casing will be applied to the shaft (e.g. AllBlocks.ANDESITE_CASING)
     * @param connectedTexturesSprite The sprite with the connected texture of your casing (e.g. AllSpriteShifts.ANDESITE_CASING)
     * @return The Block Entry of the Encased Pipe
     */
    public static BlockEntry<PublicEncasedPipeBlock> createEncasedPipe(CreateRegistrate registrate, String name, Supplier<Block> casing, CTSpriteShiftEntry connectedTexturesSprite){
        return registrate.block(name+"_encased_fluid_pipe", p -> new PublicEncasedPipeBlock(p, casing))
                .initialProperties(SharedProperties::copperMetal)
                .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(axeOrPickaxe())
                .blockstate(BlockStateGen.encasedPipe())
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(connectedTexturesSprite)))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, connectedTexturesSprite,
                        (s, f) -> !s.getValue(EncasedPipeBlock.FACING_TO_PROPERTY_MAP.get(f)))))
                .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::new))
                .loot((p, b) -> p.dropOther(b, AllBlocks.FLUID_PIPE.get()))
                .transform(EncasingRegistry.addVariantTo(AllBlocks.FLUID_PIPE))
                .register();
    }


    /**
     * Create a custom encased pipe
     * <br>
     * This method just register the block you need to make the blockstate & models (item & block)
     * @param registrate The registrate of your mod (e.g. Create.REGISTRATE)
     * @param name The name of the casing in your shaft (e.g. andesite)
     * @param connectedTexturesSprite The sprite with the connected texture of your casing (e.g. AllSpriteShifts.ANDESITE_CASING)
     * @return The Block Entry of the Encased Pipe
     */
    public static BlockEntry<ApiGearboxBlock> createGearbox(CreateRegistrate registrate,String name,CTSpriteShiftEntry connectedTexturesSprite){
        Deferred<ItemEntry<ApiVerticalGearboxItem>> itemEntryDeferred = new Deferred<>();
        BlockEntry<ApiGearboxBlock> gearbox = registrate.block(name+"_gearbox",(p)-> new ApiGearboxBlock(p,itemEntryDeferred))
                .initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .properties(p -> p.color(MaterialColor.PODZOL))
                .transform(BlockStressDefaults.setNoImpact())
                .transform(axeOrPickaxe())
                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(connectedTexturesSprite)))
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, connectedTexturesSprite,
                        (s, f) -> f.getAxis() == s.getValue(GearboxBlock.AXIS))))
                .blockstate((c, p) -> axisBlock(c, p, $ -> AssetLookup.partialBaseModel(c, p), true))
                .item()
                .transform(customItemModel())
                .register();
        itemEntryDeferred.set(registrate.item("vertical_"+name+"_gearbox", (p)->new ApiVerticalGearboxItem(p, gearbox.get()))
                .model(AssetLookup.customBlockItemModel(name + "_gearbox", "item_vertical"))
                .register());
        return gearbox;
    }


}
