package fr.iglee42.createcasing.client;

import net.minecraft.client.renderer.RenderType;

import java.util.function.Supplier;

public final class ClientRenderTypes {

    private ClientRenderTypes() {
    }

    public static Supplier<RenderType> cutoutMipped() {
        return RenderType::cutoutMipped;
    }
}
