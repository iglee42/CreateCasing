package fr.iglee42.createcasing.compat.kubejs.press;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import dev.latvian.mods.kubejs.typings.Info;
import fr.iglee42.createcasing.api.CreateCasingApi;
import fr.iglee42.createcasing.compat.kubejs.CreateCasingBuilderBaseJS;
import fr.iglee42.createcasing.compat.kubejs.KubeJSCompatPlugin;
import net.minecraft.resources.ResourceLocation;

public class PressBuilderJs extends CreateCasingBuilderBaseJS {
    private String casingTexture;
    private String topTexture;
    private String sideTexture;
    private String gearboxTexture;
    private String bottomTexture;


    public PressBuilderJs(String name) {
        super(name,name +"_press","block");
        this.sideTexture = KubeJS.MOD_ID+ ":block/"+ name+"_press_side";
        this.topTexture = KubeJS.MOD_ID+ ":block/"+ name+"_press_top";
        this.bottomTexture = KubeJS.MOD_ID+ ":block/"+ name+"_press_bottom";
        this.gearboxTexture = KubeJS.MOD_ID+ ":block/"+ name+"_gearbox";
        this.casingTexture = KubeJS.MOD_ID+ ":block/"+ name+"_casing";
    }

    public PressBuilderJs casingTexture(String texture){
        this.casingTexture = texture;
        return this;
    }

    public PressBuilderJs topTexture(String texture){
        this.topTexture = texture;
        return this;
    }

    public PressBuilderJs sideTexture(String texture){
        this.sideTexture = texture;
        return this;
    }

    public PressBuilderJs bottomTexture(String texture){
        this.bottomTexture = texture;
        return this;
    }

    public PressBuilderJs gearboxTexture(String texture){
        this.gearboxTexture = texture;
        return this;
    }


    public PressBuilderJs displayName(String displayName){
        this.displayName = displayName;
        return this;
    }

    public PressBuilderJs translationKey(String translationKey){
        this.translationKey = translationKey;
        return this;
    }

    @Override
    public void generateAssetJsons(AssetJsonGenerator generator) {
        generator.blockState(new ResourceLocation("createcasing-kubejs",fullName),(gen)->{
            gen.simpleVariant("","createcasing-kubejs:block/"+fullName);
        });
        generator.blockModel(new ResourceLocation("createcasing-kubejs",fullName),mg->{
            mg.parent("createcasing:block/api/press/block");
            mg.texture("4",sideTexture);
            mg.texture("mechanical_press_top",topTexture);
            mg.texture("mechanical_press_bottom",bottomTexture);
            mg.texture("gearbox",gearboxTexture);
            mg.texture("gearbox_top",casingTexture);
        });
        generator.itemModel(new ResourceLocation("createcasing-kubejs", fullName),mg-> {
            mg.parent("createcasing:block/api/press/item");
            mg.texture("8",sideTexture);
            mg.texture("mechanical_press_top",topTexture);
            mg.texture("mechanical_press_bottom",bottomTexture);
            mg.texture("gearbox",gearboxTexture);
            mg.texture("gearbox_top",casingTexture);
        });
    }

    @Info("Create the press in the code and return it")
    public void build() {
        CreateCasingApi.createPress(KubeJSCompatPlugin.REGISTRATE,name);
    }
}
