package yuria.growitout.proxies;

public class ClientProxy extends CommonProxy
{
    @Override
    public boolean isClient()
    {
        return true;
    }

    @Override
    public void registerHandlers()
    {
        super.registerHandlers();
    }
}