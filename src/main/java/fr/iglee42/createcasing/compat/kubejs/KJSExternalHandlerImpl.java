package fr.iglee42.createcasing.compat.kubejs;

public class KJSExternalHandlerImpl implements KJSExternalHandler{
    @Override
    public void dispatchRegisterEvent() {
        EncasedKJSEvents.REGISTER_SETS.post(new RegisterSetsEvent());
    }
}
