package fr.iglee42.createcasing.ponder;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.foundation.element.InputWindowElement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class CustomPonderScenes {

    public static void creativeCogwheel(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("creative_cogwheel", "Generating Rotational Force using Creative Cogwheels");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().layer(0), Direction.UP);

        BlockPos motor = util.grid().at(3, 1, 2);

        for (int i = 0; i < 4; i++) {
            scene.idle(5);
            scene.world().showSection(util.select().position(i, 1, 2), Direction.DOWN);
        }
        scene.idle(10);
        scene.effects().rotationSpeedIndicator(motor);
        scene.overlay().showText(50)
                .text("Creative cogwheel are a compact and configurable source of Rotational Force")
                .placeNearTarget()
                .pointAt(util.vector().topOf(motor));
        scene.idle(70);

        Vec3 blockSurface = util.vector().blockSurface(motor, Direction.NORTH);
        scene.overlay().showFilterSlotInput(blockSurface, Direction.NORTH, 80);
        scene.overlay().showControls(blockSurface, Pointing.DOWN,60).rightClick();
        scene.idle(20);

        scene.overlay().showText(60)
                .text("The generated speed can be configured on its input panels")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(blockSurface);
        scene.idle(10);
        scene.idle(50);
        scene.world().modifyKineticSpeed(util.select().fromTo(0, 1, 2, 3, 0, 2), f -> 4 * f);
        scene.idle(10);

        scene.effects().rotationSpeedIndicator(motor);
    }
}
