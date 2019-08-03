package cn.jsmod2.core.interapi.network;

import java.util.List;

public interface IResponse {

    Object get();

    List getArray();

    List getProtocolArray(boolean getArray);


}
