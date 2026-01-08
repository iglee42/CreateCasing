package fr.iglee42.createcasing.registries.generators;

import com.simibubi.create.content.kinetics.saw.SawBlock;
import com.simibubi.create.content.kinetics.saw.SawGenerator;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import fr.iglee42.createcasing.registries.EncasedBlockStateGens;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;

import java.util.Objects;

public class CustomSawGenerator extends SawGenerator {

    private final String name;

    public CustomSawGenerator(String name) {
        this.name = name;
    }

    @Override
    public <T extends Block> ModelFile getModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov, BlockState state) {
        String path = "mechanical_saw/"+name+"/";
        String orientation = state.getValue(SawBlock.FACING)
                .getAxis()
                .isVertical() ? "vertical" : "horizontal";

        ModelBuilder<?> builder = Objects.requireNonNull(EncasedBlockStateGens.createModelInBlock(prov, path + orientation))
                .parent(new ModelFile.UncheckedModelFile("create:block/mechanical_saw/"+orientation))
                .texture("gearbox", EncasedBlockStateGens.getGearboxTexture(name))
                .texture("gearbox_top", EncasedBlockStateGens.getGearboxTopTexture(name))
                .texture("particle" , Objects.equals(orientation, "vertical") ? EncasedBlockStateGens.getSawTexture(name,"_top") : EncasedBlockStateGens.getGearboxTopTexture(name))
                .texture("andesite_casing_short",EncasedBlockStateGens.getShortCasingTexture(name));

        if (Objects.equals(orientation, "vertical")) builder = builder.texture("mechanical_saw_top",EncasedBlockStateGens.getSawTexture(name,"_top"));
        else builder = builder.texture("slit",EncasedBlockStateGens.getSawTexture(name,"_top_no_slot")).texture("encased_belt",EncasedBlockStateGens.getChainDriveSideTexture(name));


        return builder;
    }
}
