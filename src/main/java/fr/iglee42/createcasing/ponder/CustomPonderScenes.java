package fr.iglee42.createcasing.ponder;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class CustomPonderScenes {

    public static void creativeCogwheel(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("creative_cogwheel", "Generating Rotational Force using Creative Cogwheels");
        scene.configureBasePlate(0, 0, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);

        BlockPos motor = util.grid.at(3, 1, 2);

        for (int i = 0; i < 4; i++) {
            scene.idle(5);
            scene.world.showSection(util.select.position(i, 1, 2), Direction.DOWN);
        }
        scene.idle(10);
        scene.effects.rotationSpeedIndicator(motor);
        scene.overlay.showText(50)
                .text("Creative cogwheel are a compact and configurable source of Rotational Force")
                .placeNearTarget()
                .pointAt(util.vector.topOf(motor));
        scene.idle(70);

        Vec3 blockSurface = util.vector.blockSurface(motor, Direction.NORTH);
        scene.overlay.showFilterSlotInput(blockSurface, Direction.NORTH, 80);
        scene.overlay.showControls(new InputWindowElement(blockSurface, Pointing.DOWN).rightClick(), 60);
        scene.idle(20);

        scene.overlay.showText(60)
                .text("The generated speed can be configured on its input panels")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(blockSurface);
        scene.idle(10);
        scene.idle(50);
        scene.world.modifyKineticSpeed(util.select.fromTo(0, 1, 2, 3, 0, 2), f -> 4 * f);
        scene.idle(10);

        scene.effects.rotationSpeedIndicator(motor);
    }
}
