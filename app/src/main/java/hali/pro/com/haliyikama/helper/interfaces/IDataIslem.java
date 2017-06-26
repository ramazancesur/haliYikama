package hali.pro.com.haliyikama.helper.interfaces;

import java.util.List;

import hali.pro.com.haliyikama.helper.EnumUtil;

/**
 * Created by ramazancesur on 26/06/2017.
 */

public interface IDataIslem {
    <T> void addOrUpdate(T data, String serviceUrl,
                         EnumUtil.SendingDataType dataType);

    <T> List<T> get(String serviceUrl, Class clazz);

}