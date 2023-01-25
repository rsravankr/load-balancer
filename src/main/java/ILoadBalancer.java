abstract class ILoadBalancer {
    public abstract Instance get(InvocationStrategy strategy);
    public abstract void addProvider(Provider provider);
    public abstract void removeProvider(Provider provider);
    public abstract void checkHeartBeat();
}
