package cn.jsmod2.core.interapi.network;


public interface IRequester {

    IRequester with(String key, Object value);

    IRequester withObject(String key,Object value);

    IRequester end(String end);

    IRequester to();

    IResponse get();

    String getString();

    void reset();

}
