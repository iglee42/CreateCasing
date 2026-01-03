package fr.iglee42.createcasing.mixins.create;

import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = DeployerBlockEntity.class,remap = false)
public interface DeployerBlockEntityAccessor {

    @Invoker("initHandler")
    void invokeInitHandler();

    @Accessor("invHandler")
    IItemHandlerModifiable getInvHandler();
}
