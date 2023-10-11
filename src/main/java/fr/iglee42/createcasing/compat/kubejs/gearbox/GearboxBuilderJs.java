package fr.iglee42.createcasing.compat.kubejs.gearbox;

import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.client.LangEventJS;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.util.UtilsJS;
import fr.iglee42.createcasing.api.CreateCasingApi;
import fr.iglee42.createcasing.compat.kubejs.CreateCasingBuilderBaseJS;
import fr.iglee42.createcasing.compat.kubejs.KubeJSCompatPlugin;
import net.minecraft.resources.ResourceLocation;

public class GearboxBuilderJs extends CreateCasingBuilderBaseJS {

    private boolean verticalItem = true;

    private CTSpriteShiftEntry connectedTexture;

    private String casingTexture;
    private String gearboxTexture;



    public GearboxBuilderJs(String name) {
        super(name,name +"_gearbox","block");
        this.casingTexture = KubeJS.MOD_ID + ":block/" + fullName;
        this.gearboxTexture = KubeJS.MOD_ID + ":block/"+name+"_gearbox";
    }


    public GearboxBuilderJs casingTexture(String casingTexture){
        this.casingTexture = casingTexture;
        return this;
    }

    public GearboxBuilderJs gearboxTexture(String gearboxTexture){
        this.gearboxTexture = gearboxTexture;
        return this;
    }

    public GearboxBuilderJs noVerticalItem(){
        verticalItem = false;
        return this;
    }

    public GearboxBuilderJs connectedTexture(CTSpriteShiftEntry sprite){
        connectedTexture = sprite;
        return this;
    }

    public GearboxBuilderJs displayName(String displayName){
        this.displayName = displayName;
        return this;
    }

    public GearboxBuilderJs translationKey(String translationKey){
        this.translationKey = translationKey;
        return this;
    }

    @Override
    public void generateAssetJsons(AssetJsonGenerator generator) {
        generator.blockState(new ResourceLocation("createcasing-kubejs",name+"_gearbox"),(gen)->{
            gen.variant("axis=x",v-> v.model("createcasing-kubejs:block/"+name+"_gearbox").y(90).x(90).uvlock());
            gen.variant("axis=y",v-> v.model("createcasing-kubejs:block/"+name+"_gearbox").uvlock());
            gen.variant("axis=z",v-> v.model("createcasing-kubejs:block/"+name+"_gearbox").y(180).x(90).uvlock());
        });
        generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_gearbox"),mg->{
            mg.parent("createcasing:block/api/base_gearbox");
            mg.texture("0", casingTexture);
            mg.texture("1",gearboxTexture);
        });
        generator.blockModel(new ResourceLocation("createcasing-kubejs",name+"_gearbox_item"),mg->{
            mg.parent("createcasing:block/api/base_gearbox_item");
            mg.texture("0", casingTexture);
            mg.texture("1",gearboxTexture);
        });
        generator.itemModel(new ResourceLocation("createcasing-kubejs", name + "_gearbox"),mg-> mg.parent("createcasing-kubejs:block/"+name + "_gearbox_item"));
        if (verticalItem) {
            generator.blockModel(new ResourceLocation("createcasing-kubejs", name + "_gearbox_item_vertical"), mg -> {
                mg.parent("createcasing:block/api/base_gearbox_item_vertical");
                mg.texture("gearbox_top", casingTexture);
                mg.texture("gearbox", gearboxTexture);
            });
            generator.itemModel(new ResourceLocation("createcasing-kubejs", "vertical_"+name + "_gearbox"),mg-> mg.parent("createcasing-kubejs:block/"+name + "_gearbox_item_vertical"));
        }
    }

    @Override
    public void generateLang(LangEventJS lang) {
        super.generateLang(lang);
        lang.add("createcasing-kubejs","item.createcasing-kubejs.vertical_" + fullName, UtilsJS.snakeCaseToTitleCase("item.createcasing-kubejs.vertical_" + fullName));
    }

    @Info("Create the gearbox in the code")
    public void build() {
        CreateCasingApi.createGearbox(KubeJSCompatPlugin.REGISTRATE,name,connectedTexture,verticalItem);
    }
}
