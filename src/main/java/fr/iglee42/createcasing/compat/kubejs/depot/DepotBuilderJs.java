package fr.iglee42.createcasing.compat.kubejs.depot;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import fr.iglee42.createcasing.api.CreateCasingApi;
import fr.iglee42.createcasing.compat.kubejs.CreateCasingBuilderBaseJS;
import fr.iglee42.createcasing.compat.kubejs.KubeJSCompatPlugin;
import net.minecraft.resources.ResourceLocation;

public class DepotBuilderJs extends CreateCasingBuilderBaseJS {
    private String casingTexture;
    private String topTexture;
    private String sideTexture;



    public DepotBuilderJs(String name) {
        super(name,name +"_depot","block");
        this.sideTexture = KubeJS.MOD_ID+ ":block/"+ name+"_depot_side";
        this.topTexture = KubeJS.MOD_ID+ ":block/"+ name+"_depot_top";
        this.casingTexture = KubeJS.MOD_ID+ ":block/"+ name+"_casing";
    }

    public DepotBuilderJs casingTexture(String texture){
        this.casingTexture = texture;
        return this;
    }

    public DepotBuilderJs topTexture(String texture){
        this.topTexture = texture;
        return this;
    }

    public DepotBuilderJs sideTexture(String texture){
        this.sideTexture = texture;
        return this;
    }


    public DepotBuilderJs displayName(String displayName){
        this.displayName = displayName;
        return this;
    }

    public DepotBuilderJs translationKey(String translationKey){
        this.translationKey = translationKey;
        return this;
    }

    @Override
    public void generateAssetJsons(AssetJsonGenerator generator) {
        generator.blockState(new ResourceLocation("createcasing-kubejs",fullName),(gen)->{
            gen.variant("","createcasing-kubejs:block/"+fullName);
        });
        generator.blockModel(new ResourceLocation("createcasing-kubejs",fullName),mg->{
            mg.parent("createcasing:block/api/base_depot");
            mg.texture("side",sideTexture);
            mg.texture("top",topTexture);
            mg.texture("casing",casingTexture);
        });
        generator.itemModel(new ResourceLocation("createcasing-kubejs", fullName),mg-> mg.parent("createcasing-kubejs:block/"+fullName));
    }

    public void build() {
        CreateCasingApi.createDepot(KubeJSCompatPlugin.REGISTRATE,name);
    }
}
