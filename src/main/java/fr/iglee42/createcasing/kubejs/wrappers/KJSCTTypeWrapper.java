package fr.iglee42.createcasing.kubejs.wrappers;

import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTType;
import com.simibubi.create.foundation.block.connected.CTTypeRegistry;
import net.minecraft.resources.ResourceLocation;

public interface KJSCTTypeWrapper {
    CTType HORIZONTAL = AllCTTypes.HORIZONTAL;
    CTType VERTICAL = AllCTTypes.VERTICAL;
    CTType OMNIDIRECTIONAL = AllCTTypes.OMNIDIRECTIONAL;

    static CTType getCtType(ResourceLocation id){
        return CTTypeRegistry.get(id);
    }
}
