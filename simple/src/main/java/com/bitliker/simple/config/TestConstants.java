package com.bitliker.simple.config;

public class TestConstants {

    public static String JSONOBJECT="{\n" +
            "\t\"test\":\"sadhaskjdf\",\n" +
            "\t\"data\": [{\n" +
            "\t\t\"recordType\": 1,\n" +
            "\t\t\"recordTime\": \"2018-08-14 11:15:39\",\n" +
            "\t\t\"thumbnail\": \"/files/6e1e7ce4-474f-484c-bdcc-85c6733cc303.jpeg\",\n" +
            "\t\t\"recordName\": \"hdhd\",\n" +
            "\t\t\"personId\": 1\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"recordType\": 0,\n" +
            "\t\t\"recordTime\": \"2018-08-14 11:25:29\",\n" +
            "\t\t\"thumbnail\": \"/storage/emulated/0/faceDemo/facePhoto/strange/2/show.jpeg\",\n" +
            "\t\t\"recordName\": \"陌生人2\",\n" +
            "\t\t\"personId\": 2\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"recordType\": 1,\n" +
            "\t\t\"recordTime\": \"2018-08-14 11:25:33\",\n" +
            "\t\t\"thumbnail\": \"/files/6e1e7ce4-474f-484c-bdcc-85c6733cc303.jpeg\",\n" +
            "\t\t\"recordName\": \"hdhd\",\n" +
            "\t\t\"personId\": 1\n" +
            "\t}]\n" +
            "}";
    public static String ERROR_JSONOBJECT="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<android.support.constraint.ConstraintLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"wrap_content\"\n" +
            "    android:background=\"@drawable/bg_corners\">\n" +
            "\n" +
            "    <ImageView\n" +
            "        android:id=\"@+id/cloneIv\"\n" +
            "        android:layout_width=\"28dp\"\n" +
            "        android:layout_height=\"28dp\"\n" +
            "        android:layout_margin=\"8dp\"\n" +
            "        android:padding=\"8dp\"\n" +
            "        android:src=\"@mipmap/ic_delete\"\n" +
            "        android:visibility=\"gone\"\n" +
            "        app:layout_constraintRight_toRightOf=\"parent\"\n" +
            "        app:layout_constraintTop_toTopOf=\"parent\" />\n" +
            "\n" +
            "    <TextView\n" +
            "        android:id=\"@+id/titleTv\"\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:background=\"@drawable/bg_pop_top_title\"\n" +
            "        android:gravity=\"center\"\n" +
            "        android:padding=\"8dp\"\n" +
            "        android:text=\"确认录入人脸\"\n" +
            "        android:textColor=\"@color/white\" />\n" +
            "\n" +
            "    <ImageView\n" +
            "        android:id=\"@+id/faceIv\"\n" +
            "        android:layout_width=\"152dp\"\n" +
            "        android:layout_height=\"152dp\"\n" +
            "        android:layout_marginLeft=\"100dp\"\n" +
            "        android:layout_marginRight=\"100dp\"\n" +
            "        android:layout_marginTop=\"30dp\"\n" +
            "        android:background=\"@mipmap/bg_face\"\n" +
            "        android:padding=\"2dp\"\n" +
            "        app:layout_constraintLeft_toLeftOf=\"parent\"\n" +
            "        app:layout_constraintRight_toRightOf=\"parent\"\n" +
            "        app:layout_constraintTop_toBottomOf=\"@id/titleTv\" />\n" +
            "\n" +
            "\n" +
            "    <TextView\n" +
            "        android:id=\"@+id/ageTv\"\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_marginLeft=\"50dp\"\n" +
            "        android:layout_marginRight=\"10dp\"\n" +
            "        android:layout_marginTop=\"20dp\"\n" +
            "        android:paddingTop=\"6dp\"\n" +
            "        android:paddingBottom=\"6dp\"\n" +
            "        android:text=\"   年龄(age)：\"\n" +
            "        android:textColor=\"#FF494949\"\n" +
            "        android:textSize=\"14sp\"\n" +
            "        app:layout_constraintLeft_toLeftOf=\"parent\"\n" +
            "        app:layout_constraintTop_toBottomOf=\"@id/faceIv\" />\n" +
            "\n" +
            "    <EditText\n" +
            "        android:id=\"@+id/ageEd\"\n" +
            "        android:layout_width=\"0dp\"\n" +
            "        android:layout_height=\"0dp\"\n" +
            "        android:layout_marginRight=\"50dp\"\n" +
            "        android:inputType=\"number\"\n" +
            "        android:lines=\"1\"\n" +
            "        android:padding=\"0dp\"\n" +
            "        android:textColor=\"#FF494949\"\n" +
            "        android:textColorHint=\"#66494949\"\n" +
            "        android:textSize=\"14sp\"\n" +
            "        app:layout_constraintBottom_toBottomOf=\"@id/ageTv\"\n" +
            "        app:layout_constraintLeft_toRightOf=\"@id/ageTv\"\n" +
            "        app:layout_constraintRight_toRightOf=\"parent\"\n" +
            "        app:layout_constraintTop_toTopOf=\"@id/ageTv\" />\n" +
            "\n" +
            "    <View\n" +
            "        android:layout_width=\"0dp\"\n" +
            "        android:layout_height=\"1dp\"\n" +
            "        android:background=\"#979797\"\n" +
            "        app:layout_constraintLeft_toLeftOf=\"@id/ageEd\"\n" +
            "        app:layout_constraintRight_toRightOf=\"@id/ageEd\"\n" +
            "        app:layout_constraintTop_toBottomOf=\"@id/ageEd\" />\n" +
            "\n" +
            "    <TextView\n" +
            "        android:id=\"@+id/nameTv\"\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:layout_marginRight=\"10dp\"\n" +
            "        android:layout_marginTop=\"30dp\"\n" +
            "        android:text=\"姓名(name)：\"\n" +
            "        android:textColor=\"#FF494949\"\n" +
            "        android:textSize=\"14sp\"\n" +
            "        app:layout_constraintLeft_toLeftOf=\"@id/ageTv\"\n" +
            "        app:layout_constraintTop_toBottomOf=\"@id/ageTv\" />\n" +
            "\n" +
            "    <EditText\n" +
            "        android:id=\"@+id/nameEd\"\n" +
            "        android:layout_width=\"0dp\"\n" +
            "        android:layout_height=\"30dp\"\n" +
            "        android:background=\"@null\"\n" +
            "        android:lines=\"1\"\n" +
            "        android:textColor=\"#FF494949\"\n" +
            "        android:textColorHint=\"#66494949\"\n" +
            "        android:textSize=\"14sp\"\n" +
            "        app:layout_constraintBottom_toBottomOf=\"@id/nameTv\"\n" +
            "        app:layout_constraintLeft_toRightOf=\"@id/nameTv\"\n" +
            "        app:layout_constraintRight_toRightOf=\"@id/ageEd\"\n" +
            "        app:layout_constraintTop_toTopOf=\"@id/nameTv\" />\n" +
            "\n" +
            "    <View\n" +
            "        android:layout_width=\"0dp\"\n" +
            "        android:layout_height=\"1dp\"\n" +
            "        android:background=\"#979797\"\n" +
            "        app:layout_constraintLeft_toLeftOf=\"@id/nameEd\"\n" +
            "        app:layout_constraintRight_toRightOf=\"@id/nameEd\"\n" +
            "        app:layout_constraintTop_toBottomOf=\"@id/nameEd\" />\n" +
            "\n" +
            "    <LinearLayout\n" +
            "        android:layout_width=\"match_parent\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:gravity=\"center\"\n" +
            "        android:layout_marginTop=\"30dp\"\n" +
            "        app:layout_constraintTop_toBottomOf=\"@id/nameTv\"\n" +
            "        android:orientation=\"horizontal\">\n" +
            "\n" +
            "\n" +
            "        <android.support.v7.widget.AppCompatTextView\n" +
            "            android:id=\"@+id/sureTv\"\n" +
            "            android:layout_width=\"0dp\"\n" +
            "            android:layout_height=\"wrap_content\"\n" +
            "            android:layout_weight=\"1\"\n" +
            "            android:background=\"@drawable/selector_rl_white_hint_bg\"\n" +
            "            android:clickable=\"true\"\n" +
            "            android:gravity=\"center\"\n" +
            "            android:padding=\"8dp\"\n" +
            "            android:text=\"确定\"\n" +
            "            android:textColor=\"?attr/colorAccent\"\n" +
            "            android:textSize=\"18sp\" />\n" +
            "\n" +
            "\n" +
            "        <android.support.v7.widget.AppCompatTextView\n" +
            "            android:id=\"@+id/cancelTv\"\n" +
            "            android:layout_width=\"0dp\"\n" +
            "            android:layout_height=\"wrap_content\"\n" +
            "            android:layout_weight=\"1\"\n" +
            "            android:background=\"@drawable/selector_rr_white_hint_bg\"\n" +
            "            android:clickable=\"true\"\n" +
            "            android:gravity=\"center\"\n" +
            "            android:padding=\"8dp\"\n" +
            "            android:text=\"取消\"\n" +
            "            android:textColor=\"?attr/colorAccent\"\n" +
            "            android:textSize=\"18sp\" />\n" +
            "    </LinearLayout>\n" +
            "</android.support.constraint.ConstraintLayout>";
}
