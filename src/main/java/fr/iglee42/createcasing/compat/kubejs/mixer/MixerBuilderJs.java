package fr.iglee42.createcasing.compat.kubejs.mixer;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import dev.latvian.mods.kubejs.typings.Info;
import fr.iglee42.createcasing.api.CreateCasingApi;
import fr.iglee42.createcasing.compat.kubejs.CreateCasingBuilderBaseJS;
import fr.iglee42.createcasing.compat.kubejs.KubeJSCompatPlugin;
import net.minecraft.resources.ResourceLocation;

public class MixerBuilderJs extends CreateCasingBuilderBaseJS {
    private String casingTexture;
    private String topTexture;
    private String sideTexture;



    public MixerBuilderJs(String name) {
        super(name,name +"_mixer","block");
        this.sideTexture = KubeJS.MOD_ID+ ":block/"+ name+"_mixer_side";
        this.topTexture = KubeJS.MOD_ID+ ":block/"+ name+"_press_top";
        this.casingTexture = KubeJS.MOD_ID+ ":block/"+ name+"_casing";
    }

    public MixerBuilderJs casingTexture(String texture){
        this.casingTexture = texture;
        return this;
    }

    public MixerBuilderJs topTexture(String texture){
        this.topTexture = texture;
        return this;
    }

    public MixerBuilderJs sideTexture(String texture){
        this.sideTexture = texture;
        return this;
    }


    public MixerBuilderJs displayName(String displayName){
        this.displayName = displayName;
        return this;
    }

    public MixerBuilderJs translationKey(String translationKey){
        this.translationKey = translationKey;
        return this;
    }

    @Override
    public void generateAssetJsons(AssetJsonGenerator generator) {
        generator.blockState(new ResourceLocation("createcasing-kubejs",fullName),(gen)->{
            gen.simpleVariant("","createcasing-kubejs:block/"+fullName);
        });
        generator.blockModel(new ResourceLocation("createcasing-kubejs",fullName),mg->{
            mg.parent("createcasing:block/api/mixer/block");
            mg.texture("4",sideTexture);
            mg.texture("11",topTexture);
            mg.texture("2",casingTexture);
        });
        generator.itemModel(new ResourceLocation("createcasing-kubejs", fullName),mg-> {
            mg.parent("createcasing:block/api/mixer/item");
            mg.texture("4",sideTexture);
            mg.texture("11",topTexture);
            mg.texture("2",casingTexture);
        });
    }

    @Info("Create the mixer in the code and return it")
    public void build() {
        CreateCasingApi.createMixer(KubeJSCompatPlugin.REGISTRATE,name);
    }
}
