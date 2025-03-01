package fr.iglee42.createcasing.registries;

import com.simibubi.create.AllSoundEvents;
import fr.iglee42.createcasing.CreateCasing;
import net.minecraft.sounds.SoundSource;

public class ModSounds extends AllSoundEvents{

    public static final SoundEntry

            MLDEG = create("mldeg").subtitle("Gnee Drakonic")
            .category(SoundSource.BLOCKS)
            .build();




    private static SoundEntryBuilder create(String name) {
        return create(CreateCasing.asResource(name));
    }


}
