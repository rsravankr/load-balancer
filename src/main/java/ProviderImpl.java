public class ProviderImpl implements Provider {
    public static int maxPerProvider = 5;
    @Override
    public Instance get() {
        return new InstanceImpl();
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
