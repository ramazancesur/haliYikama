package hali.pro.com.haliyikama.helper.interfaces;

import android.content.Context;

import java.util.List;

import hali.pro.com.haliyikama.helper.EnumUtil;

/**
 * Created by ramazancesur on 26/06/2017.
 */

public interface IDataIslem {
    <T> void addOrUpdate(T data, String serviceUrl,
                         EnumUtil.SendingDataType dataType, Context ctx);

    <T> List<T> get(String serviceUrl, Class clazz, Context ctx);

    <T> void updateDeleteCreateProcess(EnumUtil.SendingDataType sendingDataType, String message, Context context,
                                       T data, String serviceUrl);
}