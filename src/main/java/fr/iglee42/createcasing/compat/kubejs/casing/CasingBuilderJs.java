package fr.iglee42.createcasing.compat.kubejs.casing;

import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.generator.AssetJsonGenerator;
import fr.iglee42.createcasing.api.CreateCasingApi;
import fr.iglee42.createcasing.compat.kubejs.CreateCasingBuilderBaseJS;
import fr.iglee42.createcasing.compat.kubejs.KubeJSCompatPlugin;
import net.minecraft.resources.ResourceLocation;

public class CasingBuilderJs extends CreateCasingBuilderBaseJS {

    private CTSpriteShiftEntry connectedTexture;

    private String texture;



    public CasingBuilderJs(String name) {
        super(name,name +"_casing","block");
        this.texture = KubeJS.MOD_ID+ ":block/"+ fullName;
    }

    public CasingBuilderJs texture(String texture){
        this.texture = texture;
        return this;
    }


    public CasingBuilderJs connectedTexture(CTSpriteShiftEntry sprite){
        connectedTexture = sprite;
        return this;
    }

    public CasingBuilderJs displayName(String displayName){
        this.displayName = displayName;
        return this;
    }

    public CasingBuilderJs translationKey(String translationKey){
        this.translationKey = translationKey;
        return this;
    }

    @Override
    public void generateAssetJsons(AssetJsonGenerator generator) {
        generator.blockState(new ResourceLocation("createcasing-kubejs",fullName),(gen)->{
            gen.variant("","createcasing-kubejs:block/"+fullName);
        });
        generator.blockModel(new ResourceLocation("createcasing-kubejs",fullName),mg->{
            mg.parent("minecraft:block/cube_all");
            mg.texture("all",texture);
        });
        generator.itemModel(new ResourceLocation("createcasing-kubejs", fullName),mg-> mg.parent("createcasing-kubejs:block/"+fullName));
    }

    public BlockEntry<CasingBlock> build() {
       return CreateCasingApi.createCasing(KubeJSCompatPlugin.REGISTRATE,name,connectedTexture);
    }
}
