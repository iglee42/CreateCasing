package fr.iglee42.createcasing.utils;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.encasing.EncasableBlock;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import fr.iglee42.createcasing.config.CCStress;
import fr.iglee42.createcasing.registries.EncasedBlockStateGens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Objects;
import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.CreateRegistrate.casingConnectivity;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static fr.iglee42.createcasing.registries.EncasedBlockStateGens.*;

public class CasingBuilderTransformers {

    public static <B extends EncasedShaftBlock, P,E extends Block & EncasableBlock>  NonNullUnaryOperator<BlockBuilder<B, P>> encasedShaft(BlockEntry<E> shaft, String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        String sId = shaft.getId().getPath().replace("_shaft","");
        if (shaft.equals(AllBlocks.SHAFT)) sId = "normal";
        String finalSId = sId;
        return builder -> {
           BlockBuilder<B,P> b =  encasedBase(builder, shaft::get)
                    .blockstate(EncasedBlockStateGens.encasedShaft( finalSId, casing))
                    .item()
                    .model((ctx, prov) -> prov.getBuilder(ctx.getName()).parent(Objects.requireNonNull(encasedShaftModel(prov, finalSId, casing, true))))
                    .build();
           if (casingShift.get() != null){
              b=b.onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
                       .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                               (s, f) -> f.getAxis() != s.getValue(EncasedShaftBlock.AXIS))));
           }
            return b;
        };
    }

    public static <B extends CasingBlock> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> casing(
            Supplier<CTSpriteShiftEntry> ct) {
        return b -> b.initialProperties(SharedProperties::stone)
                .properties(p -> p.sound(SoundType.WOOD))
                .transform(axeOrPickaxe())
                //.blockstate((c, p) -> p.simpleBlock(c.get()))
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(ct.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, ct.get())))
                .tag(AllTags.AllBlockTags.CASING.tag)
                .item()
                .tag(AllTags.AllItemTags.CASING.tag)
                .build();
    }
    public static <B extends EncasedCogwheelBlock, P,E extends Block & EncasableBlock> NonNullUnaryOperator<BlockBuilder<B, P>> encasedCogwheel(
            BlockEntry<E> cogwheel,String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        String sId = cogwheel.getId().getPath().replace("_cogwheel","");
        if (cogwheel.equals(AllBlocks.COGWHEEL)) sId = "normal";
        String finalSId = sId;
        return b -> encasedCogwheelBase(b,finalSId, casing, casingShift, cogwheel::get, false);
    }

    public static <B extends EncasedCogwheelBlock, P,E extends Block & EncasableBlock> NonNullUnaryOperator<BlockBuilder<B, P>> encasedLargeCogwheel(
            BlockEntry<E> cogwheel,String casing, Supplier<CTSpriteShiftEntry> casingShift) {
        String sId = cogwheel.getId().getPath().replace("_large_cogwheel","");
        if (cogwheel.equals(AllBlocks.LARGE_COGWHEEL)) sId = "normal";
        String finalSId = sId;
        return b -> {
            BlockBuilder<B,P> builder = encasedCogwheelBase(b, finalSId, casing, casingShift, cogwheel::get, true);
            if (casingShift.get() != null) builder = builder.onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(casingShift.get())));
            return builder;
        };
    }

    private static <B extends EncasedCogwheelBlock, P> BlockBuilder<B, P> encasedCogwheelBase(BlockBuilder<B, P> b,
                                                                                              String cogwheel, String casing, Supplier<CTSpriteShiftEntry> casingShift, Supplier<ItemLike> drop, boolean large) {
        BlockBuilder<B,P> builder = encasedBase(b, drop).addLayer(() -> RenderType::cutoutMipped)

               .blockstate(large ? EncasedBlockStateGens.encasedLargeCogwheel(cogwheel,casing) : EncasedBlockStateGens.encasedCogwheel(cogwheel,casing))
                .item()
                .model((ctx, prov) -> prov.getBuilder(ctx.getName()).parent(Objects.requireNonNull(encasedCogwheelModel(prov, casing,cogwheel,null, large))).texture(large ? "4": "1_2",large ? getLargeCogwheelTexture(cogwheel) : getCogwheelTexture(cogwheel)))
                .build();
        if (casingShift != null) builder = builder
                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                (s, f) -> f.getAxis() == s.getValue(EncasedCogwheelBlock.AXIS)
                        && !s.getValue(f.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT
                        : EncasedCogwheelBlock.BOTTOM_SHAFT))));
        return builder;
    }

    private static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> encasedBase(BlockBuilder<B, P> b,
                                                                                           Supplier<ItemLike> drop) {
        return b.initialProperties(SharedProperties::stone)
                .properties(BlockBehaviour.Properties::noOcclusion)
                .transform(CCStress.setNoImpact())
                .loot((p, lb) -> p.dropOther(lb, drop.get()));
    }

}
