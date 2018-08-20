package com.bitliker.controller.bitjson;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 封装fastjson经常使用功能
 */
public class JSONUtils<T extends Object> {


    public static JSONObject parseObject(String message) {
        if (TextUtils.isEmpty(message)) {
            return new JSONObject();
        } else
            try {
                return JSON.parseObject(message);
            } catch (Exception e) {
                e.printStackTrace();
                return new JSONObject();
            }
    }

    public static JSONArray parseJSONArray(String message) {
        if (TextUtils.isEmpty(message)) {
            return new JSONArray();
        } else
            try {
                return JSON.parseArray(message);
            } catch (Exception e) {
                e.printStackTrace();
                return new JSONArray();
            }
    }

    public static JSONObject getJSONObject(String message, String... keys) {
        return getJSONObject(parseObject(message), keys);
    }

    public static JSONObject getJSONObject(JSONObject object, String... keys) {
        if (object != null && keys != null && keys.length > 0) try {
            for (String key : keys) {
                if (object.containsKey(key) && object.get(key) != null && object.get(key) instanceof JSONObject) {
                    return object.getJSONObject(key);
                }
            }
        } catch (Exception e) {
        }
        return new JSONObject();
    }

    public static JSONArray getJSONArray(String message, String... keys) {
        return getJSONArray(parseObject(message), keys);
    }

    public static JSONArray getJSONArray(JSONObject object, String... keys) {
        if (object != null && keys != null && keys.length > 0) try {
            for (String key : keys) {
                if (object.containsKey(key) && object.get(key) != null && object.get(key) instanceof JSONArray) {
                    return object.getJSONArray(key);
                }
            }
        } catch (Exception e) {
        }
        return new JSONArray();
    }

    public static boolean getBoolean(String message, String... keys) {
        return getBoolean(parseObject(message), keys);
    }

    public static boolean getBoolean(JSONObject object, String... keys) {
        if (keys != null && keys.length > 0) try {
            for (String key : keys) {
                if (object.containsKey(key) && object.get(key) != null && object.get(key) instanceof Boolean) {
                    return object.getBoolean(key);
                }
            }
        } catch (Exception e) {
        }
        return false;
    }


    public static String getString(String message, String... keys) {
        return getString(parseObject(message), keys);
    }

    public static String getString(JSONObject object, String... keys) {
        if (keys != null && keys.length > 0) try {
            for (String key : keys) {
                if (object.containsKey(key) && object.get(key) != null && object.get(key) instanceof String) {
                    return object.getString(key);
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static int getInt(JSONObject object, String... keys) {
        return getInt(object, 0, keys);
    }

    public static int getInt(String message, String... keys) {
        return getInt(parseObject(message), 0, keys);
    }

    public static int getInt(String message, int defValues, String... keys) {
        return getInt(parseObject(message), defValues, keys);
    }

    public static int getInt(JSONObject object, int defValues, String... keys) {
        if (keys != null && keys.length > 0) try {
            for (String key : keys) {
                if (object.containsKey(key) && object.get(key) != null && object.get(key) instanceof Integer) {
                    return object.getInteger(key);
                }
            }
        } catch (Exception e) {
        }
        return defValues;
    }

    public static double getDouble(JSONObject object, String... keys) {
        return getDouble(object, 0, keys);
    }

    public static double getDouble(String message, String... keys) {
        return getDouble(parseObject(message), 0, keys);
    }

    public static double getDouble(String message, double defValues, String... keys) {
        return getDouble(parseObject(message), defValues, keys);
    }

    public static double getDouble(JSONObject object, double defValues, String... keys) {
        if (keys != null && keys.length > 0) try {
            for (String key : keys) {
                if (object.containsKey(key) && object.get(key) != null && object.get(key) instanceof Double) {
                    return object.getDouble(key);
                }
            }
        } catch (Exception e) {
        }
        return defValues;
    }

    public static float getFalot(JSONObject object, String... keys) {
        return (float) getDouble(object, 0, keys);
    }

    public static float getFalot(String message, String... keys) {
        return getFalot(parseObject(message), 0, keys);
    }

    public static float getFalot(String message, float defValues, String... keys) {
        return getFalot(parseObject(message), defValues, keys);
    }

    public static float getFalot(JSONObject object, float defValues, String... keys) {
        if (keys != null && keys.length > 0) try {
            for (String key : keys) {
                if (object.containsKey(key) && object.get(key) != null && object.get(key) instanceof Float) {
                    return object.getFloat(key);
                }
            }
        } catch (Exception e) {
        }
        return defValues;
    }


    public static long getLong(JSONObject object, String... keys) {
        return getLong(object, 0, keys);
    }

    public static long getLong(String message, String... keys) {
        return getLong(parseObject(message), 0, keys);
    }

    public static long getLong(String message, long defValues, String... keys) {
        return getLong(parseObject(message), defValues, keys);
    }

    public static long getLong(JSONObject object, long defValues, String... keys) {
        if (keys != null && keys.length > 0) try {
            for (String key : keys) {
                if (object.containsKey(key) && object.get(key) != null && object.get(key) instanceof Long) {
                    return object.getLong(key);
                }
            }
        } catch (Exception e) {
        }
        return defValues;
    }

    public static String toJSONString(Object o) {
        if (o == null) {
            return "";
        } else {
            return JSON.toJSONString(o);
        }
    }


}
