package fr.iglee42.createcasing.kubejs;

import dev.latvian.mods.kubejs.script.ScriptType;

public class KJSExternalHandlerImpl implements KJSExternalHandler{
    @Override
    public void dispatchRegisterEvent() {
        EncasedKJSEvents.REGISTER_SETS.post(ScriptType.STARTUP,new RegisterSetsEvent());
    }
}
