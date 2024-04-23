package fr.iglee42.createcasing.compat.kubejs.encased;

import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.BuilderTransformers;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.client.LangEventJS;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.util.UtilsJS;
import fr.iglee42.createcasing.CreateCasing;
import fr.iglee42.createcasing.api.CreateCasingApi;
import fr.iglee42.createcasing.blocks.customs.EncasedCustomShaftBlock;
import fr.iglee42.createcasing.compat.kubejs.CreateCasingBuilderBaseJS;
import fr.iglee42.createcasing.compat.kubejs.CreateCasingUtilsJS;
import fr.iglee42.createcasing.compat.kubejs.EncasedBlockJSEnum;
import fr.iglee42.createcasing.compat.kubejs.KubeJSCompatPlugin;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class EncasedBuilderJs extends CreateCasingBuilderBaseJS {


    private final EncasedBlockJSEnum encasedType;

    private CTSpriteShiftEntry connectedTexture;

    private Supplier<Block> casing;

    private String cogwheelSideTexture;

    private String casingTexture;

    private String openingTexture;



    public EncasedBuilderJs(String name, EncasedBlockJSEnum encasedType) {
        super(name,name + "_encased_"+ encasedType.name().toLowerCase(),"block");
        this.encasedType = encasedType;
        casingTexture = KubeJS.MOD_ID+":block/"+name + "_casing";
        openingTexture = encasedType == EncasedBlockJSEnum.PIPE ? KubeJS.MOD_ID+":block/encased_"+name+"_pipe" : name+"_gearbox";
        cogwheelSideTexture = KubeJS.MOD_ID+":block/"+name + "_encased_cogwheel_side";
    }

    public EncasedBuilderJs cogwheelSideTexture(String texture){
        cogwheelSideTexture = texture;
        return this;
    }

    public EncasedBuilderJs casingTexture(String texture){
        casingTexture = texture;
        return this;
    }
    public EncasedBuilderJs openingTexture(String texture){
        openingTexture = texture;
        return this;
    }

    public EncasedBuilderJs connectedTexture(CTSpriteShiftEntry sprite){
        connectedTexture = sprite;
        return this;
    }

    public EncasedBuilderJs casing(Supplier<Block> casingSupplier){
        casing = casingSupplier;
        return this;
    }


    public EncasedBuilderJs displayName(String displayName){
        this.displayName = displayName;
        return this;
    }

    public EncasedBuilderJs translationKey(String translationKey){
        this.translationKey = translationKey;
        return this;
    }

    @Override
    public void generateAssetJsons(AssetJsonGenerator generator) {

        switch (encasedType) {
            case SHAFT ->{
                generator.blockState(new ResourceLocation("createcasing-kubejs",name+"_encased_shaft"),(gen)->{
                    gen.variant("axis=x",v-> v.model("createcasing-kubejs:block/"+name+"_encased_shaft").y(90).x(90).uvlock());
                    gen.variant("axis=y",v-> v.model("createcasing-kubejs:block/"+name+"_encased_shaft").uvlock());
                    gen.variant("axis=z",v-> v.model("createcasing-kubejs:block/"+name+"_encased_shaft").y(180).x(90).uvlock());
                });

                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_shaft"),mg->{
                    mg.parent("create:block/encased_shaft/block");
                    mg.texture("casing",casingTexture);
                    mg.texture("opening",openingTexture);
                });

                generator.itemModel(new ResourceLocation("createcasing-kubejs",name+"_encased_shaft"),mg->{
                    mg.parent("create:block/encased_shaft/item");
                    mg.texture("casing",casingTexture);
                    mg.texture("opening",openingTexture);
                });
            }
            case COGWHEEL -> {
                generator.blockState(new ResourceLocation("createcasing-kubejs",name+"_encased_cogwheel"),(gen)->{
                    gen.variant("axis=x,bottom_shaft=false,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/base").y(90).x(90).uvlock());
                    gen.variant("axis=y,bottom_shaft=false,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/base").uvlock());
                    gen.variant("axis=z,bottom_shaft=false,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/base").y(180).x(90).uvlock());
                    gen.variant("axis=x,bottom_shaft=true,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/bottom").y(90).x(90).uvlock());
                    gen.variant("axis=y,bottom_shaft=true,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/bottom").uvlock());
                    gen.variant("axis=z,bottom_shaft=true,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/bottom").y(180).x(90).uvlock());
                    gen.variant("axis=x,bottom_shaft=false,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/top").y(90).x(90).uvlock());
                    gen.variant("axis=y,bottom_shaft=false,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/top").uvlock());
                    gen.variant("axis=z,bottom_shaft=false,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/top").y(180).x(90).uvlock());
                    gen.variant("axis=x,bottom_shaft=true,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/top_bottom").y(90).x(90).uvlock());
                    gen.variant("axis=y,bottom_shaft=true,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/top_bottom").uvlock());
                    gen.variant("axis=z,bottom_shaft=true,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_cogwheel/top_bottom").y(180).x(90).uvlock());
                });

                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_cogwheel/base"),mg->{
                    mg.parent("create:block/encased_cogwheel/block");
                    mg.texture("casing",casingTexture);
                    mg.texture("1",casingTexture);
                    mg.texture("side",cogwheelSideTexture);
                });

                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_cogwheel/bottom"),mg->{
                    mg.parent("create:block/encased_cogwheel/block_bottom");
                    mg.texture("casing",casingTexture);
                    mg.texture("1",casingTexture);
                    mg.texture("4",openingTexture);
                    mg.texture("side",cogwheelSideTexture);
                });

                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_cogwheel/top"),mg->{
                    mg.parent("create:block/encased_cogwheel/block_top");
                    mg.texture("casing",casingTexture);
                    mg.texture("1",casingTexture);
                    mg.texture("4",openingTexture);
                    mg.texture("side",cogwheelSideTexture);
                });

                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_cogwheel/top_bottom"),mg->{
                    mg.parent("create:block/encased_cogwheel/block_top_bottom");
                    mg.texture("casing",casingTexture);
                    mg.texture("1",casingTexture);
                    mg.texture("4",openingTexture);
                    mg.texture("side",cogwheelSideTexture);
                });

                generator.itemModel(new ResourceLocation("createcasing-kubejs",name+"_encased_cogwheel"),mg->{
                    mg.parent("create:block/encased_cogwheel/item");
                    mg.texture("casing",casingTexture);
                    mg.texture("1",casingTexture);
                    mg.texture("side",cogwheelSideTexture);
                });

            }
            case LARGE_COGWHEEL -> {
                generator.blockState(new ResourceLocation("createcasing-kubejs",name+"_encased_large_cogwheel"),(gen)->{
                    gen.variant("axis=x,bottom_shaft=false,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/base").y(90).x(90).uvlock());
                    gen.variant("axis=y,bottom_shaft=false,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/base").uvlock());
                    gen.variant("axis=z,bottom_shaft=false,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/base").y(180).x(90).uvlock());
                    gen.variant("axis=x,bottom_shaft=true,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/bottom").y(90).x(90).uvlock());
                    gen.variant("axis=y,bottom_shaft=true,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/bottom").uvlock());
                    gen.variant("axis=z,bottom_shaft=true,top_shaft=false",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/bottom").y(180).x(90).uvlock());
                    gen.variant("axis=x,bottom_shaft=false,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/top").y(90).x(90).uvlock());
                    gen.variant("axis=y,bottom_shaft=false,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/top").uvlock());
                    gen.variant("axis=z,bottom_shaft=false,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/top").y(180).x(90).uvlock());
                    gen.variant("axis=x,bottom_shaft=true,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/top_bottom").y(90).x(90).uvlock());
                    gen.variant("axis=y,bottom_shaft=true,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/top_bottom").uvlock());
                    gen.variant("axis=z,bottom_shaft=true,top_shaft=true",v-> v.model("createcasing-kubejs:block/"+name+"_encased_large_cogwheel/top_bottom").y(180).x(90).uvlock());
                });

                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_large_cogwheel/base"),mg->{
                    mg.parent("create:block/encased_large_cogwheel/block");
                    mg.texture("casing",casingTexture);
                    mg.texture("1",casingTexture);
                    mg.texture("side",cogwheelSideTexture+"_connected");
                });

                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_large_cogwheel/bottom"),mg->{
                    mg.parent("create:block/encased_large_cogwheel/block_bottom");
                    mg.texture("casing",casingTexture);
                    mg.texture("1",casingTexture);
                    mg.texture("4",openingTexture);
                    mg.texture("side",cogwheelSideTexture+"_connected");
                });

                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_large_cogwheel/top"),mg->{
                    mg.parent("create:block/encased_large_cogwheel/block_top");
                    mg.texture("casing",casingTexture);
                    mg.texture("1",casingTexture);
                    mg.texture("4",openingTexture);
                    mg.texture("side",cogwheelSideTexture+"_connected");
                });

                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_large_cogwheel/top_bottom"),mg->{
                    mg.parent("create:block/encased_large_cogwheel/block_top_bottom");
                    mg.texture("casing",casingTexture);
                    mg.texture("1",casingTexture);
                    mg.texture("4",openingTexture);
                    mg.texture("side",cogwheelSideTexture+"_connected");
                });
                generator.itemModel(new ResourceLocation("createcasing-kubejs",name+"_encased_large_cogwheel"),mg->{
                    mg.parent("create:block/encased_large_cogwheel/item");
                    mg.texture("casing",casingTexture);
                    mg.texture("1",casingTexture);
                    mg.texture("side",cogwheelSideTexture);
                });
            }
            case PIPE -> {
                generator.multipartState(new ResourceLocation("createcasing-kubejs",name + "_encased_fluid_pipe"),sg->{
                    sg.part("down:false",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_flat").x(-90));
                    sg.part("up:false",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_flat").x(90));
                    sg.part("north:false",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_flat").y(180));
                    sg.part("south:false",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_flat"));
                    sg.part("west:false",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_flat").y(90));
                    sg.part("east:false",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_flat").y(270));
                    sg.part("down:true",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_open").x(-90));
                    sg.part("up:true",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_open").x(90));
                    sg.part("north:true",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_open").y(180));
                    sg.part("south:true",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_open"));
                    sg.part("west:true",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_open").y(90));
                    sg.part("east:true",mg->mg.model("createcasing-kubejs:block/"+name+"_encased_fluid_pipe/block_open").y(270));
                });
                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_fluid_pipe/block_flat"),mg->{
                    mg.parent("create:block/encased_fluid_pipe/block_flat");
                    mg.texture("0",casingTexture);
                    mg.texture("particle",casingTexture);
                });
                generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_encased_fluid_pipe/block_open"),mg->{
                    mg.parent("create:block/encased_fluid_pipe/block_open");
                    mg.texture("0",openingTexture);
                    mg.texture("particle",openingTexture);
                });
            }

            case CUSTOM_SHAFT -> {
                CreateCasingApi.forCustomShafts(shaft ->{
                    generator.blockState(new ResourceLocation("createcasing-kubejs",name+"_encased_"+shaft.getId().getPath()),(gen)->{
                        gen.variant("axis=x",v-> v.model("createcasing-kubejs:block/"+name+"_encased_shaft").y(90).x(90).uvlock());
                        gen.variant("axis=y",v-> v.model("createcasing-kubejs:block/"+name+"_encased_shaft").uvlock());
                        gen.variant("axis=z",v-> v.model("createcasing-kubejs:block/"+name+"_encased_shaft").y(180).x(90).uvlock());
                    });
                    generator.itemModel(new ResourceLocation("createcasing-kubejs",name+"_encased_"+shaft.getId().getPath().toLowerCase()),mg->{
                        mg.parent("createcasing:item/api/base_encased_"+shaft.getId().getPath());
                        mg.texture("casing",casingTexture);
                        mg.texture("opening",openingTexture);
                    });
                });
            }
        }
    }

    @Override
    public void generateLang(LangEventJS lang) {
        if (encasedType == EncasedBlockJSEnum.CUSTOM_SHAFT) {
            CreateCasingApi.forCustomShafts(shaft->{
                lang.add(KubeJSCompatPlugin.REGISTRATE.getModid(), "block."+KubeJSCompatPlugin.REGISTRATE.getModid() + "." + name+ "_encased_" + shaft.getId().getPath(),UtilsJS.snakeCaseToTitleCase(name+ "_encased_" + shaft.getId().getPath()));
            });
        } else {
            super.generateLang(lang);
        }
    }

    @Info("Create the encased block in the code")
    public void build() {
        if (casing == null) throw new IllegalArgumentException("Missing .casing() argument !");
        switch (encasedType){
            case SHAFT -> CreateCasingApi.createEncasedShaft(KubeJSCompatPlugin.REGISTRATE,name,casing,connectedTexture);
            case COGWHEEL -> {
                CTSpriteShiftEntry verticalSide = CreateCasingUtilsJS.vertical(cogwheelSideTexture);
                CTSpriteShiftEntry horizontalSide = CreateCasingUtilsJS.horizontal(cogwheelSideTexture);

                CreateCasingApi.createEncasedCogwheel(KubeJSCompatPlugin.REGISTRATE, name, casing, connectedTexture, verticalSide, horizontalSide);

            }
            case LARGE_COGWHEEL -> CreateCasingApi.createEncasedLargeCogwheel(KubeJSCompatPlugin.REGISTRATE,name,casing,connectedTexture);
            case PIPE -> CreateCasingApi.createEncasedPipe(KubeJSCompatPlugin.REGISTRATE,name,casing,connectedTexture);
            case CUSTOM_SHAFT -> CreateCasingApi.forCustomShafts(shaft -> {
                KubeJSCompatPlugin.REGISTRATE.block(name + "_encased_" + shaft.getId().getPath(), p -> new EncasedCustomShaftBlock(p, casing, shaft))
                        .properties(p -> p.mapColor(MapColor.PODZOL))
                        .transform(BuilderTransformers.encasedShaft(name, () -> connectedTexture))
                        .transform(EncasingRegistry.addVariantTo(shaft))
                        .transform(axeOrPickaxe())
                        .loot((l,s)->l.dropOther(s,s.getShaft().get().asItem()))
                        .onRegisterAfter(Registries.ITEM, CreateCasing::hideItem)
                        .register();
            });
        }
    }
}
